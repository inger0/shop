package com.app.controller;

import com.common.model.po.AddressPO;
import com.app.service.AccountService;
import com.common.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    AccountService accountService;

    //TODO 缺少获得管理员邀请码接口

    //TODO 上线后删除此接口!
    @RequestMapping(value = "loginTest")
    public ResponseEntity<Map<String, Object>> loginTest(HttpServletResponse response, HttpSession session) {
        session.setAttribute("userId", 1);
        return WebUtil.result("success");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> register(String telephone, String checkCode, String invitationCode, HttpSession session) {
        try {
            String checkCodeInSession = (String) session.getAttribute("checkCode");
            session.setAttribute("checkCode", null);//使用后立刻销毁
            if (checkCodeInSession == null || !checkCodeInSession.equals(checkCode)) {
                return WebUtil.error("failure register");
            }
            accountService.register(telephone, invitationCode);
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("failure register");
        }

    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> params, HttpSession session) {
        try {
            String telephone = params.get("telephone");
            String checkCode = params.get("checkCode");
            String checkCodeInSession = (String) session.getAttribute("checkCode");
            session.setAttribute("checkCode", null);//使用后立刻销毁
            if (checkCodeInSession == null || !checkCodeInSession.equals(checkCode)) {
                return WebUtil.error("failure login");
            }
            //TODO 调用login
            Integer userId = accountService.login(telephone);
            if (userId == null)
                return WebUtil.error("failure login");
            session.setAttribute("userId", userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("failure login");
        }
    }

    @RequestMapping(value = "getMessage", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getMessage(@RequestBody Map<String, String> params, HttpSession session) {
        try {
            String telephone = params.get("telephone");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                SecureRandom random = new SecureRandom(
                        String.valueOf(new Date().getTime()).getBytes()
                );
                stringBuilder.append(String.valueOf(random.nextInt(10)));
            }
            accountService.sendMessage(stringBuilder.toString(), telephone);
            session.setAttribute("checkCode", stringBuilder.toString());
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("failure sendMessage");
        }
    }


    @RequestMapping(value = "addAddress", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addAddress(@RequestBody AddressPO addressPO, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        addressPO.setUserId(userId);
        int result = accountService.saveAddress(addressPO);

        if (result == -1) {
            WebUtil.error("add Address failue");
        }

        return WebUtil.result("");
    }

    @RequestMapping(value = "getAddress", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAddress(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.result("");
        }
        return WebUtil.result(accountService.getAddress(userId));
    }

    //TODO 需要登录 需要userId?
    @RequestMapping(value = "getOneAddress/{addressId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOneAddress(@PathVariable("addressId") Integer addressId) {
        return WebUtil.result(accountService.getAddressById(addressId));
    }

    @RequestMapping(value = "getDefaultAddress", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDefaultAddress(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("please login");
        return WebUtil.result(accountService.getDefaultAddress(userId));
    }


    @RequestMapping(value = "uploadHeadImg", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadHeadImg(@RequestParam("file") CommonsMultipartFile file, HttpSession session) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            InputStream fileInputStream = file.getInputStream();
            byte[] b = new byte[16];
            fileInputStream.read(b, 0, b.length);
            String fileTypeHex = String.valueOf(bytesToHexString(b));
            System.out.println(fileTypeHex);
            if (!(fileTypeHex.toUpperCase().startsWith("FFD8FF") || fileTypeHex.toUpperCase().startsWith("89504E47") || fileTypeHex.toUpperCase().startsWith("47494638") || fileTypeHex.toUpperCase().startsWith("424D"))) {
                return WebUtil.error("file type error");
            }
            String newFileName = System.currentTimeMillis() + "_" + messageDigest.digest(file.getOriginalFilename().getBytes("utf-8")) + type;
            System.out.println(newFileName);


            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                throw new Exception();
            }

            if (accountService.changeUserHeadImg(newFileName, userId) != 1) {
                throw new Exception();
            }

            File newFile = new File("/Users/yujingyang/codes/shop/src/main/resources/static/" + newFileName);
            file.transferTo(newFile);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("upload Head Img error");
        }

    }

    @RequestMapping(value = "changePayPassword", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changePayPassword(@RequestBody Map<String, String> params, String newPassword, HttpSession session) {
        String originalPassword = params.get("originalPassword");
        Integer userId = (Integer) session.getAttribute("userId");
        try {
            if (userId == null)
                return WebUtil.error("please login");
            accountService.changePayPassword(originalPassword, newPassword, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("change pay password error");
        }
    }

    @RequestMapping(value = "getOriginalTelephone", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOriginalTelephone(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("please login");
        return WebUtil.result(accountService.getOriginalTelephone(userId));
    }

    @RequestMapping(value = "changeTelephone", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeTelephone(@RequestBody Map<String, String> param, HttpSession session) {
        String newTelephone = param.get("newTelephone");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("please login");
        }
        try {
            accountService.changeTelephone(newTelephone, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("change telephone error");
        }
    }

    @RequestMapping(value = "getAdminCode", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAdminCode() {
        return WebUtil.result(accountService.getAdminCode());
    }

    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> checkLogin(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return WebUtil.result(userId != null);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
