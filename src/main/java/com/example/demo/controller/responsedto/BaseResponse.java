package com.example.demo.controller.responsedto;

import java.io.Serializable;

public abstract class BaseResponse<T> implements Serializable {
    int code;
    String status;
    T data;
    String error;

    public BaseResponse(int code, T data, String status, String error) {
        this.code = code;
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
