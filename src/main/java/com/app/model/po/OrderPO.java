package com.app.model.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Data
public class OrderPO {
    Integer id;
    Integer goodId;
    Integer goodCount;
    Integer userId;
    Integer usedCoin;
    Integer usedPoint;
    String transportWay;
    Integer addressId;
    Integer amountPrice;
    Integer status;
}
