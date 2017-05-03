package com.app.controller;

import com.common.dao.ShopDao;
import com.common.model.dto.OrderAndGoodDTO;
import com.common.model.po.GoodPO;
import com.app.service.GoodService;
import com.common.model.po.OrderPO;
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
import java.util.List;
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
            return WebUtil.error("获取商品失败");
        }
    }


    @RequestMapping(value = "addGoodToCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addGoodToCart(@RequestBody Map<String, Integer> params, HttpSession session) throws Exception {
        try {
            Integer goodId = params.get("goodId");

            Integer userId = (Integer) session.getAttribute("userId");
            if(userId == null)
                return WebUtil.error("请登录");
            System.out.println(goodService);
            int count = goodService.addGoodToCast(goodId, userId);
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            return WebUtil.result(map);
        } catch (Exception e) {
            return WebUtil.error("添加到购物车失败");
        }

    }

    @RequestMapping(value = "getOrdersInCart", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOrdersInCart(HttpSession session) throws IllegalAccessException {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null)
            return WebUtil.error("请登录");
        else {
            return WebUtil.result(goodService.getOrderInfo(userId, OrderStatus.GOOD_IN_CART));
        }
    }

    //TODO 设置成只有登录的用户才可以删除 且只能删除自己的
    @RequestMapping(value = "deleteGoodFromCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteGoodFromCart(@RequestBody Map<String, Integer> params, HttpSession session) {

        Integer orderId = params.get("orderId");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("请登录");
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
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return WebUtil.error("请登录");
            }
            Integer count = params.get("count");
            int result = goodService.changeOrderCount(count, orderId, userId);
            if (result < 0) {
                return WebUtil.error("服务器提了一个问题");
            }
            Map<String, Integer> returnData = new HashMap();
            returnData.put("count", result);
            return WebUtil.result(returnData);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "commitOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> commitOrder(@RequestBody Map<String,List<Integer>> params, HttpSession session) {
        try {
            List<Integer> orderIds = params.get("orderIds");
            goodService.commitOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("完成支付的订单数为0");
        }
    }

    @RequestMapping(value = "abandonOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> abandonOrder(@RequestBody ArrayList<Integer> orderIds, HttpSession session) {
        try {
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("服务器提了一个问题");
        }
    }

    //TODO 补全接口 录入付款订单数据
    @RequestMapping(value = "payedOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> payedOrder(@RequestBody ArrayList<Integer> orderIds, HttpSession session) {
        try {
            //TODO 支付二次验证 需要前端将API使用数据传入 进行是否交款验证
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("服务器提了一个问题");
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
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("请登录");
        }
        switch (status) {
            case "All":
                queryStatus = -1;
                break;
            case "wait4pay":
                queryStatus = OrderStatus.GOOD_WAITFOR_PAY;
                break;
            case "wait4receive":
                queryStatus = OrderStatus.GOOD_AFTER_SENT;
                break;
            default:
                return WebUtil.error("状态码错误");
        }
        try {
            return WebUtil.result(goodService.getOrderInfo(userId, queryStatus));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> search(@RequestBody Map<String,String> params) {
        try {
            String goodName = params.get("goodName");
            return WebUtil.result(goodService.search(goodName));
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "getGifts", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGifts() {
        try {
            return WebUtil.result(goodService.getGifts());
        } catch (Exception e) {
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "getGift/{giftId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGiftById(@PathVariable("giftId") Integer giftId) {
        if (giftId == null)
            return WebUtil.error("wrong giftId");
        try {
            return WebUtil.result(goodService.getGiftById(giftId));
        } catch (Exception e) {
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "exchangeGift/{giftId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> exchangeGift(HttpSession session, @PathVariable("giftId") Integer giftId) {
        if (giftId == null)
            return WebUtil.error("giftId is null");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("请先登录");
        try {
            goodService.exchangeGift(giftId, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("兑换失败");
        }
    }

    @RequestMapping(value = "confirmOrder",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> confirmOrder(@RequestBody Map<String,List<OrderAndGoodDTO>> params){
        try {
            return WebUtil.result(goodService.confirmOrders(params.get("lastOrders")));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("提交结算失败");
        }


    }

}
