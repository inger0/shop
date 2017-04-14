package com.app.dao;

import com.app.model.po.OrderPO;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface OrderDao {
    OrderPO queryOrderByUserIdAndStatusAndGoodId(@Param("status")int status,@Param("userId") int userId,@Param("goodId")int goodId);

    int updateOrderCountById(@Param("orderId")Integer orderId);

    int  deleteOrderById(@Param("orderId")Integer orderId,@Param("userId")Integer userId);

    int saveOrder(OrderPO orderPO);

    OrderPO queryOrderById(@Param("orderId")Integer orderId,@Param("userId")Integer userId);

    int batchUpdateOrder(@Param("orderIds")ArrayList<Integer> orderIds, @Param("status")Integer status,@Param("userId")Integer userId );

    List<OrderPO> queryOrderByUserId(@Param("userId")int userId);
}
