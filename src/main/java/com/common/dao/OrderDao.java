package com.common.dao;

import com.common.model.dto.OrderAndGoodDTO;
import com.common.model.po.OrderPO;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Repository
public interface OrderDao {
    OrderPO queryOrderByUserIdAndStatusAndGoodId(@Param("status")int status, @Param("userId") int userId, @Param("goodId")int goodId);

    int updateOrderCountById(@Param("orderId")Integer orderId,@Param("count")Integer count);

    int  deleteOrderById(@Param("orderId")Integer orderId,@Param("userId")Integer userId);

    int saveOrder(OrderPO orderPO);

    OrderPO queryOrderById(@Param("orderId")Integer orderId,@Param("userId")Integer userId);

    int batchUpdateOrder(@Param("orderIds")List<Integer> orderIds, @Param("status")Integer status,@Param("userId")Integer userId );

    int updatePO(OrderAndGoodDTO orderPO);

    List<OrderPO> queryOrderByOrderNum(@Param("orderNum")String orderNum);

    List<OrderPO> queryOrderByUserId(@Param("userId")int userId);

    int updatePO2(OrderPO orderPO);

}
