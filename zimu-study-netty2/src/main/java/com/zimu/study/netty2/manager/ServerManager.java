package com.zimu.study.netty2.manager;

import com.zimu.study.netty2.handler.ChannelOperationsHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @author xue
 * Created on 2019-01-14.
 */
@Slf4j
@Component
public class ServerManager implements ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean started;

    private int port;

    private ChannelOperationsHandler channelOperationsHandler;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        log.info("context refreshed: {}", event);
        if (!started) {
            started = true;
            new Thread(this::startServer).start();
        }
    }

    private void startServer() {
        /**
         * 服务端需要2个线程组  boss处理客户端连接  work进行客服端连接之后的处理
         */
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.SO_RCVBUF, 1024 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("httpCodec", new HttpServerCodec())
                                    .addLast("aggregator", new HttpObjectAggregator(65536))
                                    .addLast("httpHandler", channelOperationsHandler);
                        }
                    });
            Channel channel = serverBootstrap.bind(port).channel();
            log.info("ws>>【服务器启动成功 ======== 端口：" + port + "】");
            log.info("Netty Server started.");
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("ws>>【服务器启动失败 ======== 端口：" + port + "】");
            log.error("Netty server failed to start", e);
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    @Autowired
    public void setChannelOperationsHandler(ChannelOperationsHandler channelOperationsHandler) {
        this.channelOperationsHandler = channelOperationsHandler;
    }

    @Value("${fota.open.ports.websocket:8093}")
    public void setPort(int port) {
        this.port = port;
    }
}
