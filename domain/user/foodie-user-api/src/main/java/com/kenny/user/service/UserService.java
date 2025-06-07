package com.kenny.user.service;

import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.UserBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user-api")
public interface UserService {

    /**
     * Check if username exists
     */
    @GetMapping("user/exist")
    public boolean queryUsernameIsExist(@RequestParam String username);

    /**
     * Create new user
     */
    @PostMapping("user")
    public Users createUser(@RequestBody UserBO userBO);

    /**
     * Verify username and password match for login
     */
    @GetMapping("verify")
    public Users queryUserForLogin(@RequestParam String username, @RequestParam String password);
}
