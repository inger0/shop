package com.app.service.impl;

import com.common.dao.TestDao;
import com.common.model.po.TestPO;
import com.app.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yujingyang on 2017/3/27.
 */
@Service
public class TestServiceImpl implements TestService {


    @Autowired
    TestDao testDao;

    @Override
    public String getTestContent() {
        List<TestPO> result = testDao.testSelect();
        for(TestPO tmp : result){
            return tmp.getTestCol2();
        }
        return null;
    }
}
