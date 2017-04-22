package com.common.dao;

import com.common.model.po.TestPO;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by yujingyang on 2017/3/27.
 */
@Repository
public interface TestDao {
    List<TestPO> testSelect();
}
