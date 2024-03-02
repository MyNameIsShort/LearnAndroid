package com.example.testapplication.annotate;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@BaseEvent(
        setCommonListener = "setOnClickListener",
        setCommonObjectListener = View.OnClickListener.class,
        callbackMethod = "onClick"
)
public @interface OnClickEvent {
    int value();
}
