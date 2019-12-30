package com.zimu.study.redis.service;

/**
 * @Classname TestService
 * @Description TODO
 * @Date 2019/11/18 11:03
 * @Author jianhua.wang
 */
public interface RedisService {

    void add(String key, String value);

    String get(String key);

    void del(String key);
}
