package com.zimu.study.netty2.annotation.ws;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zimu.study.netty2.enums.ErrorEnumOpen;
import com.zimu.study.netty2.manager.JSONManager;
import com.zimu.study.netty2.model.Result;
import com.zimu.study.netty2.util.WsUtil;
import com.zimu.study.netty2.vo.WSRequestArgs;
import com.zimu.study.netty2.vo.WSRequestVO;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xue
 * Created on 2019-02-14.
 */
@Slf4j
@Component
public class WSMessageDispatcher implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>, Ordered {

    private ApplicationContext applicationContext;

    private JSONManager jsonManager;

    private final Map<String, InvocableHandlerMethod> registry = new ConcurrentHashMap<>();

    private final List<InvocableHandlerMethod> removeChannelRegistry = new ArrayList<>();

    private final List<InvocableHandlerMethod> unbindChannelRegistry = new ArrayList<>();

    private volatile boolean refreshEventReceived;

    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public Result dispatch(WSRequestVO wsRequestVO, Channel channel, String userId) {
        String op = wsRequestVO.getOp(), url = wsRequestVO.getUrl();
        return dispatch(op, url, wsRequestVO.getArgs(), channel, userId);
    }

    public Result dispatch(String op, String url, ObjectNode requestArgs, Channel channel, String userId) {
        InvocableHandlerMethod handlerMethod = registry.get(op + ":" + url);
        if (handlerMethod == null) {
            return Result.failOpen(ErrorEnumOpen.WS_OP_URL_ERROR);
        }
        List<Object> args =  makeArgs(handlerMethod, channel, userId, requestArgs);

        // 这里返回的就是
        Object o = handlerMethod.invoke(args.toArray());
        return (Result) o;
    }

    public void removeChannel(Channel channel, String userId) {
        for (InvocableHandlerMethod handlerMethod : removeChannelRegistry) {
            List<Object> args = makeArgs(handlerMethod, channel, userId, null);

            handlerMethod.invoke(args.toArray());
        }
    }

    public void unbindChannel(Channel channel, String userId) {
        for (InvocableHandlerMethod handlerMethod : unbindChannelRegistry) {
            List<Object> args = makeArgs(handlerMethod, channel, userId, null);

            handlerMethod.invoke(args.toArray());
        }
    }

    /**
     * 返回requestVO和userId数据
     * @param handlerMethod
     * @param channel
     * @param userId
     * @param requestArgs
     * @return
     */
    private List<Object> makeArgs(HandlerMethod handlerMethod, Channel channel, String userId, ObjectNode requestArgs) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        List<Object> args = new ArrayList<>(methodParameters.length);
        for (MethodParameter methodParameter : methodParameters) {
            Class<?> parameterType = methodParameter.getParameterType();
            methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
            if (parameterType.isAssignableFrom(Channel.class)) {
                args.add(channel);
            } else if (WSRequestArgs.class.isAssignableFrom(parameterType)) {
                Object wsArgs = null;
                if (requestArgs != null) {
                    wsArgs = jsonManager.toPojo(requestArgs, parameterType).orElse(null);
                }
                args.add(wsArgs);
            } else if (StringUtils.equals("userId", methodParameter.getParameterName())) {
                if (Number.class.isAssignableFrom(parameterType)) {
                    args.add(NumberUtils.parseNumber(userId, (Class<Number>) parameterType));
                } else if (parameterType.isInstance(userId)) {
                    args.add(userId);
                }
            } else if (StringUtils.equals("brokerId", methodParameter.getParameterName()) && Number.class.isAssignableFrom(parameterType)) {
                args.add(WsUtil.getBrokerId(channel));
            } else{
                args.add(null);
            }
        }

        return args;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (!refreshEventReceived) {
            refreshEventReceived = true;

            Map<String, Object> beans = applicationContext.getBeansWithAnnotation(WsOpService.class);
            for (Map.Entry<String, Object> entry : beans.entrySet()) {
                Object bean = entry.getValue();
                Class<?> controllerClass = bean.getClass();
                controllerClass = ClassUtils.getUserClass(controllerClass);
                Map<Method, String> opMappingMethods = MethodIntrospector.selectMethods(controllerClass, (MethodIntrospector.MetadataLookup<String>) method -> {
                    WSRemoveChannel removeChannelAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, WSRemoveChannel.class);
                    if (removeChannelAnnotation != null) {
                        removeChannelRegistry.add(new InvocableHandlerMethod(bean, method));
                    }

                    WSUnbindChannel unbindChannelAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, WSUnbindChannel.class);
                    if (unbindChannelAnnotation != null) {
                        unbindChannelRegistry.add(new InvocableHandlerMethod(bean, method));
                    }

                    WsOpMapping annotation = AnnotatedElementUtils.findMergedAnnotation(method, WsOpMapping.class);
                    if (annotation != null && !StringUtils.isEmpty(annotation.op()) && !StringUtils.isEmpty(annotation.url())) {
                        return annotation.op() + ":" + annotation.url();
                    }

                    return "";
                });
                opMappingMethods.forEach((method, op) -> {
                    if (!StringUtils.isEmpty(op)) {
                        HandlerMethod handlerMethod = registry.get(op);
                        if (Objects.nonNull(handlerMethod)) {
                            throw new IllegalStateException("Ambiguous mapping. Cannot map method "
                                    + method.toGenericString() + " to " + op +
                                    ": There is already method " + handlerMethod + " mapped");
                        }
                        registry.put(op, new InvocableHandlerMethod(bean, method));
                    }
                });
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    static class InvocableHandlerMethod extends HandlerMethod {

        public InvocableHandlerMethod(Object bean, Method method) {
            super(bean, method);
        }

        public InvocableHandlerMethod(Object bean, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
            super(bean, methodName, parameterTypes);
        }

        public InvocableHandlerMethod(String beanName, BeanFactory beanFactory, Method method) {
            super(beanName, beanFactory, method);
        }

        protected InvocableHandlerMethod(HandlerMethod handlerMethod) {
            super(handlerMethod);
        }

        public Object invoke(Object... args) {
            ReflectionUtils.makeAccessible(getBridgedMethod());
            try {
                log.info("getBridgedMethod().invoke(getBean(), {}) error!", args);
                return getBridgedMethod().invoke(getBean(), args);
            } catch (Exception e) {
                log.error("getBridgedMethod().invoke() error! {}, {}, {}", getBridgedMethod(), getBean(), args, e);
            }
            return null;
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setJsonManager(JSONManager jsonManager) {
        this.jsonManager = jsonManager;
    }
}
