package com.common.model.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/22.
 */
@Data
public class GiftPO {
    Integer id;
    String thumbnail;
    String name;
    String headImg;
    String content;
    Integer cost;
    Integer pointValue;
    Integer coinValue;
    String telephone;
}
