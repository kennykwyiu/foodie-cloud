package com.kenny.controller;


import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    // Payment center call address
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    // WeChat payment success -> Payment center -> Foodie platform
    //                       |-> Callback notification url
    String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

    // User avatar upload location
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
                                                            File.separator + "images" +
                                                            File.separator + "foodie" +
                                                            File.separator + "faces";
//    public static final String IMAGE_USER_FACE_LOCATION = "/workspaces/images/foodie/faces";


    // FIXME move below logic to order center
//    @Autowired
//    public MyOrdersService myOrdersService;
//
//    /**
//     * Used to verify the relationship between user and order to prevent unauthorized access
//     * @return
//     */
//    public IMOOCJSONResult checkUserOrder(String userId, String orderId) {
//        Orders order = myOrdersService.queryMyOrder(userId, orderId);
//        if (order == null) {
//            return IMOOCJSONResult.errorMsg("Order does not exist!");
//        }
//        return IMOOCJSONResult.ok(order);
//    }
}
