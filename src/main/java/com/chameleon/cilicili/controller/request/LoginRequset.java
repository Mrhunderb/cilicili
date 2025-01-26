package com.chameleon.cilicili.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginRequset {

    @NotEmpty(message = "邮箱不能为空") @Email(message = "邮箱格式不正确")
    public String email;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 20, message = "密码长度不能超过20")
    @Size(min = 8, message = "密码长度不能小于8")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$", message = "密码必须包含大小写字母和数字")
    public String password;

    @NotEmpty(message = "验证码ID不能为空")
    public String captchaId;

    @NotEmpty(message = "验证码不能为空")
    @Size(max = 5)
    public String value;

}
