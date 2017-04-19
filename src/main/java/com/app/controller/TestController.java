package com.app.controller;


import com.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yujingyang on 2017/3/27.
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public  String test(){

        return testService.getTestContent();
    }
}
