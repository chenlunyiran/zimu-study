package com.zimu.study.netty2.model;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * Created on 2019-01-14.
 */
@Slf4j
class EpollLoop implements DefaultLoop {

    private static final boolean epoll;

    static {
        boolean epollCheck = false;
        try{
            Class.forName("io.netty.channel.epoll.Epoll");
            epollCheck = Epoll.isAvailable();
        }
        catch (ClassNotFoundException cnfe){
        }
        epoll = epollCheck;
        if (log.isDebugEnabled()) {
            log.debug("Default Epoll support : " + epoll);
        }
    }

    public static boolean hasEpoll() {
        return epoll;
    }

    @Override
    public EventLoopGroup newEventLoopGroup(int threads, ThreadFactory factory) {
        return new EpollEventLoopGroup(threads, factory);
    }

    @Override
    public EventLoopGroup eventLoopGroup() {
        return new EpollEventLoopGroup();
    }

    @Override
    public Class<? extends ServerChannel> getServerChannel(EventLoopGroup group) {
        return useEpoll(group) ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }

    @Override
    public Class<? extends Channel> getChannel(EventLoopGroup group) {
        return useEpoll(group) ? EpollSocketChannel.class : NioSocketChannel.class;
    }

    @Override
    public Class<? extends DatagramChannel> getDatagramChannel(EventLoopGroup group) {
        return useEpoll(group) ? EpollDatagramChannel.class : NioDatagramChannel.class;
    }

    @Override
    public String getName() {
        return "epoll";
    }

    private boolean useEpoll(EventLoopGroup group) {
        return group instanceof EpollEventLoopGroup;
    }
}
