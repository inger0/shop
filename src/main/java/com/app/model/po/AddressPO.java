package com.app.model.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/10.
 */
@Data
public class AddressPO {
    Integer id;
    Integer userId;
    String reciever;
    String address;
    String recieverTelephone;
    String mailNumber;
    Integer status;
}
