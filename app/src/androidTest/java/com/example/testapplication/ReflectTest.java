package com.example.testapplication;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.testapplication.reflect.TestModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//反射测试类
@RunWith(AndroidJUnit4.class)
public class ReflectTest {

    final String TAG = ReflectTest.class.getSimpleName(); //其实这就是反射的一种使用方式

    @Test
    public void test() {
        //获取MainActivity中的key参数
        try {
            Class<?> mClass = Class.forName("com.example.testapplication.reflect.TestModel");
            Method method = mClass.getDeclaredMethod("setKey", String.class);
            Method setI = mClass.getDeclaredMethod("setI", int.class);
            Object oj = mClass.newInstance();

            Field[] fieldList = mClass.getDeclaredFields();
            for (Field field : fieldList) {
                field.setAccessible(true);
                Log.d(TAG, "反射参数名:" + field.getName() + ",值：" + field.get(oj));
            }

            setI.invoke(oj, 2);

            method.setAccessible(true); //私有变量/方法需要进行设置
            method.invoke(oj, "Android");

            Field i = mClass.getDeclaredField("i");
            i.setAccessible(true); //私有变量/方法需要进行设置
            Log.d(TAG, "反射修改后key值:" + mClass.getDeclaredField("key").get(oj) + ",i：" + i.get(oj));

            Log.d(TAG, "正常创建model,key值:" + new TestModel().key);
        } catch (Exception e) {

        }
    }

}
