package com.zimu.study.netty2.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gavin Shen
 * @Date 2019/1/15
 */
@Data
public class BrokerConfigDO implements Serializable{
    private static final long serialVersionUID = -8439004122026548056L;
    private Long id;
    private Long brokerId;
    private String appKey;
    private String appSecret;
    private String config;
    private Date gmtCreate;
    private Date gmtModified;
    /**
     * status 1 表示有效 0 表示无效
     */
    private Integer status;
}
