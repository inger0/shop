package com.admin.service.impl;

import com.admin.service.AdminUserService;
import com.common.dao.ClassifyDao;
import com.common.dao.GoodDao;
import com.common.dao.ShopDao;
import com.common.dao.UserDao;
import com.common.model.po.GoodPO;
import com.common.model.po.UserPO;
import com.common.utils.PO2MapUtil;
import com.common.utils.enums.GoodStatus;
import com.common.utils.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        System.out.println(goodName);
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
    public void updateStatus(int goodId, int status) {
        GoodPO goodPO = goodDao.queryGoodById(goodId);
        goodPO.setStatus(goodPO.getStatus() ^ status);
        goodDao.updatePO(goodPO);
    }

}
