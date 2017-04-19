package com.app.dao;

import com.app.model.po.ShopPO;
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
}
