package com.zimu.study.common.utils;

import com.zimu.study.common.support.ErrorCode;
import com.zimu.study.common.support.ResponseResult;

/**
 * @Classname ResultUtils
 * @Description TODO
 * @Date 2019/11/18 10:49
 * @Author jianhua.wang
 */
public class ResultUtils {
    public ResultUtils() {
    }

    public static ResponseResult success() {
        return success((Object)null);
    }

    public static <T> ResponseResult success(T data) {
        return build(0, "", data);
    }

    public static ResponseResult failure(String msg) {
        return failure(-1, msg);
    }

    public static ResponseResult failure(ErrorCode errorCode) {
        return failure(errorCode, (Object)null);
    }

    public static <T> ResponseResult failure(ErrorCode errorCode, T data) {
        return build(errorCode.getCode(), errorCode.getMessage(), data);
    }

    public static ResponseResult failure(int code, String msg) {
        return build(code, msg, (Object)null);
    }

    public static <T> ResponseResult build(int code, String msg, T data) {
        return new ResponseResult(code, msg, data);
    }
}
