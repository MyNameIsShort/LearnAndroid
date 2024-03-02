package com.example.testapplication.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE) //元注解
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseEvent {
    /**
     * 事件三要素
     *  1.订阅方式
     *  2.事件源对象
     *  3.事件执行方法
     */
     String setCommonListener();

     Class<?> setCommonObjectListener();

     String callbackMethod();
}
