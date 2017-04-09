package com.app.service.impl;

import com.app.dao.GoodDao;
import com.app.model.po.GoodPO;
import com.app.service.ClassifyService;
import java.util.List;

import com.app.utils.enums.GoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    GoodDao goodDao;

    @Override
    public List<GoodPO> getGoods(int classifyId){
        return goodDao.queryGoodsByClassifyId(classifyId);
    }
}
