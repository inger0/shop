package com.common.utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by justin on 7/23/15.
 */
public class WebUtil {
    public static ResponseEntity<Map<String, Object>> result(Object result) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("code", 200);
        json.put("msg", result);

        return new ResponseEntity<Map<String, Object>>(json, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> error(Object msg) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("code", 500);
        json.put("msg", msg);

        return new ResponseEntity<Map<String, Object>>(json, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> error(int code, Object msg) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("code", code);
        json.put("msg", msg);

        return new ResponseEntity<Map<String, Object>>(json, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> error(int code, Object msg, HttpStatus status) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("code", code);
        json.put("msg", msg);

        return new ResponseEntity<Map<String, Object>>(json, status);
    }
}