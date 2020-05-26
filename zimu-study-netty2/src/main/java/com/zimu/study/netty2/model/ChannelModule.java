package com.zimu.study.netty2.model;

import lombok.Data;

/**
 * @author James Shen
 * @since 2018/10/7
 */
@Data
public class ChannelModule {

    /**
     * 请求的模块类型
     */
    private Integer reqType;
    /**
     * 对应的其他参数
     */
    private String param;

    private Long userId;

    private String sessionId;

    private String event;

    /**
     * 1-PC，2-APP
     */
    private Integer platformType;
    /**
     * 版本
     */
    private Integer version;

    public ChannelModule(){}

    public ChannelModule(String sessionId) {
        this.sessionId = sessionId;
    }

    public ChannelModule(Long userId) {
        this.userId = userId;
    }
}
