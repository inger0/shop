package com.app.utils.enums;

/**
 * Created by yujingyang on 2017/4/10.
 */
public interface OrderStatus {
    int GOOD_IN_CART = -1;//购物车中
    int GOOD_IS_SETTLED = 1;//已结算待付款
    int GOOD_AFTER_PAYED = 2;//已付款待发货
    int GOOD_AFTER_SENT = 3;
    int GOOD_AFTER_RECIEVED = 4;

}
