package com.kenny.user.controller;

import com.kenny.pojo.IMOOCJSONResult;
import com.kenny.user.pojo.UserAddress;
import com.kenny.user.pojo.bo.AddressBO;
import com.kenny.user.service.AddressService;
import com.kenny.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Address Related", tags = {"Address related API endpoints"})
@RequestMapping("address")
@RestController
public class AddressController {

    /**
     * On the order confirmation page, users can perform the following operations on shipping addresses:
     * 1. Query all shipping addresses
     * 2. Add new shipping address
     * 3. Delete shipping address
     * 4. Modify shipping address
     * 5. Set default address
     */

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Query shipping address list by user ID", notes = "Query shipping address list by user ID", httpMethod = "POST")
    @PostMapping("/list")
    public IMOOCJSONResult list(
            @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        List<UserAddress> list = addressService.queryAll(userId);
        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "Add new address", notes = "Add new address", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(@RequestBody AddressBO addressBO) {

        IMOOCJSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        addressService.addNewUserAddress(addressBO);

        return IMOOCJSONResult.ok();
    }
    private IMOOCJSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return IMOOCJSONResult.errorMsg("Receiver name cannot be empty");
        }
        if (receiver.length() > 12) {
            return IMOOCJSONResult.errorMsg("Receiver name is too long");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return IMOOCJSONResult.errorMsg("Receiver mobile number cannot be empty");
        }
        if (mobile.length() != 11) {
            return IMOOCJSONResult.errorMsg("Receiver mobile number length is incorrect");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return IMOOCJSONResult.errorMsg("Receiver mobile number format is incorrect");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return IMOOCJSONResult.errorMsg("Shipping address information cannot be empty");
        }

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "Update address", notes = "Update address", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(@RequestBody AddressBO addressBO) {

        if (StringUtils.isBlank(addressBO.getAddressId())) {
            return IMOOCJSONResult.errorMsg("Update address error: addressId cannot be empty");
        }

        IMOOCJSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        addressService.updateUserAddress(addressBO);

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "Delete address", notes = "Delete address", httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @RequestParam String userId,
            @RequestParam String addressId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.deleteUserAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "Set default address", notes = "Set default address", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public IMOOCJSONResult setDefalut(
            @RequestParam String userId,
            @RequestParam String addressId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.updateUserAddressToBeDefault(userId, addressId);
        return IMOOCJSONResult.ok();
    }
}
