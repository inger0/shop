package com.admin.service.impl;

import com.admin.service.AdminUserService;
import com.common.dao.ClassifyDao;
import com.common.dao.GoodDao;
import com.common.dao.ShopDao;
import com.common.dao.UserDao;
import com.common.model.po.ClassifyPO;
import com.common.model.po.GoodPO;
import com.common.model.po.ShopPO;
import com.common.model.po.UserPO;
import com.common.utils.PO2MapUtil;
import com.common.utils.enums.GoodStatus;
import com.common.utils.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/27.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    GoodDao goodDao;
    @Autowired
    ClassifyDao classifyDao;
    @Autowired
    ShopDao shopDao;

    @Override
    public UserPO getUserByTelephone(String telephone) {
        return userDao.queryUserByTelephone(telephone);
    }

    @Override
    public void changeParams(Integer userId, int point, int coin, int diamond) {
        UserPO userPO = userDao.queryUserById(userId);
        userPO.setCoin(coin);
        userPO.setPoint(point);
        userPO.setDiamond(diamond);
        userDao.updatePO(userPO);
    }

    @Override
    public void updateToS(Integer userId) {
        UserPO userPO = userDao.queryUserById(userId);
        userPO.setStatus(UserStatus.USER_IS_S);
        userDao.updatePO(userPO);
    }

    @Override
    public List<Map<String, Object>> getGoodByName(String goodName) throws IllegalAccessException {

        List<GoodPO> goodPOs = goodDao.queryGoodsByShopName(goodName);
        List<Map<String, Object>> result = new ArrayList<>();
        for (GoodPO po : goodPOs) {
            PO2MapUtil<GoodPO> po2MapUtil = new PO2MapUtil<>();
            Map<String, Object> tmp = po2MapUtil.mapper(po);
            tmp.put("shopName", shopDao.queryShopById(po.getShopId()).getName());
            String statusString = "";
            if ((po.getStatus() & GoodStatus.MAIN_PAGE_GOOD) > 0) {
                statusString += "首页展示 ";
            }
            if ((po.getStatus() & GoodStatus.IS_ADDVER) > 0) {
                statusString += "广告位展示 ";
            }
            tmp.put("statusString", statusString);
            result.add(tmp);
        }
        return result;
    }

    @Override
    public int updateStatus(int goodId, int status) {
        List<GoodPO> list = goodDao.queryGoodsByStatus(status);
        GoodPO goodPO = goodDao.queryGoodById(goodId);
        if (status == GoodStatus.IS_ADDVER && list.size() >= 3 && (goodPO.getStatus() ^ status) <= 0) {
            return -1;
        } else if (status == GoodStatus.MAIN_PAGE_GOOD) {
            List<GoodPO> list1 = goodDao.queryGoodsByStatusAndClassifyId(goodPO.getClassifyId(), status);
            if (list1.size() >= 9 && (goodPO.getStatus() ^ status) <= 0)
                return -2;
        }
        goodPO.setStatus(goodPO.getStatus() ^ status);
        goodDao.updatePO(goodPO);
        return 1;
    }

    @Override
    public List<Map<String, Object>> getGoodByStatus(int status) throws IllegalAccessException {
        List<GoodPO> list = goodDao.queryGoodsByStatus(status);
        List<Map<String, Object>> result = new ArrayList<>();
        for (GoodPO po : list) {
            PO2MapUtil<GoodPO> po2MapUtil = new PO2MapUtil<>();
            Map<String, Object> tmp = po2MapUtil.mapper(po);
            tmp.put("shopName", shopDao.queryShopById(po.getShopId()).getName());
            String statusString = "";
            if ((po.getStatus() & GoodStatus.MAIN_PAGE_GOOD) > 0) {
                statusString += "首页展示 ";
            }
            if ((po.getStatus() & GoodStatus.IS_ADDVER) > 0) {
                statusString += "广告位展示 ";
            }
            tmp.put("statusString", statusString);
            result.add(tmp);
        }
        return result;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteClassify(Integer classifyId) {
        List<GoodPO> tmp = goodDao.queryGoodsByClassifyId(classifyId);
        goodDao.batchDelete(tmp);
        classifyDao.deleteClassify(classifyId);
        return;
    }

    @Override
    public void addClassify(String classifyName, String classifyImg) {
        ClassifyPO classifyPO = new ClassifyPO();
        classifyPO.setClassifyName(classifyName);
        classifyPO.setClassifyImg(classifyImg);
        return;
    }

    @Override
    public void setShop(String path1, String path2, String shopName, String telephone, Integer userId) {
        ShopPO shopPO = shopDao.queryShopByUserId(userId);
        shopPO.setHeadImg(path1);
        shopPO.setActivityImg(path2);
        shopPO.setName(shopName);
        shopPO.setTelephone(telephone);
        shopDao.savePO(shopPO);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGood(GoodPO goodPO, Integer userId) {
        ClassifyPO classifyPO = classifyDao.queryClassifyById(goodPO.getClassifyId());
        goodPO.setClassifyName(classifyPO.getClassifyName());
        ShopPO shopPO = shopDao.queryShopByUserId(userId);
        goodPO.setShopId(shopPO.getId());
        goodPO.setStatus(GoodStatus.DAFAULT);
        goodDao.savePO(goodPO);
    }

    @Override
    public Map<String, Object> getUserTreeMiddle(Integer userId) {
        UserPO userPO = userDao.queryUserById(userId);
        Map<String,Object> tmp = new HashMap<>();
        tmp.put(String.valueOf(userPO.getId()),getUserTree(userId, userPO.getStatus() == UserStatus.USER_ADMIN, 0));
        return tmp;
    }


    private Map<String, Object> getUserTree(Integer userId, boolean isAdmin, int count) {

        UserPO userPO = userDao.queryUserById(userId);
        List<UserPO> queryResult = userDao.queryUsersByInviterId(userId);
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> tmp = new HashMap<>();
        Map<String, Object> children = new HashMap<>();

        params.put("text", userPO.getUserName());
        params.put("type", queryResult.size() == 0 ? "item" : "folder");
        if ((!isAdmin) && count >= 2)
            return params;
        for (UserPO row : queryResult) {
            children.put(String.valueOf(row.getId()), getUserTree(row.getId(), isAdmin, count++));
        }
        if (queryResult.size() > 0)
            tmp.put("children", children);
        if (queryResult.size() > 0)
            params.put("additionalParameters", tmp);

        return params;
    }
}
