package com.app.controller;

import com.app.model.po.AddressPO;
import com.app.service.AccountService;
import com.app.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "login")
    public ResponseEntity<Map<String, Object>> login(HttpServletResponse response, HttpSession session) {
        session.setAttribute("userName", "tmp");
        session.setAttribute("userId",1);
        Cookie userName = new Cookie("userName", "tmp");
        Cookie loginCode = new Cookie("loginCode", "tmpCode");
        response.addCookie(userName);
        response.addCookie(loginCode);
        return WebUtil.result("success");
    }

    @RequestMapping(value = "addAddress",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addAddress(@RequestBody AddressPO addressPO,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        addressPO.setUserId(userId);
        int result = accountService.saveAddress(addressPO);

        if(result == -1){
            WebUtil.error("add Address failue");
        }

        return WebUtil.result("");
    }

    @RequestMapping(value = "getAddress",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAddress(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            return WebUtil.result("");
        }
        return WebUtil.result(accountService.getAddress(userId));
    }

    //TODO 需要登录 需要userId?
    @RequestMapping(value = "getOneAddress/{addressId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getOneAddress(@PathVariable("addressId") Integer addressId){
        return WebUtil.result(accountService.getAddressById(addressId));
    }

    @RequestMapping(value = "uploadHeadImg",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> uploadHeadImg(@RequestParam("file") CommonsMultipartFile file,HttpSession session){
        File newFile = new File("/Users/yujingyang/codes/shop/src/main/resources/static/"+System.currentTimeMillis()+"_"+file.getOriginalFilename());
        try {
            if(!newFile.exists())
                newFile.createNewFile();
            file.transferTo(newFile);
            return WebUtil.result("");
        } catch (IOException e) {
            e.printStackTrace();
            return WebUtil.error("upload Head Img error");
        }

    }









}
