package com.zimu.study.springboot.controlller.redis;

import com.zimu.study.redis.service.RedisService;
import com.zimu.study.common.support.ResponseResult;
import com.zimu.study.common.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2019/11/18 10:42
 * @Author jianhua.wang
 */
@RestController
@RequestMapping("/api/zimu/study/redis")
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @GetMapping("/add")
    public ResponseResult list() {
        redisService.add("name", "jack");
        return ResultUtils.success();
    }

    @GetMapping("/get")
    public ResponseResult get() {
        return ResultUtils.success(redisService.get("name"));
    }

}
