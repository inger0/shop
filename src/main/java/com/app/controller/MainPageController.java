package com.app.controller;

import com.app.service.MainPageService;
import com.app.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

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
            return WebUtil.error("get classify failure");
        }
    }


}
