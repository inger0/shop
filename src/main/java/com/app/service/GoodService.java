package com.app.service;

import com.common.model.dto.OrderAndGoodDTO;
import com.common.model.po.GiftPO;
import com.common.model.po.GoodPO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujingyang on 2017/4/10.
 */
public interface GoodService {
    GoodPO getGoodById(Integer goodId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int addGoodToCast(int goodId, int userId) throws Exception;

    int deleteGoodFromCart(int orderId, int useId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int  changeOrderCount(int count, int orderId, int userId) throws Exception;



    int commitOrder(ArrayList<Integer> orderIds, int userId);

    int abandonOrder(ArrayList<Integer> orderIds, int userId);

    int payedOrder(ArrayList<Integer> orderIds, int userId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    List<OrderAndGoodDTO> getOrderInfo(int userId, int status) throws IllegalAccessException;

    List<GoodPO> search(String goodName);

    List<GiftPO> getGifts();

    GiftPO getGiftById(Integer giftId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void exchangeGift(Integer giftId, Integer userId) throws Exception;
}
