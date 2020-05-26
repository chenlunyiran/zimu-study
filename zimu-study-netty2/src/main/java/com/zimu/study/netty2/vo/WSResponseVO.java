package com.zimu.study.netty2.vo;

import lombok.Data;

/**
 * Created on 2019-01-15.
 */
@Data
public class WSResponseVO<T> {

    private String op;

    private String url;

    private T data;

    private int code;

    private String msg;
}
