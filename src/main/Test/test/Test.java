package test;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yujingyang on 2017/4/26.
 */
public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        String orderNo =messageDigest.digest(String.valueOf(System.currentTimeMillis() / 1000).getBytes())+"";
        System.out.println(orderNo);
    }
}
