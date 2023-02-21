package com.example.demo.exception;

import com.example.demo.controller.responsedto.MsgResponseDto;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public MsgResponseDto customeException(final CustomException customException) {
        return new MsgResponseDto(customException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException ex) {
        return new MsgResponseDto(ex);
    }


}
