package com.zimu.study.netty2.model;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ThreadFactory;

/**
 * Created on 2019-01-14.
 */
public interface DefaultLoop {

    default EventLoopGroup newEventLoopGroup(int threads, ThreadFactory factory) {
        throw new IllegalStateException("Missing Epoll/KQueue on current system");
    }

    default EventLoopGroup eventLoopGroup() {
        return new EpollEventLoopGroup();
    }

    default Class<? extends ServerChannel> getServerChannel(EventLoopGroup group) {
        return NioServerSocketChannel.class;
    }

    default Class<? extends Channel> getChannel(EventLoopGroup group) {
        return NioSocketChannel.class;
    }

    default Class<? extends DatagramChannel> getDatagramChannel(EventLoopGroup group) {
        return NioDatagramChannel.class;
    }

    default String getName() {
        return "nio";
    }
}
