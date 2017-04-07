package com.app.dao;

import com.app.model.po.ClassifyPO;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Repository
public interface ClassifyDao {
    List<ClassifyPO> queryClassify();
}
