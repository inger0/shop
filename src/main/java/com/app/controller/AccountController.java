package com.app.controller;

import com.app.utils.WebUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {
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
}
