package com.app.service;

import com.common.model.po.GoodPO;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/10.
 */
public interface ClassifyService {

    List<GoodPO> getGoods(int classifyId);
}
