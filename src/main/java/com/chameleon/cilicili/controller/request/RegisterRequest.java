package com.chameleon.cilicili.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterRequest {
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    public String email;

    @NotEmpty(message = "用户名不能为空")
    @Size(max = 20, message = "用户名长度不能超过20")
    public String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 50, message = "密码长度不能超过50")
    public String password;

    @NotEmpty(message = "验证码ID不能为空")
    public String captchaId;

    @NotEmpty(message = "验证码不能为空")
    @Size(max = 5)
    public String value;
}

