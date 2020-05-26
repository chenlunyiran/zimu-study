package com.zimu.study.netty2.util;

import io.netty.util.AttributeKey;

/**
 * @author James Shen
 * @since 2018/10/16
 */
public class Attributes {

    public static final AttributeKey<Object> SESSION = AttributeKey.valueOf("session");

    public static final AttributeKey<Object> USER = AttributeKey.valueOf("user");

    public static final AttributeKey<Long> BROKER_ID = AttributeKey.valueOf("brokerId");

    public static final AttributeKey<Object> PLATFORM_TYPE = AttributeKey.valueOf("platformType");
}
