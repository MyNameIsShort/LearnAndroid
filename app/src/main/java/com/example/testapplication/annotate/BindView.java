package com.example.testapplication.annotate;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //作用于参数（作用对象）
@Retention(RetentionPolicy.RUNTIME) //运行时注解（时机）
public @interface BindView {
    int value();
}
