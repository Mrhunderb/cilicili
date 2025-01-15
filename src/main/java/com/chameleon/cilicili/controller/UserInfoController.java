package com.chameleon.cilicili.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chameleon.cilicili.service.UserInfoService;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    @Autowired
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestParam String userId) {
        return ResponseEntity.ok(userInfoService.findByUserId(userId));
    }
    
}
