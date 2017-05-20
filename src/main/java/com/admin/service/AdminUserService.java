package com.admin.service;

import com.common.model.po.GoodPO;
import com.common.model.po.UserPO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    int updateStatus(int goodId, int status);

    List<Map<String, Object>> getGoodByStatus(int status) throws IllegalAccessException;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void deleteClassify(Integer classifyId);

    void addClassify(String classifyName, String classifyImg);

    void setShop(String path11, String s, String path1, String path2, Integer userId);

    void  saveGood(GoodPO goodPO, Integer userId);

    Map<String, Object> getUserTreeMiddle(Integer userId);

    List<Map<String, Object>> queryShopGoodByName(String goodName, Integer userId) throws IllegalAccessException;

    List<Map<String, Object>> queryShopGoodByStatus(Integer userId, Integer status) throws IllegalAccessException;

    String getTestContent();
}
