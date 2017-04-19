package com.app.service.impl;

import com.app.dao.GoodDao;
import com.app.dao.ShopDao;
import com.app.model.po.GoodPO;
import com.app.model.po.ShopPO;
import com.app.service.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.utils.enums.GoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Autowired
    GoodDao goodDao;

    @Override
    public List<GoodPO> getAll(int shopId) {
        return goodDao.queryGoodsByShopId(shopId);
    }

    @Override
    public List<GoodPO> getHotSale(int shopId) {
        return goodDao.queryGoodsByShopIdAndStatus(shopId, GoodStatus.SHOP_HOT_SALE);
    }

    @Override
    public Map<String,Object> getShopMainPage(int shopId){
        ShopPO shopPO = shopDao.queryShopById(shopId);
        List<GoodPO> poList = goodDao.queryGoodsByShopIdAndStatus(shopId,GoodStatus.SHOP_MAIN_PAGE_GOOD);
        Map<String, Object> result = new HashMap();
        result.put("goods",poList);
        result.put("headImgUrl",shopPO.getHeadImg());
        result.put("mainImgUrl",shopPO.getMainImg());
        return result;
    }

    @Override
    public String getShopActivityImgUrl(int shopId){
        return shopDao.queryShopById(shopId).getActivityImg();
    }

    @Override
    public String getTelephone(int shopId){
        return shopDao.queryShopById(shopId).getTelephone();
    }




}
