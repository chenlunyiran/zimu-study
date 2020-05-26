package com.zimu.study.netty2.vo;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

/**
 * Created on 2019-01-15.
 */
@Data
public class WSRequestVO {

    private String op;

    private String url;

    private String timestamp;

    private String signature;

    private String event;

    private ObjectNode args;

    /**
     * pc、app
     */
    private String platform;
}
