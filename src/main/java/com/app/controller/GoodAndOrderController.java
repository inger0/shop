package com.app.controller;

import com.app.service.GoodService;
import com.app.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Controller
@RequestMapping(value = "good")
public class GoodAndOrderController {

    @Autowired
    GoodService goodService;

    @RequestMapping(value = "getGoodContent/{goodId}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getGoodContent( @PathVariable("goodId") Integer goodId){
        try {
            return WebUtil.result(goodService.getGoodById(goodId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("get good failure");
        }
    }

    @RequestMapping(value = "addGoodToCart",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addGoodToCart(Integer goodId){

        return null;
    }


}
