package com.example.testapplication.generic;

//泛型类
public class GenericModel<T, P, L> {

    private T data;
    private P dataP;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GenericModel(T data) {
        this.data = data;
    }
}
