package com.zimu.study.redis.controller;

import com.zimu.study.common.support.ResponseResult;
import com.zimu.study.common.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2020/5/12 12:02
 * @Author jianhua.wang
 */
@RestController
@RequestMapping("/api/zimu/study/redis/test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/add")
    public ResponseResult add() {
        stringRedisTemplate.opsForValue().set("name", "1");
        return ResultUtils.success();
    }

    @GetMapping("/get")
    public ResponseResult get() {
        String name = stringRedisTemplate.opsForValue().get("name");
        return ResultUtils.success(name);
    }

}
