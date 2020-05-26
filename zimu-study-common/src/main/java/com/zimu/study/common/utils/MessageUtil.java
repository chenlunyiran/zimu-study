package com.zimu.study.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname MessageUtil
 * @Description 敏感信息转化. 先removeLink,然后convertToNormal
 * @Date 2020/3/29 18:17
 * @Author jianhua.wang
 */
public class MessageUtil {

    /**
     * 校验汉字
     */
    private static final Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 短信敏感词
     */
    private static final List<String> sensitiveWords = Arrays.asList("爆仓", "平仓", "违约金", "逾期", "夺宝");

    /**
     * 替换短信敏感词
     *
     * @param msg
     * @return
     */
    public static String convertToNormal(String msg) {
        msg = msg.replace("【HCOIN】", "");
        msg = msg.replace(" (GTM 08:00)", "");
        //如果为中文模板且包含敏感词
        boolean containChinese = isContainChinese(msg);
        if (containChinese) {
            for (String a : sensitiveWords) {
                if (msg.contains(a)) {
                    String c = String.valueOf(a.charAt(0));
                    String replaceFirst = a.replace(c, c + " ");
                    msg = msg.replace(a, replaceFirst);
                }
            }
            return msg;
        }

        //如果为英文文模板且包含中文标点
        msg = PunctuationUtil.chinesePunctuationToEnglish(msg);
        return msg;
    }

    /**
     * 去除英文模板中的链接
     *
     * @param msg
     * @return
     */
    public static String removeLink(String msg) {
        boolean containChinese = isContainChinese(msg);
        if (!containChinese) {
            msg = msg.replace("welcome to HCOIN.COM！", "");
        }
        return msg;
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = convertToNormal("158***3427在2020-03-30 18:43:46 (GTM 08:00)登录HCoin，请确认是否为您本人操作。");
        System.out.println(str);
        System.out.println(str.length());
    }
}
