package com.app.service;

import com.common.model.po.AddressPO;
import com.taobao.api.ApiException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/17.
 */
public interface AccountService {
    int saveAddress(AddressPO addressPO);

    List<AddressPO> getAddress(Integer userId);

    AddressPO getAddressById(Integer id);

    int changeUserHeadImg(String filePath, Integer userId);

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void changePayPassword(String originalPassword, String password, Integer userId) throws Exception;

    String getOriginalTelephone(Integer userId);

    void changeTelephone(String nowTelephone, Integer userId) throws Exception;

    void sendMessage(String code, String telephone) throws ApiException;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    String register(String inviterCode, String telephone) throws Exception;

    Integer login(String telephone) throws Exception;


    String getAdminCode();

    AddressPO getDefaultAddress(Integer userId);

    int updateAddress(AddressPO addressPO);




    Integer wechatLogin(String code, String UUID) throws Exception;



    String getCharge(Integer userId, String channel, String price, Integer integer) throws Exception;

    String getUUID(Integer userId);

    void changeUserName(Integer userId, String userName);
}
