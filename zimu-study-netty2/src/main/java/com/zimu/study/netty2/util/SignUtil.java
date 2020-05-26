package com.zimu.study.netty2.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * create on 15/01/2019
 *
 * @author JASON.TAO
 */
@Slf4j
public class SignUtil {
    public static String getDefaultSin (String secret, String base64Str) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-256");

        md.update(secret.getBytes());
        byte[] digest = md.digest(base64Str.getBytes());
        return Base64.getEncoder().encodeToString(digest);
    }

    /**
     * 对字符串md5加密
     *
     * @param value
     * @return
     */
    public static String getMD5(String value) {
        String result = null;
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
            md5.update((value).getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException error){
            error.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }
}
