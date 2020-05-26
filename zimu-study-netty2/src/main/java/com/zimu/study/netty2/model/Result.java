package com.zimu.study.netty2.model;

import com.alibaba.fastjson.JSON;
import com.zimu.study.netty2.enums.ErrorEnum;
import com.zimu.study.netty2.enums.ErrorEnumOpen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author James Shen
 * @since 2018/10/5
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7408424483523749738L;
    private Logger logger = LoggerFactory.getLogger(Result.class);
    static final int DEFAULT_SUCCESS_CODE = 0;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 返回值
     */
    private int code;

    /**
     * 结果对象
     */
    private T data;

    public static Result success(int code) {
        Result result = new Result();
        result.setCode(code);

        return result;
    }

    public static Result success(Object data,int code) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);

        return result;
    }


    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(DEFAULT_SUCCESS_CODE);
        result.setData(data);

        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(DEFAULT_SUCCESS_CODE);
        return result;
    }

    public static Result fail(ErrorEnum errorEnum) {
        Result result = new Result();

        result.setCode(errorEnum.getCode());
        result.setMsg(errorEnum.getMsg());
        return result;
    }

    public static Result fail(ErrorEnum errorEnum, String msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(errorEnum.getCode());
        return result;
    }

    public static Result fail(int code, String msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    public static Result failOpen(ErrorEnumOpen errorEnumOpen) {
        Result result = new Result();
        result.setCode(errorEnumOpen.getCode());
        result.setMsg(errorEnumOpen.getMsg());
        return result;
    }

    public static Result failOpen(ErrorEnumOpen errorEnumOpen, Object data) {
        Result result = new Result();
        result.setCode(errorEnumOpen.getCode());
        result.setMsg(errorEnumOpen.getMsg());
        result.setData(data);
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess(){
        return code == DEFAULT_SUCCESS_CODE;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String str;
        try {
            str = JSON.toJSONString(this);
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            str = "";
        }
        return str;
    }
}
