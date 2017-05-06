package com.app.controller;

import com.app.service.GoodService;
import com.common.model.po.AddressPO;
import com.app.service.AccountService;
import com.common.model.po.UserPO;
import com.common.utils.Constants;
import com.common.utils.UUIDUtil;
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
    @Autowired
    GoodService goodService;

    //TODO 缺少获得管理员邀请码接口

    //TODO 上线后删除此接口!
    @RequestMapping(value = "loginTest")
    public ResponseEntity<Map<String, Object>> loginTest(HttpServletResponse response, HttpSession session) {
        session.setAttribute("userId", 29);
        return WebUtil.result("success");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> params, HttpSession session) {
        try {
            String telephone = params.get("telephone");
            String checkCode = params.get("checkCode");
            String invitationCode = params.get("invitationCode");
            String checkCodeInSession = (String) session.getAttribute("checkCode");
            session.setAttribute("checkCode", null);//使用后立刻销毁
            if (checkCodeInSession == null || !checkCodeInSession.equals(checkCode)) {
                return WebUtil.error("failure register");
            }
            String UUID = accountService.register(invitationCode, telephone);
            return WebUtil.result(UUID);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("注册失败 该手机号已存在");
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
                return WebUtil.error("验证码错误");
            }
            //TODO 调用login
            Integer userId = accountService.login(telephone);
            if (userId == null)
                return WebUtil.error("登录失败");
            session.setAttribute("userId", userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("登录失败");
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
            return WebUtil.error("发送信息失败");
        }
    }


    @RequestMapping(value = "logOut", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> logOut(HttpSession session) {
        session.setAttribute("userId", null);
        return WebUtil.result("");
    }

    @RequestMapping(value = "addAddress", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addAddress(@RequestBody AddressPO addressPO, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        addressPO.setUserId(userId);
        if (addressPO.getStatus() == null)
            addressPO.setStatus(Constants.ADDRESS_NOT_DEFAULT);
        int result = accountService.saveAddress(addressPO);

        if (result == -1) {
            WebUtil.error("添加地址失败");
        }

        return WebUtil.result("");
    }

    @RequestMapping(value = "updateAddress", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateAddress(@RequestBody AddressPO addressPO, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        addressPO.setUserId(userId);
        if (addressPO.getStatus() == null)
            addressPO.setStatus(Constants.ADDRESS_NOT_DEFAULT);
        int result = accountService.updateAddress(addressPO);

        if (result == -1) {
            WebUtil.error("添加地址失败");
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
            return WebUtil.error("请登录");
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
            String newFileName = System.currentTimeMillis() + "_" + UUIDUtil.generateUUID() + type;
            System.out.println(newFileName);


            Integer userId = (Integer) session.getAttribute("userId");

            if (userId == null) {
                return WebUtil.error("请登录");
            }

            if (accountService.changeUserHeadImg(newFileName, userId) != 1) {
                throw new Exception();
            }

            File newFile = new File("/var/www/static/userImgs/" + newFileName);
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
                return WebUtil.error("请登录");
            accountService.changePayPassword(originalPassword, newPassword, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("更改支付密码失败");
        }
    }

    @RequestMapping(value = "getOriginalTelephone", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getOriginalTelephone(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("请登录");
        return WebUtil.result(accountService.getOriginalTelephone(userId));
    }

    @RequestMapping(value = "changeTelephone", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeTelephone(@RequestBody Map<String, String> param, HttpSession session) {
        String newTelephone = param.get("newTelephone");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return WebUtil.error("请登录");
        }
        try {
            accountService.changeTelephone(newTelephone, userId);
            return WebUtil.result("");
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("更换手机号失败");
        }
    }

    @RequestMapping(value = "getUUID", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUUID(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("请登录");
        else {
            String UUID = accountService.getUUID(userId);
            return WebUtil.result(UUID);
        }
    }

    @RequestMapping(value = "wechatLogin", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> weChatLogin(String code, String UUID, HttpSession session, HttpServletResponse response) throws Exception {
        try {
            Integer userIdRe = accountService.wechatLogin(code, UUID);
            session.setAttribute("userId", userIdRe);
            response.sendRedirect("/");
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("请先注册");
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

    @RequestMapping(value = "getCharge", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCharge(HttpSession session, String channel, String orderNum, Integer price) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
            return WebUtil.error("请登录");
        try {

            String result = accountService.getCharge(userId, channel, orderNum, price);
            System.out.println(result);
            if (result.equals("error")) {
                return WebUtil.result("auth");
            } else {
                return WebUtil.result(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtil.error("获取支付凭据失败");
        }
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

    @RequestMapping(value = "paySucceed", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> paySucceed(String orderNum, HttpServletResponse response) throws IOException {
        goodService.paySucceed(orderNum);
        response.sendRedirect("/payment/succeed");
        return WebUtil.result("");
    }

    @RequestMapping(value = "changeUserName", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeUserName(HttpSession session, @RequestBody Map<String, String> params) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null)
                return WebUtil.error("请登录");
            else {
                String userName = params.get("userName");
                accountService.changeUserName(userId, userName);
            }
            return WebUtil.result("");
        }catch (Exception e){
            e.printStackTrace();
            return WebUtil.error("服务器内部错误");
        }
    }

    @RequestMapping(value = "getInvitationCode",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getInvitationCode(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return WebUtil.error("请登录");
        }
        return WebUtil.result(accountService.getInvitationCode(userId));
    }

}
