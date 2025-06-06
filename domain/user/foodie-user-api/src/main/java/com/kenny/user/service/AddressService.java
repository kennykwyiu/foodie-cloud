package com.kenny.user.service;


import com.kenny.user.pojo.UserAddress;
import com.kenny.user.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {

    /**
     * Query user's shipping address list by user ID
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    /**
     * Add new address for user
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * Update user's address
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);

    /**
     * Delete user's address information by user ID and address ID
     * @param userId
     * @param addressId
     */
    public void deleteUserAddress(String userId, String addressId);

    /**
     * Update default address
     * @param userId
     * @param addressId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * Query specific user address object information by user ID and address ID
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddres(String userId, String addressId);
}
