package com.app.service;

import com.common.model.po.GoodPO;

import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/10.
 */
public interface ShopService {
    List<GoodPO> getAll(int shopId);

    List<GoodPO> getHotSale(int shopId);

    Map<String,Object> getShopMainPage(int shopId);

    String getShopActivityImgUrl(int shopId);

    String getTelephone(int shopId);
}
