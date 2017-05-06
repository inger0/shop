package com.common.model.dto;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/13.
 */
@Data
public class OrderAndGoodDTO {

    Integer orderId;
    Integer goodId;
    Integer goodCount;
    Integer userId;
    Integer usedCoin;
    Integer usedPoint;
    String transportWay;
    Integer addressId;
    Integer amountPrice;
    String name;
    String headImg;
    Integer classifyId;
    String classifyName;
    Integer shopId;
    String shopName;
    Integer originPrice;
    Integer stock;
    Integer salesVolume;
    Integer minPrice;
    Integer newPrice;
    Integer maxPoint;
    Integer minPoint;
    Integer maxCoin;
    Integer minCoin;
    String orderNum;
    Integer orderStatus;
    String describeStatus;
}
