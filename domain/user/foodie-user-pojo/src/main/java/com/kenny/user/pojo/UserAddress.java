package com.kenny.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_address")
public class UserAddress {
    /**
     * Address primary key ID
     */
    @Id
    private String id;

    /**
     * Associated user ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Recipient name
     */
    private String receiver;

    /**
     * Recipient mobile number
     */
    private String mobile;

    /**
     * Province
     */
    private String province;

    /**
     * City
     */
    private String city;

    /**
     * District
     */
    private String district;

    /**
     * Detailed address
     */
    private String detail;

    /**
     * Extended field
     */
    private String extand;

    /**
     * Whether it is the default address
     */
    @Column(name = "is_default")
    private Integer isDefault;

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
     * Get address primary key ID
     *
     * @return id - Address primary key ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set address primary key ID
     *
     * @param id Address primary key ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get associated user ID
     *
     * @return user_id - Associated user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set associated user ID
     *
     * @param userId Associated user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get recipient name
     *
     * @return receiver - Recipient name
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Set recipient name
     *
     * @param receiver Recipient name
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * Get recipient mobile number
     *
     * @return mobile - Recipient mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set recipient mobile number
     *
     * @param mobile Recipient mobile number
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Get province
     *
     * @return province - Province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Set province
     *
     * @param province Province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Get city
     *
     * @return city - City
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     *
     * @param city City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get district
     *
     * @return district - District
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Set district
     *
     * @param district District
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Get detailed address
     *
     * @return detail - Detailed address
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Set detailed address
     *
     * @param detail Detailed address
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Get extended field
     *
     * @return extand - Extended field
     */
    public String getExtand() {
        return extand;
    }

    /**
     * Set extended field
     *
     * @param extand Extended field
     */
    public void setExtand(String extand) {
        this.extand = extand;
    }

    /**
     * Get whether it is the default address
     *
     * @return is_default - Whether it is the default address
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * Set whether it is the default address
     *
     * @param isDefault Whether it is the default address
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Get creation time
     *
     * @return created_time - Creation time
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
     * @return updated_time - Update time
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