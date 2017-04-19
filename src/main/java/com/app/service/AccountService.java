package com.app.service;

import com.app.model.po.AddressPO;
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
}
