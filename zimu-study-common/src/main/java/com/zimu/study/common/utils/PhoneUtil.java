package com.zimu.study.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class PhoneUtil {
    
    /**
     * 根据国家代码和手机号 判断手机号是否有效
     *
     * @param phoneNumber
     * @param countryCode
     * @return
     */
//    public static boolean checkPhoneNumber(final String phoneNumber, final String countryCode) {
//
//        final int ccode = StringUtil.toInteger(countryCode);
//        final long phone = StringUtil.toLong(phoneNumber, 0L);
//
//        final PhoneNumber pn = new PhoneNumber();
//        pn.setCountryCode(ccode);
//        pn.setNationalNumber(phone);
//
//        final boolean mark = PhoneNumberUtil.getInstance().isValidNumber(pn);
//        return mark;
//
//    }

    /**
     * 获取六位验证码
     *
     * @return
     */
    public static String getRandNumber() {
        final StringBuffer sb = new StringBuffer();
        int count = 1;
        final Random random = new Random();
        while (true) {
            if (count > 6) {
                break;
            }
            final int num = random.nextInt(10);
            if (count == 1 && num == 0) {
                continue;
            }
            if (sb.indexOf(String.valueOf(num)) == -1) {
                sb.append(num);
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 手机掩码
     * 11位的手机号为 15858313456 -> 158****3456
     * 10位的手机号为 1302345564 -> 130****564
     * 9位的手机号为 912345678 -> 91****678
     * 8位的手机号为 10098787 -> 10****87
     * 7位 1098787 -> 1****87
     * @param phone
     * @return
     */
    public static String mask(String phone) {
        if(StringUtils.isBlank(phone) || phone.length() < 7) {
            return phone;
        }
        int midIdx = phone.length() / 2;
        int leftOffset = midIdx - 2;
        int rightOffset = midIdx + 2;
        String left = phone.substring(0, leftOffset);
        String right = phone.substring(rightOffset, phone.length());
        return left + "****" + right;
    }
}
