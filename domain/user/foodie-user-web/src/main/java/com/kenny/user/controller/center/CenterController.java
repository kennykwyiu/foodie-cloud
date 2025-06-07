package com.kenny.user.controller.center;

import com.kenny.pojo.IMOOCJSONResult;
import com.kenny.user.pojo.Users;
import com.kenny.user.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User Center", tags = {"User center related endpoints"})
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "Get user information", notes = "Get user information", httpMethod = "GET")
    @GetMapping("userInfo")
    public IMOOCJSONResult userInfo(
            @ApiParam(name = "userId", value = "User ID", required = true)
            @RequestParam String userId) {

        Users user = centerUserService.queryUserInfo(userId);
        return IMOOCJSONResult.ok(user);
    }


}
