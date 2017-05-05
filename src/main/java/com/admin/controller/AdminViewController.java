package com.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yujingyang on 2017/5/5.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminViewController {

    @RequestMapping(value = "/shop/{viewName}",method = RequestMethod.GET)
    public String getShopView(@PathVariable("viewName") String viewName){

        return "shop/"+viewName;
    }

    @RequestMapping(value = "/{viewName}",method = RequestMethod.GET)
    public String getView(@PathVariable("viewName") String viewName){

        return viewName;
    }
}
