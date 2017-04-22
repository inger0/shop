package com.app.service.impl;

import com.common.dao.AddressDao;
import com.common.dao.UserDao;
import com.common.model.po.AddressPO;
import com.common.model.po.UserPO;
import com.app.service.AccountService;
import com.common.utils.Constants;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
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
        if (userPO == null || userPO.getPayPassword() == null)
            throw new Exception();
        if (!BCrypt.checkpw(originalPassword, password))
            throw new Exception();
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        if (userDao.updatePayPassword(hashPassword, userId) != 1)
            throw new Exception();
    }

    @Override
    public String getOriginalTelephone(Integer userId) {
        return userDao.queryUserById(userId).getTelephone();
    }

    @Override
    public void changeTelephone(String nowTelephone, Integer userId) throws Exception {
        if (userDao.updateTelephone(nowTelephone, userId) != 1)
            throw new Exception();

    }

    @Override
    public void sendMessage(String code, String telephone) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Constants.MESSAGE_API, "23741172", "45c868d1fef6bc53ca7c5da9e93a747d");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName("高翎实业");
        req.setSmsParamString("{code:'" + code + "'}");
        req.setRecNum(telephone);
        req.setSmsTemplateCode("SMS_62700067");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void register(String inviterCode, String telephone) throws Exception {
        UserPO userPO = userDao.queryUserByInvitationCode(inviterCode);
        if(userPO == null)
            throw new Exception();
        userDao.saveUser(telephone,userPO.getInviterId());

    }

    public int login(String telephone){
        //TODO 返回userPO id
       return 1;
    }



}
