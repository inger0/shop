package com.app.controller;

import com.app.service.ClassifyService;
import com.common.utils.WebUtil;
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
@RequestMapping(value = "classify")
public class ClassifyController {

    @Autowired
    ClassifyService classifyService;

    @RequestMapping(value = "getGoods",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getGoods(Integer classifyId){
        try {
            return WebUtil.result(classifyService.getGoods(classifyId));
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("getGoods failure");
        }

    }

}
