package com.app.service.impl;

import com.app.dao.ClassifyDao;
import com.app.model.po.ClassifyPO;
import com.app.service.MainPageService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private ClassifyDao classifyDao;

    public List<ClassifyPO> getClassify(){
        return classifyDao.queryClassify();
    }
}
