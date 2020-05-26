package com.zimu.study.netty2.handler;

import com.zimu.study.netty2.util.WsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.SEC_WEBSOCKET_KEY;
import static io.netty.handler.codec.http.HttpHeaderNames.UPGRADE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created on 2019-01-14.
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ChannelOperationsHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketHandler webSocketHandler;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handlerHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        FullHttpResponse response = null;
        HttpMethod method = request.method();
        HttpHeaders headers = request.headers();

        if (HttpMethod.GET != method) {
            response = new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN);
            sendHttpResponse(ctx, request, response);
            return;
        }

        if (!"WebSocket".equalsIgnoreCase(headers.get(UPGRADE))) {
            log.error("Invalid 'Upgrade' header");
            response = new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST);
            sendHttpResponse(ctx, request, response);
            return;
        }

        String connectionValue = headers.get(CONNECTION);
        if (!connectionValue.contains("Upgrade") && !connectionValue.contains("upgrade")) {
            log.error("Invalid 'Connection' header");
            response = new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST);
            sendHttpResponse(ctx, request, response);
            return;
        }

        String key = headers.get(SEC_WEBSOCKET_KEY);
        if (key == null) {
            log.error("Missing \"Sec-WebSocket-Key\" header");
            return;
        }

        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://" + request.headers().get("Host") + "/" + "ws", null, true, 65536);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelPipeline pipeline = ctx.pipeline();
            pipeline.remove(ctx.name());
            pipeline.addLast("idle", new IdleStateHandler(20,0,0, TimeUnit.SECONDS))
                    .addLast("wsHandler", webSocketHandler);
            handshaker.handshake(ctx.channel(), request)
                    .addListener(future -> {
                        log.info("OK");
                    });

            // todo 在这里保存SDK传过来的信息，怎么用需要考虑下
            // sdk的版本
            String version = request.headers().get("version");
            String platform = request.headers().get("platform");
            String userAgent = request.headers().get("userAgent");
            String networkState = request.headers().get("networkState");
            String udid = request.headers().get("udid");
            String channelId = request.headers().get("channelId");
            String timeStamp = request.headers().get("timeStamp");
            String acceptLanguage = request.headers().get("acceptLanguage");
            log.info("headers:{}", request.headers().toString());
            WsUtil.saveBrokerId(ctx, request, null);
            log.info("channel({})：connected，version：{}", ctx.channel(), version);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Autowired
    public void setWebSocketHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }
}
