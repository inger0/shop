package com.app.service.impl;

import com.app.dao.AddressDao;
import com.app.model.po.AddressPO;
import com.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/17.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AddressDao addressDao;

    @Override
    public int saveAddress(AddressPO addressPO){
        return  addressDao.saveAddress(addressPO);
    }

    @Override
    public List<AddressPO> getAddress(Integer userId){
        return addressDao.queryAddressByUserId(userId);
    }

    @Override
    public AddressPO getAddressById(Integer id){
        return addressDao.queryAddressById(id);
    }

}
