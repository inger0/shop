package com.app.controller;

import com.app.service.ShopService;
import com.app.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Controller
@RequestMapping(value = "shop")
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    ShopService shopService;


    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAll(Integer shopId){
        try {
            return WebUtil.result(shopService.getAll(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get all goods failure");
        }
    }

    @RequestMapping(value = "getHotSale", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getHotSale(Integer shopId){
        try {
            return WebUtil.result(shopService.getHotSale(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get hot sale goods failure");
        }
    }

    @RequestMapping(value = "getShopMainPage", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getShopMainPage(int shopId){
        try {
            return WebUtil.result(shopService.getShopMainPage(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get shop main page failure");
        }
    }

    @RequestMapping(value = "getActivityImgUrl", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getActivityImgUrl(int shopId){
        try {
            return WebUtil.result(shopService.getShopActivityImgUrl(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get activity img failure");
        }
    }

    @RequestMapping(value = "getTelephone", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getTelephone(int shopId){
        try {
            return WebUtil.result(shopService.getTelephone(shopId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get telephone failure");
        }
    }


}
