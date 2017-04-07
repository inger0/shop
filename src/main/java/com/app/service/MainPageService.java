package com.app.service;

import com.app.model.po.ClassifyPO;
import com.app.model.po.GoodPO;

import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/3/30.
 */
public interface MainPageService {
    List<ClassifyPO> getClassify();
    Map<String, List<GoodPO>> getMainPageGood();
}
