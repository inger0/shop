package com.app.controller;

import com.app.dao.ShopDao;
import com.app.model.po.GoodPO;
import com.app.model.po.ShopPO;
import com.app.service.GoodService;
import com.app.utils.MapperPO2DTO;
import com.app.utils.PO2MapUtil;
import com.app.utils.WebUtil;
import com.app.utils.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            Map<String,Object>  map = (new PO2MapUtil<GoodPO>()).mapper(goodPO);
            map.put("telephone",shopDao.queryShopById(goodPO.getShopId()).getTelephone());
            return WebUtil.result(map);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("get good failure");
        }
    }



    @RequestMapping(value = "addGoodToCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addGoodToCart(Integer goodId, HttpSession session) throws Exception {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            System.out.println(goodService);
            int count = goodService.addGoodToCast(goodId, userId);
            return WebUtil.result(count);
        } catch (Exception e) {
            return WebUtil.error("addGoodToCart failure");
        }

    }

    @RequestMapping(value = "getOrdersInCart", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getOrdersInCart(HttpSession session) throws IllegalAccessException {
        Integer userId = (Integer) session.getAttribute("userId");

        if(userId == null)
            return WebUtil.error("please login");
        else{

            return WebUtil.result(goodService.getOrderInfo(userId,OrderStatus.GOOD_IN_CART));
        }
    }

    //TODO 设置成只有登录的用户才可以删除 且只能删除自己的
    @RequestMapping(value = "deleteGoodFromCart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteGoodFromCart(Integer orderId, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return  WebUtil.error("please login");
        }
        int result = goodService.deleteGoodFromCart(orderId, userId);
        if (result > 0) {
            return WebUtil.result("");
        }else {
            return WebUtil.error("no goods found");
        }
    }

    @RequestMapping(value = "changeOrderCount/{orderId}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> changeOrderCount(@PathVariable("orderId") Integer orderId, int count, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId  == null){
            return WebUtil.error("please login");
        }

        int result = goodService.changeOrderCount(count,orderId,userId);

        if(result < 0){
            return WebUtil.error("change Order Count failure");
        }
        Map<String,Integer> returnData = new HashMap();
        returnData.put("count", result);
        return WebUtil.result(returnData);
    }

    @RequestMapping(value = "commitOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> commitOrder(ArrayList<Integer> orderIds,HttpSession session){
        try {
            goodService.commitOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        }
        catch (Exception e){
            return WebUtil.error("commit order failure");
        }
    }

    @RequestMapping(value = "abandonOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> abandonOrder(ArrayList<Integer> orderIds,HttpSession session){
        try {
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        }
        catch (Exception e){
            return WebUtil.error("abandon order failure");
        }
    }

    @RequestMapping(value = "payedOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> payedOrder(ArrayList<Integer> orderIds,HttpSession session){
        try {
            //TODO 支付二次验证 需要前端将API使用数据传入 进行是否交款验证
            goodService.abandonOrder(orderIds, (Integer) session.getAttribute("userId"));
            return WebUtil.result("");
        }
        catch (Exception e){
            return WebUtil.error("commit order failure");
        }
    }

    @RequestMapping(value = "getRate",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> changeOrderInfo(){
        Map<String,Double> map = new HashMap();
        map.put("coinRate",0.3);
        map.put("pointRate",0.3);
        return WebUtil.result(map);
    }

    @RequestMapping(value = "getOrderInfo/{status}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getOrderInfo(@PathVariable("status") String status, HttpSession session){
        int queryStatus;
        switch (status){
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
            return WebUtil.result(goodService.getOrderInfo((Integer) session.getAttribute("userId"),queryStatus));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return WebUtil.error("get order info error");
        }
    }

}
