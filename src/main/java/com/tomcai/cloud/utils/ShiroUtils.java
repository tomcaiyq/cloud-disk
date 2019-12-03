package com.tomcai.cloud.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtils {
    private static final int TIMES = 1024;

    public static String encryption(String salt, String password) {
        return new Md5Hash(password, ByteSource.Util.bytes(salt), TIMES).toHex();
    }
}
