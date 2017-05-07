package com.app.service.impl;

import com.common.dao.AddressDao;
import com.common.dao.OrderDao;
import com.common.dao.UserDao;
import com.common.model.po.AddressPO;
import com.common.model.po.UserPO;
import com.app.service.AccountService;
import com.common.utils.Constants;
import com.common.utils.UUIDUtil;
import com.common.utils.enums.UserStatus;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/17.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AddressDao addressDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveAddress(AddressPO addressPO) {
        if (addressPO.getStatus() == Constants.ADDRESS_IS_DEFAULT) {
            AddressPO tmp = addressDao.queryAddressByStatus(Constants.ADDRESS_IS_DEFAULT, addressPO.getUserId());
            if (tmp != null)
                tmp.setStatus(Constants.ADDRESS_NOT_DEFAULT);
            addressDao.updatePO(tmp);
        }
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
        return userDao.updateHeadImgById("/userImgs/" + filePath, userId);
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
        System.out.println(rsp);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String register(String inviterCode, String telephone) throws Exception {
        UserPO userPO = userDao.queryUserByInvitationCode(inviterCode);
        UserPO po = userDao.queryUserByTelephone(telephone);
        if (userPO == null || po != null)
            throw new Exception();
        UserPO savePO = new UserPO();
        savePO.setInviterId(userPO.getId());
        savePO.setTelephone(telephone);
        savePO.setUserName("唐僧用户" + telephone.substring(telephone.length() - 4));
        savePO.setUUID(UUIDUtil.generateUUID());
        savePO.setHeadImg("/imgs/user.jpg");
        String code = Long.toHexString(System.currentTimeMillis()/1000);
        savePO.setInvitationCode(code);
        userDao.savePO(savePO);
        return savePO.getUUID();
    }

    @Override
    public Integer login(String telephone) throws Exception {
        UserPO userPO = userDao.queryUserByTelephone(telephone);
        if (userPO == null)
            throw new Exception();
        return userPO.getId();
    }

    @Override
    public String getAdminCode() {
        UserPO userPO = userDao.queryUserByStatus(1);
        return userDao.queryUserByStatus(UserStatus.USER_ADMIN).getInvitationCode();
    }

    @Override
    public AddressPO getDefaultAddress(Integer userId) {
        AddressPO addressPO = addressDao.queryAddressByStatus(Constants.ADDRESS_IS_DEFAULT, userId);
        return addressDao.queryAddressByStatus(Constants.ADDRESS_IS_DEFAULT, userId);
    }

    @Override
    public int updateAddress(AddressPO addressPO) {
        if (addressPO.getStatus() == Constants.ADDRESS_IS_DEFAULT) {
            AddressPO tmp = addressDao.queryAddressByStatus(Constants.ADDRESS_IS_DEFAULT, addressPO.getUserId());
            if (tmp != null)
                tmp.setStatus(Constants.ADDRESS_NOT_DEFAULT);
            addressDao.updatePO(tmp);
        }
        return addressDao.updatePO(addressPO);
    }


    @Override
    public Integer wechatLogin(String code, String UUID) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.APP_ID + "&secret=" + Constants.APP_SECRET + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse response = null;
        //3.执行get请求并返回结果
        response = httpclient.execute(httpget);
        String result;
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity);
            JSONObject json = new JSONObject(result); //Convert String to JSON Object
            String unionId = json.getString("openid");
            UserPO userPO = userDao.queryUserByLoginCode(unionId);
            if (userPO == null) {
                UserPO tmp = userDao.queryUserByUUID(UUID);
                if (tmp == null)
                    throw new Exception();
                tmp.setLoginCode(unionId);
                userDao.updatePO(tmp);
                return tmp.getId();
            }
            return userPO.getId();
        } else {
            throw new Exception();
        }
    }

    @Override
    public String getCharge(Integer userId, String channel, String orderNum, Integer price) throws Exception {
        UserPO userPO = userDao.queryUserById(userId);
        if (userPO == null)
            throw new Exception();
        else if (userPO.getLoginCode() == null) {
            return "error";
        } else {

            return createChargeWithOpenid(userPO.getLoginCode(), userPO.getTelephone(), channel, orderNum, price).toString();
        }
    }


    private Charge createChargeWithOpenid(String openid, String telephone, String channel, String orderNum, int price) throws NoSuchAlgorithmException, RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = "sk_test_Hqv1iTf1WzfPWTCyPCmj5SSC";
        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", price);//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", "TS_SHOP");
        chargeMap.put("body", "唐僧商城商品支付");

        chargeMap.put("order_no", orderNum);// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
        chargeMap.put("channel", channel);// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
        chargeMap.put("client_ip", "127.0.0.1"); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", "app_D4i1SODuXHKCDCWj");
        chargeMap.put("app", app);

        Map<String, Object> extra = new HashMap<String, Object>();
        switch (channel) {
            case "wx_pub":
                extra.put("open_id", openid);// 用户在商户微信公众号下的唯一标识，获取方式可参考 WxPubOAuthExample.java
                break;
            case "alipay_wap":
                extra.put("success_url", "http://www.tangseng.shop/account/paySucceed?orderNum=" + orderNum);
                extra.put("cancel_url", "http://www.tangseng.shop/payment/failed");
                break;
            case "upacp_wap":
                extra.put("success_url", "http://www.tangseng.shop/account/paySucceed?orderNum=" + orderNum);
                break;
        }
        chargeMap.put("extra", extra);
//        try {
//            //发起交易请求
//            charge = Charge.create(chargeMap);
//            // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
//            String chargeString = charge.toString();
//
//        } catch (PingppException e) {
//            e.printStackTrace();
//        }
        return Charge.create(chargeMap);
    }

    @Override
    public String getUUID(Integer userId) {
        UserPO userPO = userDao.queryUserById(userId);
        return userPO.getUUID();
    }

    @Override
    public void changeUserName(Integer userId, String userName) {
        UserPO userPO = userDao.queryUserById(userId);
        userPO.setUserName(userName);
        userDao.updatePO(userPO);
    }

    @Override
    public String getInvitationCode(Integer userId){
        UserPO userPO = userDao.queryUserById(userId);
        return userPO.getInvitationCode();
    }

}
