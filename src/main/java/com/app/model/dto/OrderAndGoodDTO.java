package com.app.model.dto;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/13.
 */
@Data
public class OrderAndGoodDTO {

    Integer goodId;
    Integer goodCount;
    Integer userId;
    Integer usedCoin;
    Integer usedPoint;
    String transportWay;
    Integer addressId;
    Integer amountPrice;
    String name;
    Integer classifyId;
    String classifyName;
    Integer shopId;
    Integer originPrice;
    Integer stock;
    Integer salesVolume;
    Integer minPrice;
    Integer newPrice;
    Integer maxPoint;
    Integer minPoint;
    Integer maxCoin;
    Integer minCoin;
    String describeStatus;
}
