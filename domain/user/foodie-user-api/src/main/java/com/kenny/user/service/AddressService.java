package com.kenny.user.service;

import com.kenny.user.pojo.UserAddress;
import com.kenny.user.pojo.bo.AddressBO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("address-api")
public interface AddressService {

    /**
     * Query user's shipping address list by user ID
     * @param userId
     * @return
     */
    @GetMapping("addressList")
    public List<UserAddress> queryAll(@RequestParam String userId);

    /**
     * Add new address for user
     * @param addressBO
     */
    @PostMapping("address")
    public void addNewUserAddress(@RequestBody AddressBO addressBO);

    /**
     * Update user's address
     * @param addressBO
     */
    @PutMapping("address")
    public void updateUserAddress(@RequestBody AddressBO addressBO);

    /**
     * Delete user's address information by user ID and address ID
     * @param userId
     * @param addressId
     */
    @DeleteMapping("address")
    public void deleteUserAddress(@RequestParam String userId, @RequestParam String addressId);

    /**
     * Update default address
     * @param userId
     * @param addressId
     */
    @PostMapping("setDefaultAddress")
    public void updateUserAddressToBeDefault(@RequestParam String userId, @RequestParam String addressId);

    /**
     * Query specific user address object information by user ID and address ID
     * @param userId
     * @param addressId
     * @return
     */
    @GetMapping("queryAddress")
    public UserAddress queryUserAddres(@RequestParam String userId, @RequestParam(required = false) String addressId);
}
