package com.zimu.study.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Classname PunctuationUtil
 * @Description 中文全角标点符号转换为英文半角标点符号
 * @Date 2020/3/29 19:48
 * @Author jianhua.wang
 */
public class PunctuationUtil {

    //中文标点符号
    final static String[] chinesePunctuation = {"！", "，", "。", "；", "《", "》", "（", "）", "？",
            "｛", "｝", "“", "：", "【", "】", "”", "‘", "’"};


    //英文标点符号
    final static String[] englishPunctuation = {"!", ",",
            ".", ";", "<", ">", "(", ")", "?", "{", "}", "\"",
            ":", "{", "}", "\"", "\'", "\'"};

    /**
     * 中文标点符号转英文字标点符号
     *
     * @param str 原字符串
     * @return str 新字符串
     */
    public static final String chinesePunctuationToEnglish(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        //去除空格
        str = str.replaceAll("\\s+", "");
        for (int i = 0; i < chinesePunctuation.length; i++) {
            str = str.replaceAll(chinesePunctuation[i], englishPunctuation[i]);
        }
        return str;
    }
}
