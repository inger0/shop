package com.admin.service;

import com.common.model.po.UserPO;

import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/27.
 */
public interface AdminUserService {
    UserPO getUserByTelephone(String telephone);

    void changeParams(Integer userId, int point, int coin, int diamond);

    void updateToS(Integer userId);

    List<Map<String, Object>> getGoodByName(String goodName) throws IllegalAccessException;

    void updateStatus(int goodId, int status);
}
