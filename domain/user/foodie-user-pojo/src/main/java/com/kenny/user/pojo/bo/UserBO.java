package com.kenny.user.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User Business Object", description = "Data received from client, encapsulated in this entity")
public class UserBO {

    @ApiModelProperty(value = "Username", name = "username", example = "imooc", required = true)
    private String username;
    @ApiModelProperty(value = "Password", name = "password", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "Confirm Password", name = "confirmPassword", example = "123456", required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
