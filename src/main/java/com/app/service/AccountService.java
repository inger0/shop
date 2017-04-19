package com.app.service;

import com.app.model.po.AddressPO;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/17.
 */
public interface AccountService {
    int saveAddress(AddressPO addressPO);

    List<AddressPO> getAddress(Integer userId);

    AddressPO getAddressById(Integer id);

    int changeUserHeadImg(String filePath, Integer userId);
}
