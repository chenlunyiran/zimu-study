package com.zimu.study.netty2.model;

import lombok.Data;

/**
 * @author liudingshun
 * @date 2019/8/7
 */
@Data
public class SimpleUser {
    private Long brokerId;
    private Long userId;

    public SimpleUser(Long brokerId, Long userId) {
        this.brokerId = brokerId;
        this.userId = userId;
    }

    public SimpleUser() {
    }
}
