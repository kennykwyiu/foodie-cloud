package com.kenny.user.service;


import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.UserBO;

public interface UserService {

    /**
     * Check if username exists
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * Create new user
     */
    public Users createUser(UserBO userBO);

    /**
     * Verify username and password match for login
     */
    public Users queryUserForLogin(String username, String password);
}
