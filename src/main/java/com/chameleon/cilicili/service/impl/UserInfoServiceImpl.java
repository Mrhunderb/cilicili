package com.chameleon.cilicili.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chameleon.cilicili.config.redis.RedisUtils;
import com.chameleon.cilicili.dao.impl.UserInfoDaoImpl;
import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;
import com.chameleon.cilicili.service.UserInfoService;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDaoImpl userInfoDao;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DefaultKaptcha kaptcha;

    @Override
    public UserInfo findByUserId(String userId) {
        UserInfo userInfo = userInfoDao.selectById(userId);
        kaptcha.createText();
        redisUtils.set("kaptcha", kaptcha.createText());
        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }

    @Override
    public void deleteByUserId(String userId) {
        userInfoDao.deleteById(userId);
    }
    
    @Override
    public List<UserInfoDto> findAll() {
        return null;
    }

}
