package com.common.dao;

import com.common.model.po.ShopPO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Repository
public interface ShopDao {
    List<ShopPO> queryShopByStatus(@Param("status")int status);

    ShopPO queryShopById(int shopId);

    ShopPO queryShopByUserId(@Param("userId")Integer userId);

    int savePO(ShopPO shopPO);

    int updatePO(ShopPO shopPO);
}
