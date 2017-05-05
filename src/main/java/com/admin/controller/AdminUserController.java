package com.admin.controller;

import com.admin.service.AdminUserService;
import com.app.service.MainPageService;
import com.common.dao.UserDao;
import com.common.model.po.GoodPO;
import com.common.model.po.UserPO;
import com.common.utils.UUIDUtil;
import com.common.utils.WebUtil;
import com.common.utils.enums.GoodStatus;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yujingyang on 2017/4/27.
 */
@Controller
@RequestMapping(value = "adminUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    MainPageService mainPageService;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "queryByTelephone", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryUserByTelephone(String telephone) {

        return WebUtil.result(adminUserService.getUserByTelephone(telephone));
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody Map<String, Integer> params) {
        try {

            adminUserService.changeParams(params.get("userId"), params.get("point"), params.get("coin"), params.get("diamond"));
            return WebUtil.result("");
        } catch (Exception e) {
            return WebUtil.error("failure saveUser");
        }
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateUser(Integer userId) {
        adminUserService.updateToS(userId);
        return WebUtil.result("");
    }

    @RequestMapping(value = "queryGoodByName", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGoodsByName(String goodName) throws UnsupportedEncodingException, IllegalAccessException {
        goodName = new String(goodName.getBytes("iso8859-1"), "UTF-8");
        return WebUtil.result(adminUserService.getGoodByName("%" + goodName + "%"));
    }

    @RequestMapping(value = "queryGoodByStatus", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryGoodByStatus(String goodType) throws UnsupportedEncodingException, IllegalAccessException {
        Integer statusCode = null;
        goodType = URLDecoder.decode(goodType, "utf-8");
        switch (goodType) {
            case "首页展示":
                statusCode = GoodStatus.MAIN_PAGE_GOOD;
                break;
            case "广告位":
                statusCode = GoodStatus.IS_ADDVER;
                break;
        }
        return WebUtil.result(adminUserService.getGoodByStatus(statusCode));
    }

    @RequestMapping(value = "updateGood", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateStatus(Integer goodId, String status) throws Exception {
        Integer statusCode = null;
        switch (status) {
            case "mainPage":
                statusCode = GoodStatus.MAIN_PAGE_GOOD;
                break;
            case "advertise":
                statusCode = GoodStatus.IS_ADDVER;
                break;
        }
        if (statusCode == null)
            throw new Exception();
        int result = adminUserService.updateStatus(goodId, statusCode);
        if (result == -1) {
            return WebUtil.error("广告位不能超过三个 请先取消某商品广告位");
        } else if (result == -2) {
            return WebUtil.error("首页商品每类六个 请先取消某商品首页展示");
        }
        return WebUtil.result("");
    }

    @RequestMapping(value = "uploadMainPageImg", method = RequestMethod.POST)
    public String uploadMainPageImg(@RequestParam("file1") CommonsMultipartFile file1, @RequestParam("file2") CommonsMultipartFile file2, @RequestParam("file3") CommonsMultipartFile file3, @RequestParam("file4") CommonsMultipartFile file4, HttpSession session) throws IOException {
        Integer userId = (Integer) session.getAttribute("userId");
        //TODO 根据userId判断用户权限
        //TODO 上线前改名字
        String type1 = file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf('.'));

        String type2 = file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf('.'));

        String type3 = file3.getOriginalFilename().substring(file3.getOriginalFilename().lastIndexOf('.'));

        String type4 = file4.getOriginalFilename().substring(file4.getOriginalFilename().lastIndexOf('.'));


        File newFile1 = new File("/Users/yujingyang/codes/slide1" + type1);
        File newFile2 = new File("/Users/yujingyang/codes/slide2" + type2);
        File newFile3 = new File("/Users/yujingyang/codes/slide3" + type3);
        File newFile4 = new File("/Users/yujingyang/codes/advertising" + type4);
        file1.transferTo(newFile1);
        file2.transferTo(newFile2);
        file3.transferTo(newFile3);
        file4.transferTo(newFile4);

        return "uploadSuccess";
    }

    @RequestMapping(value = "getRate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getRate() throws IOException {
        File file = new File("/Users/yujingyang/codes/shop/target/com.app.shop/WEB-INF/classes/config/app.properties");
        System.out.println(file.getAbsolutePath());
        Properties prop = new Properties();
        prop.load(new FileInputStream(file));
        Map<String, String> result = new HashMap<>();
        result.put("coinRate", prop.getProperty("coinRate"));
        result.put("pointRate", prop.getProperty("pointRate"));
        result.put("coinGet", prop.getProperty("coinGet"));
        result.put("pointGet", prop.getProperty("pointGet"));
        return WebUtil.result(result);
    }

    @RequestMapping(value = "setRate", method = RequestMethod.POST)
    public String setRate(@RequestParam("coinRate") Double coinRate, @RequestParam("pointRate") Double pointRate, @RequestParam("coinGet") Integer coinGet, @RequestParam("pointGet") Integer pointGet) throws IOException {
        File file = new File("/Users/yujingyang/codes/shop/target/com.app.shop/WEB-INF/classes/config/app.properties");
        System.out.println(file.getAbsolutePath());
        Properties prop = new Properties();
        prop.load(new FileInputStream(file));
        Map<String, String> result = new HashMap<>();
        prop.setProperty("coinRate", String.valueOf(coinRate));
        prop.setProperty("pointRate", String.valueOf(pointRate));
        prop.setProperty("coinGet", String.valueOf(coinGet));
        prop.setProperty("pointGet", String.valueOf(pointGet));
        prop.store(new FileOutputStream(file), "");
        return "uploadSuccess";
    }

    @RequestMapping(value = "getClassify", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getClassify() {
        return WebUtil.result(mainPageService.getClassify());
    }

    @RequestMapping(value = "deleteClassify/{classifyId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteClassify(@PathVariable("classifyId") Integer classifyId) {
        //TODO 检验身份
        adminUserService.deleteClassify(classifyId);
        return WebUtil.result("");
    }

    @RequestMapping(value = "addClassify", method = RequestMethod.POST)
    public String addClassify(@RequestParam("classifyName") String classifyName, @RequestParam("file1") CommonsMultipartFile file) throws IOException {
        String UUID = UUIDUtil.generateUUID();
        File newFile = new File("/var/www/static/classifyImg/" + UUID + file.getOriginalFilename());
        String filePath = "/classifyImg/" + UUID + file.getOriginalFilename();
        file.transferTo(newFile);
        adminUserService.addClassify(classifyName, filePath);
        return "uploadSuccess";
    }

    @RequestMapping(value = "uploadShopImg", method = RequestMethod.POST)
    public String uploadShopImg(@RequestParam("file1") CommonsMultipartFile file1, @RequestParam("file2") CommonsMultipartFile file2, String shopName, String telephone, HttpSession session) throws IOException {
        String UUID1 = UUIDUtil.generateUUID();
        String UUID2 = UUIDUtil.generateUUID();

        File newFile1 = new File("/var/www/static/shopImgs/" + UUID1 + file1.getOriginalFilename());
        File newFile2 = new File("/var/www/static/shopImgs/" + UUID2 + file2.getOriginalFilename());

        file1.transferTo(newFile1);
        file2.transferTo(newFile2);

        String path1 = "/shopImgs/" + UUID1 + file1.getOriginalFilename();
        String path2 = "/shopImgs/" + UUID1 + file2.getOriginalFilename();

        Integer userId = (Integer) session.getAttribute("userId");

        adminUserService.setShop(path1, path2, shopName, telephone, userId);
        return "uploadSuccess";
    }

    @RequestMapping(value = "addGoodToShop", method = RequestMethod.POST)
    public String addGoodToShop(
            String name, Integer classifyId, Integer originPrice, Integer stock, Integer minPrice, Integer maxCoin
            , Integer maxPoint, CommonsMultipartFile headImg, CommonsMultipartFile contentImg, CommonsMultipartFile content, HttpSession session
    ) throws IOException {
        GoodPO goodPO = new GoodPO();
        goodPO.setName(name);
        goodPO.setClassifyId(classifyId);
        goodPO.setOriginPrice(originPrice);
        goodPO.setStock(stock);
        goodPO.setSalesVolume(0);
        goodPO.setMinPrice(minPrice);
        goodPO.setMaxCoin(maxCoin);
        goodPO.setMaxPoint(maxPoint);
        String UUID = UUIDUtil.generateUUID();
        File newHeadImg = new File("/var/www/static/goodImgs/" + UUID + headImg.getOriginalFilename());
        File newContentImg = new File("/var/www/static/goodImgs/" + UUID + contentImg.getOriginalFilename());
        File newContent = new File("/var/www/static/goodImgs/" + UUID + content.getOriginalFilename());

        headImg.transferTo(newHeadImg);
        contentImg.transferTo(newContentImg);
        content.transferTo(newContent);

        goodPO.setHeadImg("/goodImgs/" + UUID + headImg.getOriginalFilename());
        goodPO.setContentImg("/goodImgs/" + UUID + contentImg.getOriginalFilename());
        goodPO.setContent("/goodImgs/" + UUID + content.getOriginalFilename());

        Integer userId = (Integer) session.getAttribute("userId");

        adminUserService.saveGood(goodPO, userId);


        return "uploadSuccess";
    }

    @RequestMapping(value = "getUserTree", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUserTree(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return WebUtil.result(adminUserService.getUserTreeMiddle(userId));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(String telephone, String password, HttpSession session) {
        if (BCrypt.checkpw(password, "$2a$10$YgHyjBPxgcy6lVj0V9BIR.fd0Jpwe/DnmDhCk57DW92/d/0wTXtqO")) {
            UserPO userPO = userDao.queryUserByTelephone(telephone);
            if (userPO != null) {
                session.setAttribute("userId", userPO.getId());
                return WebUtil.result("");
            }
        }
        return WebUtil.error("登录失败");

    }

}
