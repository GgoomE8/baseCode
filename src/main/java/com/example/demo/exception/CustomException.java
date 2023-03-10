package com.example.demo.exception;

import com.example.demo.enums.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private int statuscode;
    private String msg;

    public CustomException(ErrorCode errorCode) {
        this.statuscode = errorCode.getHttpStatus().value();
        this.msg = errorCode.getMessage();
    }

    public CustomException(int statusCode, String msg) {
        this.statuscode = statusCode;
        this.msg = msg;
    }
}
