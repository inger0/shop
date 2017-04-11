package com.app.service.impl;

import com.app.dao.ClassifyDao;
import com.app.dao.GoodDao;
import com.app.dao.ShopDao;
import com.app.dao.UserDao;
import com.app.model.po.ClassifyPO;
import com.app.model.po.GoodPO;
import com.app.model.po.ShopPO;
import com.app.model.po.UserPO;
import com.app.service.MainPageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.utils.Constants;
import com.app.utils.enums.GoodStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by yujingyang on 2017/3/30.
 */
@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private ClassifyDao classifyDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    public List<ClassifyPO> getClassify(){
        return classifyDao.queryClassify();
    }

    @Override
    public Map<String, List<GoodPO>> getMainPageGood() {
        List<GoodPO> goodPOs= goodDao.queryGoodsByStatus(GoodStatus.MAIN_PAGE_GOOD);
        //添加优品牌
        ShopPO shopPO = shopDao.queryShopByStatus(Constants.SHOP_OUTSTANDING).get(0);

        //按分类添加热门商品
        Map<String,List<GoodPO>> result = new HashMap();
        result.put("outstanding_shop_goods",new ArrayList<GoodPO>());
        for(GoodPO po : goodPOs){
            Integer goodClassify = po.getClassifyId();
            if(result.get(goodClassify) == null){
                result.put(String.valueOf(goodClassify),new ArrayList<GoodPO>());
            }
            //TODO 优品牌商品是否有特殊状态
            if(po.getShopId() == shopPO.getId()){
                result.get("outstanding_shop_goods").add(po);
            }
            List<GoodPO> poList = result.get(goodClassify);
            poList.add(po);
        }
        return result;
    }

    @Override
    public Map<String, Object> getUserInfo(Integer userId) {
        Map<String,Object> result = new HashMap();
        if(userId == null){
            return null;
        }
        UserPO userPO = userDao.queryUserById(userId);
        //TODO 以后再写比率 这里先使用暂时的比率
        if(userPO==null)
            return null;
        result.put("coin",userPO.getCoin());
        result.put("point",userPO.getPoint());
        result.put("price",Math.floor(userPO.getPoint()*0.3+userPO.getCoin()*0.4));
        return result;
    }


}
