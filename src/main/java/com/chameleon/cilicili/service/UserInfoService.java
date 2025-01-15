package com.chameleon.cilicili.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chameleon.cilicili.mapper.UserInfoMapper;
import com.chameleon.cilicili.model.entity.UserInfo;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo findByUserId(String userId) {
        return userInfoMapper.selectByUserId(userId);
    }

    public UserInfo findByUsername(String username) {
        return userInfoMapper.selectByUsername(username);
    }

}
