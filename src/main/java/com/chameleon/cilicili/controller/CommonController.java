package com.chameleon.cilicili.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chameleon.cilicili.config.kaptcha.KaptchaUtils;
import com.chameleon.cilicili.controller.response.ResponseVO;

@RestController
public class CommonController {

    @Autowired
    private KaptchaUtils kaptchaUtils;
   
    @GetMapping("/captcha")
    public ResponseEntity<?> captcha() throws IOException {
        Map<String, Object> map = kaptchaUtils.createCaptcha();
        ResponseVO<?> r = ResponseVO.success(map);
        return ResponseEntity.ok(r);
    }
    
}
