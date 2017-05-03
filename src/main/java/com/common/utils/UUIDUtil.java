package com.common.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public class UUIDUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","").substring(0, 32).toUpperCase();
    }
}