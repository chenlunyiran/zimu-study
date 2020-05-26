package com.zimu.study.common;

import com.zimu.study.common.utils.HttpUtils;
import com.zimu.study.common.utils.XmlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SMSYunZhiXun
 * @Description TODO
 * @Date 2020/4/14 10:36
 * @Author jianhua.wang
 */
public class SMSYunZhiXun {

    public static void main(String[] args) {
        String url = "http://140.143.152.178:8888/statusApi.aspx";
        Map params = new HashMap();
        params.put("action", "query");
//        params.put("sendTime", "");
//        params.put("extno", "");
        params.put("userid", "533");
        params.put("account", "bjhbtx");
        params.put("password", "a123456");
//        params.put("mobile", phone);
//        params.put("content", content);

        String response;
        try {
            System.out.println(params.toString());
            response = HttpUtils.doPost(url, params);
            System.out.println("YunZhiXun  sms_response:" + response);
            Object xmlToBean = XmlUtils.xmlToBean(response, YunZhiXunResp.class);
            System.out.println(xmlToBean);
        } catch (Exception e) {
            System.out.println("YunZhiXun error" + e);
        }
    }
}
