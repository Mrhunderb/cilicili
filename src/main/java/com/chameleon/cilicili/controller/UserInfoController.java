package com.chameleon.cilicili.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chameleon.cilicili.component.KaptchaComponent;
import com.chameleon.cilicili.controller.request.LoginRequset;
import com.chameleon.cilicili.controller.request.RegisterRequest;
import com.chameleon.cilicili.controller.response.ResponseVO;
import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;
import com.chameleon.cilicili.service.impl.UserInfoServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    @Autowired
    private final UserInfoServiceImpl userInfoService;

    @Autowired
    private final KaptchaComponent kaptchaUtils;

    public UserInfoController(UserInfoServiceImpl userInfoService, KaptchaComponent kaptchaUtils) {
        this.userInfoService = userInfoService;
        this.kaptchaUtils = kaptchaUtils;
    }

    public UserInfoServiceImpl getUserInfoService() {
        return userInfoService;
    }

    @GetMapping("/info")
    public ResponseVO<?> getUserInfo(@RequestParam String userId) {
        UserInfo user = userInfoService.findByUserId(userId);
        ResponseVO<UserInfo> response = ResponseVO.success(user);
        return response;
    }

    @PostMapping("/register")
    public ResponseVO<?> register(@Valid @RequestBody RegisterRequest request) {
        kaptchaUtils.validateCaptcha(request.captchaId, request.value);
        userInfoService.register(request.email, request.username, request.password);
        ResponseVO<String> response = ResponseVO.success(null);
        return response;
    }

    @PostMapping("/login")
    public ResponseVO<?> login(HttpServletResponse response,
                               @Valid @RequestBody LoginRequset request) {
        kaptchaUtils.validateCaptcha(request.captchaId, request.value);
        UserInfoDto user = UserInfoDto.fromEntity(
                userInfoService.login(request.email, request.password));
        response.addCookie(null);
        return ResponseVO.success(user);
    }
    
}
