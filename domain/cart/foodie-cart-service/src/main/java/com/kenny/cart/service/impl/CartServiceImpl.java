package com.kenny.cart.service.impl;

import caom.kenny.cart.service.CartService;
import com.kenny.pojo.ShopcartBO;
import com.kenny.utils.JsonUtils;
import com.kenny.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.kenny.controller.BaseController.FOODIE_SHOPCART;

public class CartServiceImpl implements CartService {
    @Autowired
    private RedisOperator redisOperator;

    @Override
    public boolean addItemToCart(@RequestParam("userId") String userId,
                                 @RequestBody ShopcartBO shopcartBO) {
        // When a logged-in user adds items to cart, synchronize with Redis cache
        // Check if item already exists in cart, if yes, increment the quantity
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        List<ShopcartBO> shopcartList = null;
        if (StringUtils.isNotBlank(shopcartJson)) {
            // Cart already exists in Redis
            shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            // Check if item exists in cart, if yes, increment the count
            boolean isHaving = false;
            for (ShopcartBO sc: shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(shopcartBO.getSpecId())) {
                    sc.setBuyCounts(sc.getBuyCounts() + shopcartBO.getBuyCounts());
                    isHaving = true;
                }
            }
            if (!isHaving) {
                shopcartList.add(shopcartBO);
            }
        } else {
            // No cart in Redis
            shopcartList = new ArrayList<>();
            // Add directly to cart
            shopcartList.add(shopcartBO);
        }

        // Overwrite existing cart in Redis
        redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartList));

        return true;
    }

    @Override
    public boolean removeItemFromCart(@RequestParam("userId") String userId,
                                      @RequestParam("itemSpecId") String itemSpecId) {
        // When user deletes item from cart page, if logged in, synchronize deletion with Redis cart
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        if (StringUtils.isNotBlank(shopcartJson)) {
            // Cart exists in Redis
            List<ShopcartBO> shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            // Check if item exists in cart, if yes, remove it
            for (ShopcartBO sc: shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(itemSpecId)) {
                    shopcartList.remove(sc);
                    break;
                }
            }
            // Overwrite existing cart in Redis
            redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartList));
        }

        return true;
    }

    @Override
    public boolean clearCart(@RequestParam("userId") String userId) {
        redisOperator.del(FOODIE_SHOPCART + ":" + userId);
        return true;
    }
}
