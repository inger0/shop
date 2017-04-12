package com.app.dao;

import com.app.model.po.OrderPO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface OrderDao {
    OrderPO queryOrderByUserIdAndStatusAndGoodId(@Param("status")int status,@Param("userId") int userId,@Param("goodId")int goodId);


    //TODO 明天继续 完成OrderDao的编写

    int updateOrderCountById(@Param("orderId")int orderId);

    int saveOrder(OrderPO orderPO);
}
