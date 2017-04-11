package com.app.service.impl;

import com.app.dao.GoodDao;
import com.app.dao.OrderDao;
import com.app.model.po.GoodPO;
import com.app.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public GoodPO getGoodById(Integer goodId) {
        if (goodId != null)
            return goodDao.queryGoodById(goodId);
        else
            return null;
    }

    public int addGoodToCast(int goodId){

        return -1;
    }
}
