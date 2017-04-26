package com.common.dao;

import com.common.model.po.GoodPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/6.
 */
@Repository
public interface GoodDao {
    List<GoodPO> queryGoodsByStatus(@Param("status") int status);

    GoodPO queryGoodById(@Param("id") int id);

    List<GoodPO> queryGoodsByShopName(@Param("goodName")String goodName);

    List<GoodPO> queryGoodsByStatusAndClassifyId(@Param("classifyId")int classifyId,@Param("status") int status);

    List<GoodPO> queryGoodRegexp(@Param("regexp")String regexp);

    List<GoodPO> queryGoodsByClassifyId(int classifyId);

    List<GoodPO> queryGoodsByShopId(int shopId);

    List<GoodPO> queryGoodsByShopIdAndStatus(@Param("shopId") int shopId,@Param("status") int status);

    int  updatePO(GoodPO goodPO);
}
