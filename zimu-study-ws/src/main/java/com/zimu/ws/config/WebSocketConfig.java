package com.zimu.ws.config;

import com.zimu.ws.consts.GlobalConsts;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * @author junmingyang
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //订阅Broker名称
        config.enableSimpleBroker("/topic");
        //全局使用的订阅前缀(客户端订阅路径上会体现出来)
        config.setApplicationDestinationPrefixes(GlobalConsts.APP_PREFIX);
        //点对点使用的订阅前缀(客户端订阅路径上会体现出来)，不设置的话，默认也是/user/
        //注意:enableSimpleBroker方法里的某个参数路径必须和该方法的路径要一样，不然指定用户发送消息将会失败
        config.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(GlobalConsts.ENDPOINT).withSockJS();
    }

}