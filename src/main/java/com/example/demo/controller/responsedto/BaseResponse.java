package com.example.demo.controller.responsedto;

import java.io.Serializable;

public abstract class BaseResponse<T> implements Serializable {
    int status;
    T data;
    String msg;

    public BaseResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }


    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
