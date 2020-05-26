package com.zimu.study.netty2.model;

import lombok.Data;

/**
 * @author liudingshun
 * @date 2019/8/7
 */
@Data
public class SdkSession {
    private Long brokerId;

    private String token;

    public SdkSession(Long brokerId, String token) {
        this.brokerId = brokerId;
        this.token = token;
    }

    public SdkSession() {
    }
}
