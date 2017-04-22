package com.common.dao;

import com.common.model.po.GiftPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by yujingyang on 2017/4/22.
 */
@Repository
public interface GiftDao {
    List<GiftPO> queryGifts();

    GiftPO queryGiftById(@Param("id")int id);
}
