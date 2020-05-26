package com.zimu.study.netty2.annotation.ws;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xue
 * Created on 2019-02-14.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface WsOpService {

    @AliasFor(annotation = Service.class, attribute = "value")
    String value() default "";
}
