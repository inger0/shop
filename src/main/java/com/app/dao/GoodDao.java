package com.app.dao;

import com.app.model.po.GoodPO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/6.
 */
@Repository
public interface GoodDao {
    List<GoodPO> queryGoodsByStatus(@Param("status") int status);

    GoodPO queryGoodById(@Param("id") int id);

    List<GoodPO> queryGoodsByStatusAndClassifyId(@Param("classifyId")int classifyId,@Param("status") int status);

    List<GoodPO> queryGoodsByClassifyId(int classifyId);
}
