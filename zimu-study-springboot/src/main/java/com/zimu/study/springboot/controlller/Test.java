package com.zimu.study.springboot.controlller;

import com.zimu.study.common.support.ResponseResult;
import com.zimu.study.common.utils.ResultUtils;
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
@RequestMapping("/api/zimu/study")
public class Test {

    @GetMapping("/index")
    public ResponseResult list() {
        return ResultUtils.success("1111111");
    }

}
