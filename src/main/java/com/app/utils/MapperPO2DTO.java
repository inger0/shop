package com.app.utils;

import com.app.model.dto.OrderAndGoodDTO;
import com.app.model.po.GoodPO;
import com.app.model.po.OrderPO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/13.
 */
public class MapperPO2DTO<T, E, M> {

    public T mapper(T o1, E o2, M o3) throws IllegalAccessException {
        Class class4E = o2.getClass();
        Class class4M = o3.getClass();
        Class class4T = o1.getClass();
        Field[] fields4E = class4E.getDeclaredFields();
        Field[] fields4M = class4M.getDeclaredFields();
        Field[] fields4T = class4T.getDeclaredFields();
        Map<String, Object> map = new HashMap();

        for (Field field : fields4E) {
            field.setAccessible(true);
            map.put(field.getName(),field.get(o2));
        }
        for (Field field : fields4M) {
            field.setAccessible(true);
            map.put(field.getName(),field.get(o3));
        }
        for(Field field : fields4T){
            field.setAccessible(true);
            field.set(o1,map.get(field.getName()));
        }
        return o1;
    }

    public static void main(String[] args) throws IllegalAccessException {
        OrderAndGoodDTO orderAndGoodDTO = new OrderAndGoodDTO();
        OrderPO orderPO = new OrderPO();
        GoodPO goodPO = new GoodPO();
        orderPO.setUsedPoint(120);
        orderPO.setGoodCount(500);
        goodPO.setMaxPoint(500);
        MapperPO2DTO<OrderAndGoodDTO,OrderPO,GoodPO> mapper =  new MapperPO2DTO();
        mapper.mapper(orderAndGoodDTO,orderPO,goodPO);
        System.out.println("123");
    }
}
