package com.app.controller;

import com.app.service.MainPageService;
import com.common.utils.Constants;
import com.common.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Controller
@RequestMapping(value = "main")
public class MainPageController {

    @Autowired
    MainPageService mainPageService;

    @RequestMapping(value = "getClassify" ,method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getClassify(){
        try {
            return WebUtil.result(mainPageService.getClassify());

        }catch (Exception e){
            return WebUtil.error("服务器提了一个问题");
        }
    }

    @RequestMapping(value = "getMainPageGoods",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getGoods(){
        try {

            return WebUtil.result(mainPageService.getMainPageGood());
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("服务器提了一个问题");
        }

    }
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getUserInfo(HttpSession session){
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            return WebUtil.result(mainPageService.getUserInfo(userId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("获取用户信息错误");
        }
    }

    @RequestMapping(value = "getAdvertisingShop",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAdvertisingShop() throws IOException {
        File file = new File(Constants.PROPERTIES_DIR+"app.properties");
        Properties prop = new Properties();
        prop.load(new FileInputStream(file));
        Integer shopId = Integer.parseInt((String)prop.get("advertisingShopId"));
        return WebUtil.result(shopId);
    }



}
