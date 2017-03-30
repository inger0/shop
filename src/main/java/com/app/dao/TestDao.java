package com.app.dao;

import com.app.model.po.TestPO;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
/**
 * Created by yujingyang on 2017/3/27.
 */
@MapperScan
public interface TestDao {
    List<TestPO> testSelect();
}
