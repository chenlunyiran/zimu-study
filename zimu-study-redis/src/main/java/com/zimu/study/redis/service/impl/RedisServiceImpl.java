package com.zimu.study.redis.service.impl;

import com.zimu.study.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Classname TestServiceImpl
 * @Description TODO
 * @Date 2019/11/18 11:03
 * @Author jianhua.wang
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void add(String key, String value) {
        stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
}
