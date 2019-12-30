package com.zimu.study.common.support;

import java.io.Serializable;

/**
 * @Classname ResponseResult
 * @Description TODO
 * @Date 2019/11/18 10:46
 * @Author jianhua.wang
 */
public class ResponseResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
