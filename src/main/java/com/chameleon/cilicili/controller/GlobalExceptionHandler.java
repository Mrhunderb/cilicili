package com.chameleon.cilicili.controller;

import java.io.IOException;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chameleon.cilicili.controller.response.ResponseVO;
import com.chameleon.cilicili.exception.KaptchaException;
import com.chameleon.cilicili.exception.ServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyBatisSystemException.class)
    public ResponseEntity<?> exceptionHandlerExceptionResolverResponseEntity(Exception e) {
        ResponseVO<?> response = ResponseVO.failure(500, "database error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<?> ioExceptionResponseEntity(Exception e) {
        ResponseVO<?> response = ResponseVO.failure(500, "IO error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodValidationExceptionResponseEntity(MethodArgumentNotValidException e) {
        StringBuilder errorMessages = new StringBuilder("请求验证错误: ");
        e.getBindingResult().getAllErrors().forEach(error -> {
            errorMessages.append(error.getDefaultMessage()).append(", ");
        });
        ResponseVO<?> response = ResponseVO.failure(400, errorMessages.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = KaptchaException.class)
    public ResponseEntity<?> kaptchaExceptionResponseEntity(KaptchaException e) {
        ResponseVO<?> response = ResponseVO.failure(400, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> httpMediaTypeNotSupportedExceptionResponseEntity(Exception e) {
        ResponseVO<?> response = ResponseVO.failure(415, "不支持的请求类型");
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<?> serviceExpectionResponseEntity(ServiceException e) {
        ResponseVO<?> response = ResponseVO.failure(400, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> defaultErrorResponseEntity(Exception e) {
        if (e.getMessage() == null) {
            ResponseVO<?> response = ResponseVO.failure(500, "未知错误");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e.getMessage().contains("Duplicate entry")) {
            ResponseVO<?> response = ResponseVO.failure(500, "重复的数据");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ResponseVO<?> response = ResponseVO.failure(500, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
