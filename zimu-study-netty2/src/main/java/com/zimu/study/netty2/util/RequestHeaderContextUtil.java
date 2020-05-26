package com.zimu.study.netty2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author huangtao 2019/3/25 5:44 PM
 * @Description
 */
@Slf4j
@Component
public class RequestHeaderContextUtil {

    public static String getApiKey(HttpServletRequest request) {
        String apiKey = request.getHeader("apiKey");
        return apiKey;
    }

    public static Long getBrokerId(HttpServletRequest request){
        Long brokerId = Long.parseLong(request.getHeader("Broker-Id"));
        return brokerId;
    }
    public static Integer getPlatform(HttpServletRequest request){
        Integer platform = request.getIntHeader("Platform");
        return platform;
    }

    public static String getLanguage(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");

        return language;
    }

}
