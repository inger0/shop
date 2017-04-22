package com.common.model.po;

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
    Integer originPrice;//原价
    Integer stock;
    Integer salesVolume;
    Integer minPrice;//最低实付
    Integer newPrice;//唐僧价
    Integer maxPoint;
    Integer minPoint;
    Integer maxCoin;
    Integer minCoin;
    String headImg;
    String contentImg;
    String content;
    Integer status;

}
