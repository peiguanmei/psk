package com.p.psk.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 第一次md5加密,用于网络传输
     *
     * @param password
     * @return
     */
    public static String inputPassToFormPass(String password) {
        //避免在网络传输被截取
        String str = "" + salt.charAt(0) + password + salt.charAt(6);
        return md5(str);
    }

    /**
     * 第二次md5加密,用于存储到数据库,此处可以传入非固定的salt
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt + formPass;
        return md5(str);
    }

    public static String inputPassToDBPass(String input, String salt) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(formPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9",salt));
        System.out.println(inputPassToDBPass("123455",salt));
    }
}
