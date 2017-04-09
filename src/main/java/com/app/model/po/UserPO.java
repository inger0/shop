package com.app.model.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/4/7.
 */
@Data
public class UserPO {
    Integer id;
    String loginCode;
    String payPassword;
    String telephone;
    int coin;
    int point;
    int diamond;
    int status;
    int inviterId;
    String invitationCode;

}
