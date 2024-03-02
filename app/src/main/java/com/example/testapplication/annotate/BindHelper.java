package com.example.testapplication.annotate;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BindHelper {

    static final String TAG = BindHelper.class.getSimpleName();

    public static void bind(Object obj) {
        bindContentView(obj);
        bindView(obj);
        bindEvent(obj);
    }

    private static void bindEvent(Object obj) {
        Class<?> aClass = obj.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);

            for (Annotation annotation : method.getAnnotations()) {
                Class<? extends Annotation> aType = annotation.annotationType();
                BaseEvent event = aType.getAnnotation(BaseEvent.class);
                Log.d(TAG, "BaseEvent:" + (event == null));
                if (event == null) continue;

                String commonListener = event.setCommonListener();
                Class<?> commonObjectListener = event.setCommonObjectListener();
                String methodStr = event.callbackMethod();

                try {
                    Method vMethod = aType.getDeclaredMethod("value");
                    vMethod.setAccessible(true);
                    int value = (int) vMethod.invoke(annotation);
                    Method findMethod = aClass.getMethod("findViewById", int.class);
                    findMethod.setAccessible(true);
                    Object view = findMethod.invoke(obj, value);

                    Method commonMethod = view.getClass().getMethod(commonListener, commonObjectListener);
                    commonMethod.setAccessible(true);

                    Object proxy = Proxy.newProxyInstance(
                            commonObjectListener.getClassLoader(),
                            new Class[]{commonObjectListener}, (o, m, objects) -> method.invoke(obj, null));
                    commonMethod.invoke(view, proxy);
                } catch (Exception e) {
                    Log.d(TAG, "event-error:" + e.getMessage());
                }

            }
        }
    }

    private static void bindView(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        try {
            Method method = aClass.getMethod("findViewById", int.class);
            for (Field field : fields) {
                field.setAccessible(true);
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView == null) {
                    continue;
                }

                method.setAccessible(true);
                int viewId = bindView.value();
                field.set(obj, method.invoke(obj, viewId));
            }
        } catch (Exception e) {
            Log.d(TAG, "BindView-Error:" + e.getMessage());
        }

    }

    private static void bindContentView(Object obj) {
        Class<?> aClass = obj.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView == null) {
            Log.d(TAG, "ContentView注解未找到");
            return;
        }

        int id = contentView.value();
        try {
            Method method = aClass.getMethod("setContentView", int.class);
            method.invoke(obj, id);
        } catch (Exception e) {
            Log.d(TAG, "setContentView错误");
        }
    }

}
