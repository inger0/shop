package com.app.controller;

import com.common.dao.ShopDao;
import com.common.model.po.GoodPO;
import com.app.service.GoodService;
import com.common.utils.PO2MapUtil;
import com.common.utils.WebUtil;
import com.common.utils.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Controller
@RequestMapping(value = "good")
public class GoodAndOrderController {

    @Autowired
    GoodService goodService;

    @Autowired
    ShopDao shopDao;

    @RequestMapping(value = "getGoodContent/{goodId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGoodContent(@PathVariable("goodId") Integer goodId) {
        try {
            GoodPO goodPO = goodService.getGoodById(goodId);
            Map<String, Object> map = (new PO2MapUtil<GoodPO>()).mapper(goodPO);
            map.put("telephone", shopDao.queryShopById(goodPO.getShopId()).getTelephone());
            return WebUtil.result(map);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("get good failure");
        }
    }


    @RequestMapping(value = "addGoodToCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addGoodToCart(@RequestBody Map<String,Integer> params, HttpSession session) throws Exception {
        try {
            Integer goodId = params.get("goodId");
            Integer userId = (Integer) session.getAttribute("userId");
            System.out.println(goodService);
            int count = goodService.addGoodToCast(goodId, userId);
            return WebUtil.result(count);
        } catch (Exception e) {
            return WebUtil.error("addGoodToCart failure");
        }

    }

    @RequestMapping(value = "getOrdersInCart", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOrdersInCart(HttpSession session) throws IllegalAccessException {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null)
            return WebUtil.error("please login");
        else {
            return WebUtil.result(goodService.getOrderInfo(userId, OrderStatus.GOOD_IN_CART));
        }
    }

    //TODO 设置成只有登录的用户才可以删除 且只能删除自己的
    @RequestMapping(value = "deleteGoodFromCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteGoodFromCart(@RequestBody Map<String,Integer> params, HttpSession session) {

        Integer orderId = params.get("orderId");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("please login");
        }
        int result = goodService.deleteGoodFromCart(orderId, userId);
        if (result > 0) {
            return WebUtil.result("");
        } else {
            return WebUtil.error("no goods found");
        }
    }

    @RequestMapping(value = "changeOrderCount/{orderId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeOrderCount(@PathVariable("orderId") Integer orderId, @RequestBody Map<String, Integer> params, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("please login");
        }
        Integer count = params.get("count");
        int result = goodService.changeOrderCount(count, orderId, userId);
        if (result < 0) {
            return WebUtil.error("change Order Count failure");
        }
        Map<String, Integer> returnData = new HashMap();
        returnData.put("count", result);
        return WebUtil.result(returnData);
    }

    @RequestMapping(value = "commitOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> commitOrder(@RequestBody  ArrayList<Integer> orderIds, HttpSession session) {
        try {
            goodService.commitOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("commit order failure");
        }
    }

    @RequestMapping(value = "abandonOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> abandonOrder(@RequestBody  ArrayList<Integer> orderIds, HttpSession session) {
        try {
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("abandon order failure");
        }
    }

    @RequestMapping(value = "payedOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> payedOrder(@RequestBody ArrayList<Integer> orderIds, HttpSession session) {
        try {
            //TODO 支付二次验证 需要前端将API使用数据传入 进行是否交款验证
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("commit order failure");
        }
    }

    @RequestMapping(value = "getRate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> changeOrderInfo() {
        Map<String, Double> map = new HashMap();
        map.put("coinRate", 0.3);
        map.put("pointRate", 0.3);
        return WebUtil.result(map);
    }

    @RequestMapping(value = "getOrderInfo/{status}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOrderInfo(@PathVariable("status") String status, HttpSession session) {
        int queryStatus;
        switch (status) {
            case "all":
                queryStatus = -1;
                break;
            case "wait4pay":
                queryStatus = OrderStatus.GOOD_WAITFOR_PAY;
                break;
            case "wait4receive":
                queryStatus = OrderStatus.GOOD_AFTER_SENT;
                break;
            default:
                return WebUtil.error("wrong status");
        }
        try {
            return WebUtil.result(goodService.getOrderInfo((Integer) session.getAttribute("userId"), queryStatus));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return WebUtil.error("get order info error");
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> search(String goodName) {
        try {

            return WebUtil.result(goodService.search(goodName));
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("failure search");
        }
    }

    @RequestMapping(value = "getGifts", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGifts() {
        try {
            return WebUtil.result(goodService.getGifts());
        } catch (Exception e) {
            return WebUtil.error("failure getGifts");
        }
    }

    @RequestMapping(value = "getGift/{giftId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGiftById(@PathVariable("giftId") Integer giftId) {
        if (giftId == null)
            return WebUtil.error("wrong giftId");
        try {
            return WebUtil.result(goodService.getGiftById(giftId));
        } catch (Exception e) {
            return WebUtil.error("failure getGift");
        }
    }

    @RequestMapping(value = "exchangeGift/{giftId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> exchangeGift(HttpSession session, @PathVariable("giftId") Integer giftId) {
        if (giftId == null)
            return WebUtil.error("giftId is null");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("please login");
        try {
            goodService.exchangeGift(giftId, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("failure exchange");
        }
    }


}
