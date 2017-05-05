package com.common.dao;

import com.common.model.po.ClassifyPO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Repository
public interface ClassifyDao {
    List<ClassifyPO> queryClassify();

    ClassifyPO queryClassifyById(@Param("classifyId")Integer id);

    int deleteClassify(@Param("id")Integer id);

    int saveClassify(ClassifyPO classifyPO);
}
