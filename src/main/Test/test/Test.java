package test;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yujingyang on 2017/4/26.
 */
public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String code = Long.toHexString(System.currentTimeMillis()/1000);
        System.out.println(code);
    }
}
