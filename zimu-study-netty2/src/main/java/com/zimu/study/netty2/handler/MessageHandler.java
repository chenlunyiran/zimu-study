package com.zimu.study.netty2.handler;

import com.zimu.study.netty2.model.ChannelModule;
import io.netty.channel.Channel;


/**
 * @author James Shen
 * @since 2018/10/7
 */
public interface MessageHandler {
    /**
     * 消息推送
     * @param channel
     * @param channelModule
     */
    void push(Channel channel, ChannelModule channelModule);

}
