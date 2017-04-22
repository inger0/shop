package com.app.service.impl;

import com.common.dao.GoodDao;
import com.common.dao.ShopDao;
import com.common.model.po.GoodPO;
import com.common.model.po.ShopPO;
import com.app.service.ClassifyService;
import java.util.List;

import com.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    GoodDao goodDao;

    @Autowired
    ShopDao shopDao;

    @Override
    public List<GoodPO> getGoods(int classifyId){
        if(classifyId == 0){
            ShopPO shopPO = shopDao.queryShopByStatus(Constants.SHOP_OUTSTANDING).get(0);
            List<GoodPO> poList = goodDao.queryGoodsByShopId(shopPO.getId());
            return poList;
        }
        return goodDao.queryGoodsByClassifyId(classifyId);
    }
}
