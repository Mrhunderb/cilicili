package com.chameleon.cilicili.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chameleon.cilicili.controller.response.ResponseVO;
import com.chameleon.cilicili.model.entity.UserInfo;
import com.chameleon.cilicili.service.impl.UserInfoServiceImpl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    @Autowired
    private final UserInfoServiceImpl userInfoService;

    public UserInfoController(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

    public UserInfoServiceImpl getUserInfoService() {
        return userInfoService;
    }

    @GetMapping("/info")
    public ResponseVO<?> getUserInfo(@RequestParam String userId) {
        UserInfo user = userInfoService.findByUserId(userId);
        ResponseVO<UserInfo> response = new ResponseVO<UserInfo>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(user);
        return response;
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(@NotEmpty @Email String email, 
                                      @NotEmpty @Size(max = 20) String username,
                                      @NotEmpty @Size(max = 50) String password) {
        return ResponseEntity.ok(new String());
    }
    
}
