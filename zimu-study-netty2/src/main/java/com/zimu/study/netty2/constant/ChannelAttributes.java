package com.zimu.study.netty2.constant;

import io.netty.util.AttributeKey;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xue
 * Created on 2019-01-15.
 */
public final class ChannelAttributes {

    public static final AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");

    public static final AttributeKey<AtomicLong> HEARTBEAT_COUNTER = AttributeKey.valueOf("heartbeat_counter");

//    public static final AttributeKey<WSSettledOrderVO> SETTLED_ORDER = AttributeKey.valueOf("settled_order");
}
