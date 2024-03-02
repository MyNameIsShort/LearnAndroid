package com.example.testapplication.generic;

//泛型接口实现类(也可在当前类中确定泛型类型)
public class TestClass<E> implements GenericInterface<E> {

    private E testData;

    public TestClass() {

    }

    public TestClass(E testData) {
        this.testData = testData;
    }

    public void setTestData(E data) {
        this.testData = data;
    }

    //不是泛型方法，即使使用了泛型数据类型，但是是类中声明的
    @Override
    public E getTestData() {
        return testData;
    }

    //泛型方法
    public <T> T genericMethod(T data) {
        return data;
    }
}
