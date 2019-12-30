package com.zimu.study.common.utils;

import org.apache.commons.lang3.RandomUtils;

/**
 * 分享码工具类
 */
public class ShareCodeUtil {

    private static final String vars []  ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


    public static String createShareCode(Integer length){
        String shareCode = "";
        while (length >0){
            int i = RandomUtils.nextInt(0, 62);
            shareCode = shareCode+vars[i];
            length --;
        }
        return shareCode;
    }





}
