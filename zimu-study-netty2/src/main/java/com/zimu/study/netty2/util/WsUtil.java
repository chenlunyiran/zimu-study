package com.zimu.study.netty2.util;

import com.zimu.study.netty2.manager.SessionManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Shen
 * @since 2018/10/7
 */
@Slf4j
public class WsUtil {

    private static SessionManager sessionManager = BeanUtil.getBean(SessionManager.class);
    private static TokenUtil tokenUtil = BeanUtil.getBean(TokenUtil.class);
    /**
     * 获取绑定在channel中的sessionId
     * @param channel
     * @return
     */
    public static String getSessionId(Channel channel) {
        Object object = channel.attr(Attributes.SESSION).get();
        if (object != null) {
            return object.toString();
        }
        relieveSessionChannel(channel);
        return "";
    }

    public static Long findUserId(Channel channel) {
        Object object = channel.attr(Attributes.USER).get();
        if (object != null) {
            return Long.parseLong(object.toString());
        }
        return null;
    }

    /**
     * 获取绑定在channel中的userId
     * @param channel
     * @return
     */
    public static Long getNoSdkUserId(Channel channel) {
        return findUserId(channel);
    }

    public static Long getBrokerId(Channel channel) {
        return channel.attr(Attributes.BROKER_ID).get();
    }

    /**
     * 解除user - channel绑定关系
     * @param channel
     */
    public static void relieveUserChannel(Channel channel) {
        Object object = channel.attr(Attributes.USER).get();
        if (object != null) {
            long userId = Long.valueOf(object.toString());
            List<Channel> list =  GlobalUserUtil.getUserSessionChannelMap().get(userId);
            list.remove(channel);
        }
        channel.attr(Attributes.USER).set(null);
    }

    public static Map<String, String> toParamMap(String uri) {
        Map<String, String> paramMap = new HashMap<>();
        int ix = uri.indexOf("?");
        if (ix == -1) {
            return paramMap;
        }
        String tmp = uri.substring(ix+1);
        if (StringUtils.isEmpty(tmp)) {
            return paramMap;
        }
        String[] params =  tmp.split("&");
        for (String param : params){
            String[] entry = param.split("=");
            if (entry.length !=2) {
                continue;
            }
            paramMap.put(entry[0], entry[1]);
        }
        return paramMap;
    }

    public static void saveBrokerId(ChannelHandlerContext ctx, FullHttpRequest request, String defaultBrokerId) {
        String brokerId = request.headers().get("brokerId");
        if (StringUtils.isEmpty(brokerId)) {
            Map<String, String> map = toParamMap(request.uri());
            brokerId = map.get("brokerId");
        }
        if (StringUtils.isEmpty(brokerId)) {
            brokerId = defaultBrokerId;
        }
        ctx.channel().attr(Attributes.BROKER_ID).set(Long.parseLong(brokerId));
    }


//    public static Integer getSdkCurrency(Channel channel) {
//        WSSpotIndexVO wsSpotIndexVO = SpotIndexService.getSubscribedTimelyChannels().get(channel);
//        if (null == wsSpotIndexVO) {
//            return null;
//        }
//        return wsSpotIndexVO.getCurrency();
//
//    }
//
//
//    public static Integer getNoSdkCurrency(Channel channel) {
//        ChannelModule channelModule = GlobalUserUtil.getChannelModule(channel, ModuleEnum.SPOT_INDEX);
//        if (null == channelModule) {
//            return OptionRoleEnum.VFOTA.getId();
//        }
//        return getNoSdkCurrency(channelModule.getParam());
//
//    }
//
//    public static Integer getNoSdkCurrency(String param) {
//        SpotIndexParamDTO paramDTO = BasicUtils.exeWhitoutError(() -> JSON.parseObject(param, SpotIndexParamDTO.class));
//        if (null == paramDTO) {
//            return OptionRoleEnum.VFOTA.getId();
//        }
//        return paramDTO.getCurrency();
//    }

    /**
     * 解除session - channel 绑定关系
     * @param channel
     */
    public static void relieveSessionChannel(Channel channel) {
        channel.attr(Attributes.SESSION).set(null);
    }

    public static void relieveChannel(Channel channel) {
        channel.attr(Attributes.USER).set(null);
        channel.attr(Attributes.SESSION).set(null);
    }


}
