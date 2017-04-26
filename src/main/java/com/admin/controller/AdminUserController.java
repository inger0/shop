package com.admin.controller;

import com.admin.service.AdminUserService;
import com.common.utils.WebUtil;
import com.common.utils.enums.GoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/27.
 */
@Controller
@RequestMapping(value = "adminUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @RequestMapping(value = "queryByTelephone",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> queryUserByTelephone(String telephone){

        return WebUtil.result(adminUserService.getUserByTelephone(telephone));
    }

    @RequestMapping(value = "saveUser",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> saveUser(@RequestBody Map<String,Integer> params){
        try {

            adminUserService.changeParams(params.get("userId"),params.get("point"),params.get("coin"),params.get("diamond"));
            return WebUtil.result("");
        }catch (Exception e){
            return WebUtil.error("failure saveUser");
        }
    }
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateUser(Integer userId){
        adminUserService.updateToS(userId);
        return WebUtil.result("");
    }

    @RequestMapping(value = "queryGoodByName",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getGoodsByName(String goodName) throws UnsupportedEncodingException, IllegalAccessException {
        goodName = new String(goodName.getBytes("iso8859-1"),"UTF-8");
        return WebUtil.result(adminUserService.getGoodByName("%"+goodName+"%"));
    }

    @RequestMapping(value = "updateGood",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateStatus(Integer goodId, String status) throws Exception {
        Integer statusCode = null;
        switch (status){
            case "mainPage":
                statusCode = GoodStatus.MAIN_PAGE_GOOD;
            case "advertise":
                statusCode = GoodStatus.IS_ADDVER;
        }
        if(statusCode == null)
            throw new Exception();
        adminUserService.updateStatus(goodId,statusCode);
        return WebUtil.result("");
    }
}
