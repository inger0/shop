package com.app.service.impl;

import com.app.dao.AddressDao;
import com.app.dao.UserDao;
import com.app.model.po.AddressPO;
import com.app.model.po.UserPO;
import com.app.service.AccountService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yujingyang on 2017/4/17.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AddressDao addressDao;
    @Autowired
    UserDao userDao;

    @Override
    public int saveAddress(AddressPO addressPO) {
        return addressDao.saveAddress(addressPO);
    }

    @Override
    public List<AddressPO> getAddress(Integer userId) {
        return addressDao.queryAddressByUserId(userId);
    }

    @Override
    public AddressPO getAddressById(Integer id) {
        return addressDao.queryAddressById(id);
    }

    @Override
    public int changeUserHeadImg(String filePath, Integer userId) {
        return userDao.updateHeadImgById(filePath, userId);
    }



    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePayPassword(String originalPassword, String password, Integer userId) throws Exception {
        UserPO userPO = userDao.queryUserById(userId);
        if(userPO == null || userPO.getPayPassword() == null)
            throw new Exception();
        if(!BCrypt.checkpw(originalPassword,password))
            throw new Exception();
        String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        if(userDao.updatePayPassword(hashPassword,userId) != 1)
            throw new Exception();
    }

    @Override
    public String getOriginalTelephone(Integer userId){
        return userDao.queryUserById(userId).getTelephone();
    }

    @Override
    public void changeTelephone(String nowTelephone,Integer userId) throws Exception {
        if(userDao.updateTelephone(nowTelephone,userId) != 1)
            throw new Exception();

    }







}
