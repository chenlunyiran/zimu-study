package com.zimu.study.netty2.model;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
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
class KqueueLoop implements DefaultLoop {

    private static final boolean kqueue;

    static {
        boolean kqueueCheck = false;
        try{
            Class.forName("io.netty.channel.kqueue.KQueue");
            kqueueCheck = KQueue.isAvailable();
        }
        catch (ClassNotFoundException cnfe){
        }
        kqueue = kqueueCheck;
        if (log.isDebugEnabled()) {
            log.debug("Default KQueue support : " + kqueue);
        }
    }

    public static boolean hasKQueue() {
        return kqueue;
    }

    @Override
    public EventLoopGroup newEventLoopGroup(int threads, ThreadFactory factory) {
        return new KQueueEventLoopGroup(threads, factory);
    }

    @Override
    public EventLoopGroup eventLoopGroup() {
        return new KQueueEventLoopGroup();
    }

    @Override
    public Class<? extends ServerChannel> getServerChannel(EventLoopGroup group) {
        return useKQueue(group) ? KQueueServerSocketChannel.class : NioServerSocketChannel.class;
    }

    @Override
    public Class<? extends Channel> getChannel(EventLoopGroup group) {
        return useKQueue(group) ? KQueueSocketChannel.class : NioSocketChannel.class;
    }

    @Override
    public Class<? extends DatagramChannel> getDatagramChannel(EventLoopGroup group) {
        return useKQueue(group) ? KQueueDatagramChannel.class : NioDatagramChannel.class;
    }

    @Override
    public String getName() {
        return "kqueue";
    }

    private boolean useKQueue(EventLoopGroup group) {
        return group instanceof KQueueEventLoopGroup;
    }
}
