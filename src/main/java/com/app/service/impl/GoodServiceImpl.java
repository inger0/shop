package com.app.service.impl;

import com.app.dao.GoodDao;
import com.app.dao.OrderDao;
import com.app.model.po.GoodPO;
import com.app.model.po.OrderPO;
import com.app.service.GoodService;
import com.app.utils.Constants;
import com.app.utils.enums.OrderStatus;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public GoodPO getGoodById(Integer goodId) {
        if (goodId != null)
            return goodDao.queryGoodById(goodId);
        else
            return null;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addGoodToCast(int goodId, int userId) throws Exception {

        OrderPO orderPO = orderDao.queryOrderByUserIdAndStatusAndGoodId(OrderStatus.GOOD_IN_CART, userId, goodId);
        if (orderPO == null) {
            OrderPO order4save = new OrderPO();
            order4save.setGoodId(goodId);
            order4save.setGoodCount(1);
            order4save.setUserId(userId);
            order4save.setStatus(OrderStatus.GOOD_IN_CART);
            orderDao.saveOrder(order4save);
            return Constants.SUCCESS;
        } else {
            orderDao.updateOrderCountById(orderPO.getId());
            return Constants.SUCCESS;
        }
    }
}
