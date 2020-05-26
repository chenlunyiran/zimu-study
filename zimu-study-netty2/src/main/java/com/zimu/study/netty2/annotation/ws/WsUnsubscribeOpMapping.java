package com.zimu.study.netty2.annotation.ws;

import com.zimu.study.netty2.constant.WSOps;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xue
 * Created on 2019-02-14.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@WsOpMapping(op = WSOps.UNSUBSCRIBE)
public @interface WsUnsubscribeOpMapping {

    @AliasFor(annotation = WsOpMapping.class, attribute = "url")
    String value();
}
