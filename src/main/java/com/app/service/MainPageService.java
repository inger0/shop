package com.app.service;

import com.app.model.po.ClassifyPO;

import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/3/30.
 */
public interface MainPageService {
    List<ClassifyPO> getClassify();
    Map<String, Object> getMainPageGood();

    Map<String, Object> getUserInfo(Integer userId);
}
