package com.app.model.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/6.
 */
@Data
public class GoodPO {
    Integer id;
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
    String headImg;
    String contentImg;
    String content;
    Integer status;

}
