package com.kenny.user.pojo.bo;

/**
 * Business Object for adding or modifying user address
 */
public class AddressBO {

    /**
     * Address ID
     */
    private String addressId;

    /**
     * User ID
     */
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
