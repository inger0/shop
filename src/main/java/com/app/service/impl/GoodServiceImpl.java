package com.app.service.impl;

import com.common.dao.*;
import com.common.model.dto.OrderAndGoodDTO;
import com.common.model.po.GiftPO;
import com.common.model.po.GoodPO;
import com.common.model.po.OrderPO;
import com.app.service.GoodService;
import com.common.model.po.UserPO;
import com.common.utils.MapperPO2DTO;
import com.common.utils.enums.OrderStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Autowired
    private GiftDao giftDao;

    @Autowired
    private UserDao userDao;


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
            orderDao.updateOrderCountById(orderPO.getId(),orderPO.getGoodCount()+1);
            return orderPO.getGoodCount()+1;

        }
    }

    @Override
    public int deleteGoodFromCart(int orderId, int userId) {
        return orderDao.deleteOrderById(orderId, userId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int changeOrderCount(int count, int orderId, int userId) throws Exception {
        OrderPO orderPO = orderDao.queryOrderById(orderId, userId);

        GoodPO goodPO = goodDao.queryGoodById(orderPO.getGoodId());
        if (orderPO == null || count <= 0 || goodPO == null) {//防止前端提交错误数据
            throw new Exception();
        }
        //TODO 注意库存
        orderDao.updateOrderCountById(orderPO.getId(),count);
        return count;
    }

    @Override
    public int commitOrder(List<Integer> orderIds, int userId) {
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
                switch (orderPO.getStatus()){
                    case OrderStatus.GOOD_AFTER_RECIEVED:
                        orderAndGoodDTO.setDescribeStatus("交易成功");
                    default:
                        orderAndGoodDTO.setDescribeStatus("交易中");
                }
                results.add(orderAndGoodDTO);
            }
        }
        return results;

    }

    @Override
    public List<GoodPO> search(String goodName){
        char[] chars = goodName.toCharArray();
        String regexp = ".*";
        for(char c : chars){
            regexp = regexp+c+".*";
        }
        List<GoodPO> goodPOs = goodDao.queryGoodRegexp(regexp);
        return goodPOs;
    }

    @Override
    public List<GiftPO> getGifts(){
        return giftDao.queryGifts();
    }

    @Override
    public GiftPO getGiftById(Integer giftId){
        return giftDao.queryGiftById(giftId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void exchangeGift(Integer giftId, Integer userId) throws Exception {
        UserPO userPO = userDao.queryUserById(userId);
        GiftPO giftPO = giftDao.queryGiftById(giftId);
        if(userPO.getDiamond() - giftPO.getCost() <0)
            throw new Exception();
        userPO.setCoin(userPO.getCoin()+giftPO.getCoinValue());
        userPO.setPoint(userPO.getPoint()+giftPO.getPointValue());
        userPO.setDiamond(userPO.getDiamond()-giftPO.getCost());
        userDao.updatePO(userPO);
    }

    @Override
    public String confirmOrders(List<OrderAndGoodDTO> confirmOrders) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(String.valueOf(System.currentTimeMillis() / 1000).getBytes());

        byte b[] = messageDigest.digest();

        int i;

        StringBuffer buf = new StringBuffer("DD");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        String orderNo = buf.toString().substring(8, 24);

        for(OrderAndGoodDTO orderPO : confirmOrders){
            orderPO.setOrderNum(orderNo);
            orderDao.updatePO(orderPO);
        }
        return orderNo;
    }

    @Override
    public void paySucceed(String orderNum){
        List<OrderPO> list = orderDao.queryOrderByOrderNum(orderNum);
        for(OrderPO orderPO : list){
            orderPO.setStatus(OrderStatus.GOOD_AFTER_PAYED);
            orderDao.updatePO2(orderPO);
        }

    }

}
