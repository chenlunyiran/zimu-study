package com.zimu.study.netty2.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zimu.study.netty2.annotation.ws.WSMessageDispatcher;
import com.zimu.study.netty2.enums.ErrorEnumOpen;
import com.zimu.study.netty2.manager.JSONManager;
import com.zimu.study.netty2.model.Result;
import com.zimu.study.netty2.vo.WSRequestVO;
import com.zimu.study.netty2.vo.WSResponseVO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static com.zimu.study.netty2.constant.ChannelAttributes.HEARTBEAT_COUNTER;
import static com.zimu.study.netty2.constant.ChannelAttributes.USER_ID;

/**
 * Created on 2019-01-15.
 */
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private static Logger log = LoggerFactory.getLogger("open_api_ws");

//    private ApiSecurityService apiSecurityService;

    private WSMessageDispatcher wsMessageDispatcher;

    private JSONManager jasonManager;

//    private UserChannelBinder userChannelBinder;
//
//    private UserBaseOpenManager userBaseOpenManager;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            String text = ((TextWebSocketFrame) msg).text();
            log.info("incoming text: {}", text);
            WSResponseVO<Object> response = new WSResponseVO<>();
            Channel channel = ctx.channel();
            Optional<WSRequestVO> wsRequestVOOp = jasonManager.parseJSON(text, WSRequestVO.class);
            if (!wsRequestVOOp.isPresent()) {
                log.error("no request vo, {}", wsRequestVOOp);
                response.setCode(ErrorEnumOpen.WS_REQUEST_ERROR.getCode());
                response.setMsg(ErrorEnumOpen.WS_REQUEST_ERROR.getMsg());
                sendMessage(response, channel);
                return;
            }

            WSRequestVO wsRequestVO = wsRequestVOOp.get();
            log.info("request vo: {}", wsRequestVO);
            String event = wsRequestVO.getEvent();
            if (StringUtils.equals(event, "ping")) {
                ObjectNode pong = jasonManager.newObjectNode();
                pong.put("event", "pong");
                sendMessage(pong, channel);

                AtomicLong counter = channel.attr(HEARTBEAT_COUNTER).get();
                counter.incrementAndGet();
                return;
            }
            String signature = wsRequestVO.getSignature();
            String timestamp = wsRequestVO.getTimestamp();
            String op = wsRequestVO.getOp();
            String url = wsRequestVO.getUrl();
            ObjectNode args = wsRequestVO.getArgs();
            String platform = wsRequestVO.getPlatform();

            response.setOp(op);
            response.setUrl(url);

            Result result = null;
            String userId = channel.attr(USER_ID).get();
            if (StringUtils.equals(url, "v1/login")) {
                String uId = args.get("userId").asText();
                if (StringUtils.isEmpty(uId)) {
                    response.setCode(ErrorEnumOpen.WS_LOGIN_NO_USER_ID.getCode());
                    response.setMsg(ErrorEnumOpen.WS_LOGIN_NO_USER_ID.getMsg());
                    sendMessage(response, channel);
                    return;
                } else if (uId.equals(userId)){
                    response.setCode(ErrorEnumOpen.WS_OK.getCode());
                    response.setMsg(ErrorEnumOpen.WS_OK.getMsg());
                    sendMessage(response, channel);
                    return;
                }
//                SdkSession sdkSession = userBaseOpenManager.getSessionByUserId(uId);
//                String token = sdkSession.getToken();
//                Long brokerId = sdkSession.getBrokerId();
//                if (StringUtils.isEmpty(token) || null == brokerId) {
//                    log.error("empty token or brokerId, token:{}, brokerId:{}", token, brokerId);
//                    response.setCode(ErrorEnumOpen.WS_LOGIN_NO_TOKEN.getCode());
//                    response.setMsg(ErrorEnumOpen.WS_LOGIN_NO_TOKEN.getMsg());
//                    sendMessage(response, channel);
//                    return;
//                }
//                boolean same = validateSign(args, token, timestamp, signature, platform, url);
//                if (!same) {
//                    response.setCode(ErrorEnumOpen.WS_VERIFICATION_FAIL.getCode());
//                    response.setMsg(ErrorEnumOpen.WS_VERIFICATION_FAIL.getMsg());
//                    sendMessage(response, channel);
//                    log.error("login error url or invalid token. url:{}, userId:{}, token:{}", url, userId, token);
//                    return;
//                }
//                channel.attr(Attributes.BROKER_ID).set(brokerId);
                result = wsMessageDispatcher.dispatch(wsRequestVO, channel, uId);
            } else {
                boolean noLoginUrl = isNoLoginUrl(url);
                boolean valid = false;
                String token = "";
                if (!noLoginUrl) {
                    if (StringUtils.isEmpty(userId)) {
                        response.setCode(ErrorEnumOpen.WS_CHANNEL_ERROR.getCode());
                        response.setMsg(ErrorEnumOpen.WS_CHANNEL_ERROR.getMsg());
                        sendMessage(response, channel);
                        return;
                    }
//                    token = apiSecurityService.getTokenByUserId(userId);
//                    valid = StringUtils.isNotEmpty(token) && validateSign(args, token, timestamp, signature, platform, url);
                }
                if (noLoginUrl || valid) {
                    result = wsMessageDispatcher.dispatch(wsRequestVO, channel, userId);
                } else {
                    if (!valid) {
                        response.setCode(ErrorEnumOpen.WS_VERIFICATION_FAIL.getCode());
                        response.setMsg(ErrorEnumOpen.WS_VERIFICATION_FAIL.getMsg());
                        sendMessage(response, channel);
                        log.error("no login error url or invalid token. url:{}, userId:{}, token:{}", url, userId, token);
                        return;
                    }
                }
            }
            if (Objects.nonNull(result)) {
                response.setCode(result.getCode());
                response.setMsg(result.getMsg());
                response.setData(result.getData());
                sendMessage(response, channel);
            }
        }

        if (msg instanceof PingWebSocketFrame) {
            ctx.writeAndFlush(new PongWebSocketFrame(msg.content().retain()));
            return;
        }
        if (msg instanceof CloseWebSocketFrame) {
            ctx.writeAndFlush(msg.retainedDuplicate()).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        if (msg instanceof BinaryWebSocketFrame) {
            return;
        }
        if (msg instanceof PongWebSocketFrame) {
            return;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel inactive");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().attr(HEARTBEAT_COUNTER).set(new AtomicLong());
        log.info("handler added");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        invalidateChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("exception caught", cause);
        Channel channel = ctx.channel();
//        invalidateChannel(channel);
        ctx.close();
        channel.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()) {
                case READER_IDLE:
//                    AtomicLong counter = ctx.channel().attr(HEARTBEAT_COUNTER).get();
//                    if (counter.get() == 0) {
//                        ctx.close();
//                        ctx.channel().close();
//                    } else {
//                        counter.set(0);
//                        ctx.writeAndFlush(ping);
//                    }
                    break;
                case WRITER_IDLE:
                    ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    break;
                default:
                    break;
            }
        }
    }

//    private void invalidateChannel(Channel channel) {
//        String userId = channel.attr(USER_ID).get();
//        if (StringUtils.isNotEmpty(userId)) {
//            boolean userRemoved = userChannelBinder.removeChannel(userId, channel);
//            if (userRemoved) {
//                wsMessageDispatcher.unbindChannel(channel, userId);
//            }
//        }
//        wsMessageDispatcher.removeChannel(channel, userId);
//    }

//    private boolean validateSign(ObjectNode args, String token, String timestamp, String signFromRequest, String platform, String url) {
//        //验签
//        List<String> params = new ArrayList<>();
//        if (Objects.nonNull(args)) {
//            Iterator<Map.Entry<String, JsonNode>> fields = args.fields();
//            while (fields.hasNext()) {
//                Map.Entry<String, JsonNode> next = fields.next();
//                params.add(next.getKey() + "=" + next.getValue().asText());
//            }
//        }
//        String sortedParams = params.stream()
//                .sorted()
//                .collect(joining("&"));
//
//        // calculate signature
//        String stringForSigning = sortedParams + timestamp;
//        return apiSecurityService.validateSignature(stringForSigning, token, signFromRequest, platform, url);
//    }

    private void sendMessage(Object message, Channel channel) {
        sendMessage(jasonManager.toJSONString(message), channel);
    }

    private void sendMessage(String message, Channel channel) {
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    private final List<String> noLoginUrls = Arrays.asList(
            "v1/spot/index/history",
            "v1/spot/index/timely",
            "v1/exchange/spot/index",
            "v1/exercise/price",
            "v1/front/config"
    );

    private boolean isNoLoginUrl(String url) {
        return noLoginUrls.contains(url);
    }

//    @Autowired
//    public void setApiSecurityService(ApiSecurityService apiSecurityService) {
//        this.apiSecurityService = apiSecurityService;
//    }

    @Autowired
    public void setWsMessageDispatcher(WSMessageDispatcher wsMessageDispatcher) {
        this.wsMessageDispatcher = wsMessageDispatcher;
    }

    @Autowired
    public void setJasonManager(JSONManager jasonManager) {
        this.jasonManager = jasonManager;
    }

//    @Autowired
//    public void setUserChannelBinder(UserChannelBinder userChannelBinder) {
//        this.userChannelBinder = userChannelBinder;
//    }
//
//    @Autowired
//
//    public void setUserBaseOpenManager(UserBaseOpenManager userBaseOpenManager) {
//        this.userBaseOpenManager = userBaseOpenManager;
//    }
}
