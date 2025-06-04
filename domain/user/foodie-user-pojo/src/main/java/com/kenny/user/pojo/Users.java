package com.kenny.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Users {
    /**
     * Primary key ID - User ID
     */
    @Id
    private String id;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Nickname
     */
    private String nickname;

    /**
     * Real name
     */
    private String realname;

    /**
     * Profile picture
     */
    private String face;

    /**
     * Mobile number
     */
    private String mobile;

    /**
     * Email address
     */
    private String email;

    /**
     * Gender: 1: Male, 0: Female, 2: Undisclosed
     */
    private Integer sex;

    /**
     * Birthday
     */
    private Date birthday;

    /**
     * Creation time
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * Update time
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * Get primary key ID - User ID
     *
     * @return id - Primary key ID - User ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set primary key ID - User ID
     *
     * @param id Primary key ID - User ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get username
     *
     * @return username - Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password
     *
     * @return password - Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     *
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get nickname
     *
     * @return nickname - Nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set nickname
     *
     * @param nickname Nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get real name
     *
     * @return realname - Real name
     */
    public String getRealname() {
        return realname;
    }

    /**
     * Set real name
     *
     * @param realname Real name
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * Get profile picture
     *
     * @return face - Profile picture
     */
    public String getFace() {
        return face;
    }

    /**
     * Set profile picture
     *
     * @param face Profile picture
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * Get mobile number
     *
     * @return mobile - Mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set mobile number
     *
     * @param mobile Mobile number
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Get email address
     *
     * @return email - Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email address
     *
     * @param email Email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get gender: 1: Male, 0: Female, 2: Undisclosed
     *
     * @return sex - Gender: 1: Male, 0: Female, 2: Undisclosed
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * Set gender: 1: Male, 0: Female, 2: Undisclosed
     *
     * @param sex Gender: 1: Male, 0: Female, 2: Undisclosed
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * Get birthday
     *
     * @return birthday - Birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set birthday
     *
     * @param birthday Birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get creation time
     *
     * @return createdTime - Creation time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Set creation time
     *
     * @param createdTime Creation time
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Get update time
     *
     * @return updatedTime - Update time
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Set update time
     *
     * @param updatedTime Update time
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}