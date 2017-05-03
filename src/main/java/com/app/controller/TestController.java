package com.app.controller;


import com.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yujingyang on 2017/3/27.
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("shop.jsp");
    }
}
