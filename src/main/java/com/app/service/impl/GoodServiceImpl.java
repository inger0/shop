package com.app.service.impl;

import com.app.dao.GoodDao;
import com.app.dao.OrderDao;
import com.app.dao.ShopDao;
import com.app.model.dto.OrderAndGoodDTO;
import com.app.model.po.GoodPO;
import com.app.model.po.OrderPO;
import com.app.service.GoodService;
import com.app.utils.Constants;
import com.app.utils.MapperPO2DTO;
import com.app.utils.enums.OrderStatus;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    private ShopDao shopDao;


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
            return order4save.getGoodCount();
        } else {
            orderDao.updateOrderCountById(orderPO.getId());
            return orderPO.getGoodCount();

        }
    }

    @Override
    public int deleteGoodFromCart(int orderId, int userId) {
        return orderDao.deleteOrderById(orderId, userId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int changeOrderCount(int count, int orderId, int userId) {
        OrderPO orderPO = orderDao.queryOrderById(orderId, userId);
        GoodPO goodPO = goodDao.queryGoodById(orderPO.getGoodId());
        if (orderPO == null || count <= 0) {//防止前端提交错误数据
            return -1;
        }
        orderPO.setGoodCount(count);
        //TODO 注意库存
        orderDao.saveOrder(orderPO);
        return orderPO.getGoodCount();
    }

    @Override
    public int commitOrder(ArrayList<Integer> orderIds, int userId) {
        return orderDao.batchUpdateOrder(orderIds, OrderStatus.GOOD_IS_SETTLED, userId);
    }

    @Override
    public int abandonOrder(ArrayList<Integer> orderIds, int userId) {
        return orderDao.batchUpdateOrder(orderIds, OrderStatus.GOOD_WAITFOR_PAY, userId);
    }

    @Override
    public int payedOrder(ArrayList<Integer> orderIds, int userId) {
        return orderDao.batchUpdateOrder(orderIds, OrderStatus.GOOD_AFTER_PAYED, userId);
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<OrderAndGoodDTO> getOrderInfo(int userId, int status) throws IllegalAccessException {

        List<OrderPO> orderPOs = orderDao.queryOrderByUserId(userId);
        List<OrderAndGoodDTO> results = new ArrayList();

        MapperPO2DTO<OrderAndGoodDTO , OrderPO, GoodPO> mapper = new MapperPO2DTO();

        for(OrderPO orderPO : orderPOs){
            if(orderPO.getStatus() == status || status == -1){
                GoodPO goodPO = goodDao.queryGoodById(orderPO.getGoodId());
                OrderAndGoodDTO orderAndGoodDTO = mapper.mapper(new OrderAndGoodDTO(),orderPO,goodPO);
                orderAndGoodDTO.setShopName(shopDao.queryShopById(goodPO.getShopId()).getName());
                orderAndGoodDTO.setOrderId(orderPO.getId());
                results.add(orderAndGoodDTO);
            }
        }
        return results;

    }
}
