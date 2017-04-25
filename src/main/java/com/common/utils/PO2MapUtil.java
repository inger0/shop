package com.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujingyang on 2017/4/15.
 */
public class PO2MapUtil<T> {
    public Map<String,Object> mapper(T object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap();
        for(Field field : fields){
            field.setAccessible(true);
            map.put(field.getName(),field.get(object));
            System.out.println(field.getName());
        }
        return map;
    }
}
