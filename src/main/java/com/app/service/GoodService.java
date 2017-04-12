package com.app.service;

import com.app.model.po.GoodPO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yujingyang on 2017/4/10.
 */
public interface GoodService {
    GoodPO getGoodById(Integer goodId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int addGoodToCast(int goodId, int userId) throws Exception;
}
