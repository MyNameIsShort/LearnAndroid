package com.example.testapplication;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.testapplication.generic.TestClass;
import com.example.testapplication.generic.GenericModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//泛型测试类
@RunWith(AndroidJUnit4.class)
public class GenericTest {

   final String TAG = GenericTest.class.getSimpleName();

    @Test
    public void test() {
        GenericModel<Integer, String, Float> test = new GenericModel<>(1);
//        test.setData("1"); //编译报错 泛型类型已确定为Integer;

        TestClass<Double> testClass = new TestClass<>(0.08);
        Log.d(TAG, "class:" + test.getData() + ",interface:" + testClass.getTestData()
                + ",method:" + testClass.genericMethod(true));

        //************************************************************************************
        typeErase();
        extendsWillCard();
        indefiniteWillCard();

        //PECS原则 生成者使用extends通配符，消费者使用super通配符，参考Collection.copy()
    }

    //类型擦除
    private void typeErase() {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        //list1 和 list2在编译后会进行类型擦除，都变为ArrayList<Object>，因此对比类信息相同
        list1.add("1");
        list2.add(2);
//        list2.add("2"); //编译报错。创建泛型类时，list2的泛型数据类型被记录下来，因此即使经过类型擦除，在对对象操作时，编译器会自动将对象进行类型转换并类型检查成功
        Log.d(TAG, "类型擦除：" + (list1.getClass() == list2.getClass()));
    }

    //上界通配符(一般用来读，而不是写)
    private void extendsWillCard() {
        ArrayList<? extends Number> testClass = new ArrayList<>();
        testClass.add(null);
//        Number data = testClass1.get(0);
//        testClass1.add(2.8);
//        testClass1.add(1);
        //编译报错，testClass泛型数据类型为Number子类，而Integer仅为其中一种，也可能是Double, ……，未确定类型，为了防止在获取时出现ClassCast错误，禁止写入某一子类数据

        //那为什么还需要引入~的概念呢？
        //为了拓展方法形参中类型参数的范围,例：add()中声明类型为int,那么double类型又需要写一个方法，而内部逻辑其实是相同的
        Log.d(TAG, "上界通配符:" + add(new ArrayList<>(Arrays.asList(1, 2, 3))));
    }

    private int add(List<? extends Number> list) {
        if (list == null || list.size() == 0) return -1;

        return list.get(0).intValue() + list.get(list.size() - 1).intValue();
    }

    //下界通配符(一般用来写，而不是读)
    private void superWillCard() {
        ArrayList<? super Number> list = new ArrayList<>();
        //因为泛型类型参数范围是Number及其超类，所以能保证完全覆盖到子类，写入没问题
        list.add(1);
        list.add(1.2);
    }

    //无限定通配符
    private void indefiniteWillCard() {
        ArrayList<?> list = new ArrayList<>();
        list.add(null);
//        list.add(1); //编译错误

        Object oj = list.get(0);
//        Integer integer = list.get(0); //编译错误
        Log.d(TAG, "无限定通配符:" + oj);
    }
}
