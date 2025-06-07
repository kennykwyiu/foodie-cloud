package com.kenny.user.controller;

import com.kenny.controller.BaseController;
import com.kenny.pojo.IMOOCJSONResult;
import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.ShopcartBO;
import com.kenny.user.pojo.bo.UserBO;
import com.kenny.user.service.UserService;
import com.kenny.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(value = "Registration and Login", tags = {"Registration and login related endpoints"})
@RestController
@RequestMapping("passport")
public class PassportController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "Check if username exists", notes = "Check if username exists", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {

        // 1. Check if username is not empty
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("Username cannot be empty");
        }

        // 2. Check if username already exists
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("Username already exists");
        }

        // 3. Request successful, username is not duplicated
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "User registration", notes = "User registration", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0. Check if username and password are not empty
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("Username or password cannot be empty");
        }

        // 1. Check if username exists
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("Username already exists");
        }

        // 2. Password length must be at least 6 characters
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("Password length must be at least 6 characters");
        }

        // 3. Check if passwords match
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("Passwords do not match");
        }

        // 4. Create user
        Users userResult = userService.createUser(userBO);

        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO Generate user token and store in redis session
        // Synchronize shopping cart data
        synchShopcartData(userResult.getId(), request, response);

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "User login", notes = "User login", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 0. Check if username and password are not empty
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("Username or password cannot be empty");
        }

        // 1. Login
        Users userResult = userService.queryUserForLogin(username,
                    MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return IMOOCJSONResult.errorMsg("Username or password is incorrect");
        }

        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO Generate user token and store in redis session
        // Synchronize shopping cart data
        synchShopcartData(userResult.getId(), request, response);

        return IMOOCJSONResult.ok(userResult);
    }

    /**
     * After successful registration/login, synchronize shopping cart data between cookie and redis
     */
    // TODO put into shopCart module
    private void synchShopcartData(String userId, HttpServletRequest request,
                                   HttpServletResponse response) {

        /**
         * 1. If redis has no data:
         *    - If cookie shopping cart is empty, do nothing
         *    - If cookie shopping cart has items, add them to redis
         * 2. If redis has data:
         *    - If cookie shopping cart is empty, overwrite local cookie with redis data
         *    - If cookie shopping cart has items:
         *      - If an item exists in both cookie and redis, use cookie data (overwrite redis)
         *      - Reference: JD.com's implementation
         * 3. After syncing to redis, overwrite local cookie to ensure data consistency
         */

        // Get shopping cart from redis
        String shopcartJsonRedis = redisOperator.get(FOODIE_SHOPCART + ":" + userId);

        // Get shopping cart from cookie
        String shopcartStrCookie = CookieUtils.getCookieValue(request, FOODIE_SHOPCART, true);

        if (StringUtils.isBlank(shopcartJsonRedis)) {
            // Redis is empty, cookie has data, add cookie data to redis
            if (StringUtils.isNotBlank(shopcartStrCookie)) {
                redisOperator.set(FOODIE_SHOPCART + ":" + userId, shopcartStrCookie);
            }
        } else {
            // Redis has data, cookie has data, merge shopping cart items (overwrite redis for same items)
            if (StringUtils.isNotBlank(shopcartStrCookie)) {

                /**
                 * 1. For existing items, overwrite redis quantity with cookie quantity (reference: JD.com)
                 * 2. Mark items for deletion in a pending list
                 * 3. Remove all pending items from cookie
                 * 4. Merge redis and cookie data
                 * 5. Update redis and cookie
                 */

                List<ShopcartBO> shopcartListRedis = JsonUtils.jsonToList(shopcartJsonRedis, ShopcartBO.class);
                List<ShopcartBO> shopcartListCookie = JsonUtils.jsonToList(shopcartStrCookie, ShopcartBO.class);

                // Define pending deletion list
                List<ShopcartBO> pendingDeleteList = new ArrayList<>();

                for (ShopcartBO redisShopcart : shopcartListRedis) {
                    String redisSpecId = redisShopcart.getSpecId();

                    for (ShopcartBO cookieShopcart : shopcartListCookie) {
                        String cookieSpecId = cookieShopcart.getSpecId();

                        if (redisSpecId.equals(cookieSpecId)) {
                            // Overwrite quantity, don't add up (reference: JD.com)
                            redisShopcart.setBuyCounts(cookieShopcart.getBuyCounts());
                            // Add cookieShopcart to pending deletion list for final deletion and merge
                            pendingDeleteList.add(cookieShopcart);
                        }

                    }
                }

                // Remove overwritten items from cookie
                shopcartListCookie.removeAll(pendingDeleteList);

                // Merge lists
                shopcartListRedis.addAll(shopcartListCookie);
                // Update redis and cookie
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART, JsonUtils.objectToJson(shopcartListRedis), true);
                redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartListRedis));
            } else {
                // Redis has data, cookie is empty, overwrite cookie with redis data
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART, shopcartJsonRedis, true);
            }

        }
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "User logout", notes = "User logout", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        // Clear user-related cookies
        CookieUtils.deleteCookie(request, response, "user");

        // TODO Clear shopping cart on logout
        // Clear user data from distributed session
        CookieUtils.deleteCookie(request, response, FOODIE_SHOPCART);

        return IMOOCJSONResult.ok();
    }

}
