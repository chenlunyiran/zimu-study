package com.zimu.study.netty2.util;

import com.zimu.study.netty2.enums.ModuleEnum;
import com.zimu.study.netty2.model.ChannelModule;
import com.zimu.study.netty2.model.SimpleUser;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Shen
 * @since 2018/10/7
 */
@Slf4j
public class GlobalUserUtil {

    private static final Logger WS_LOG = LoggerFactory.getLogger("webSocket");
    private static final ConcurrentHashMap<Channel, Map<Integer, ChannelModule>> CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, List<Channel>> USER_CHANNEL_MAP = new ConcurrentHashMap<>();
    public static void handlerAdded(Channel channel) {
        CHANNEL_MAP.put(channel, new ConcurrentHashMap<>());
    }

    public static void handlerRemoved(Channel channel) {
        if (CHANNEL_MAP.containsKey(channel)) {
            CHANNEL_MAP.remove(channel);
        }
        Long userId = WsUtil.findUserId(channel);
        if (userId != null) {
           if (USER_CHANNEL_MAP.containsKey(userId)) {
               USER_CHANNEL_MAP.remove(userId);
           }
        }
    }

    public static ChannelModule getChannelModule(Channel channel, ModuleEnum type) {
        if (null == type || null == channel) {
            return null;
        }
        Map<Integer, ChannelModule> channelModuleMap = CHANNEL_MAP.get(channel);
        if (null == channelModuleMap) {
            return null;
        }
        return channelModuleMap.get(type.getCode());
    }

    public static ConcurrentHashMap<Channel, Map<Integer, ChannelModule>> getChannelMap() {
        return CHANNEL_MAP;
    }

    public static ConcurrentHashMap<Long, List<Channel>> getUserSessionChannelMap() {
        return USER_CHANNEL_MAP;
    }

    public static List<SimpleUser> getOnlineUsers(){
        List<SimpleUser> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(USER_CHANNEL_MAP)) {
            for (var entry : USER_CHANNEL_MAP.entrySet()) {
                if (CollectionUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                Long brokerId = WsUtil.getBrokerId(entry.getValue().get(0));
                list.add(new SimpleUser(brokerId, entry.getKey()));
            }
        }
        return list;
    }

    public static void cancelSubscribe(Channel channel, Integer subscribeType) {
        if (!CHANNEL_MAP.containsKey(channel)) {
            WS_LOG.info("cancel subscribe failed, not fund channel: [{}]", channel);
            return;
        }
        try {
            Map<Integer, ChannelModule> channelModuleMap = CHANNEL_MAP.get(channel);
            if (channelModuleMap != null) {
                channelModuleMap.remove(subscribeType);
            }
        } catch (Exception e) {
            WS_LOG.info("cancel subscribe error", e);
        }
    }

    public static int getChannelNumber() {
        return CHANNEL_MAP.keySet().size();
    }

    public static List<Channel> listUserChannel(Long userId){
        return USER_CHANNEL_MAP.get(userId);
    }
}
