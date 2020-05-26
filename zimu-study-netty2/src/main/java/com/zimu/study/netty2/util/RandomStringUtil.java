package com.zimu.study.netty2.util;

import java.util.Random;

/**
 * create on 15/01/2019
 *
 * @author JASON.TAO
 */
public class RandomStringUtil {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
