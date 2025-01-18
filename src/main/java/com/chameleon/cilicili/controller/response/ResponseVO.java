package com.chameleon.cilicili.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {

    // 响应状态码
    private int code;

    // 响应消息
    private String message;

    // 响应数据（泛型）
    private T data;

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(200, "Success", data);
    }

    public static <T> ResponseVO<T> success(String message, T data) {
        return new ResponseVO<>(200, message, data);
    }

    public static <T> ResponseVO<T> failure(int code, String message) {
        return new ResponseVO<>(code, message, null);
    }

    public static <T> ResponseVO<T> failure(String message) {
        return new ResponseVO<>(500, message, null);
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
