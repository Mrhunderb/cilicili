package com.chameleon.cilicili.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chameleon.cilicili.config.redis.RedisUtils;
import com.chameleon.cilicili.dao.impl.UserInfoDaoImpl;
import com.chameleon.cilicili.exception.ServiceException;
import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;
import com.chameleon.cilicili.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDaoImpl userInfoDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserInfo findByUserId(String userId) {
        UserInfo userInfo = userInfoDao.selectById(userId);
        return userInfo;
    }

    @Override
    public UserInfo findByEmail(String email) {
        UserInfo userInfo = userInfoDao.selectByEmail(email);
        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        try {
            userInfoDao.insert(userInfo);
        } catch (Exception e) {
            throw new ServiceException("插入用户信息失败");
        }
    }

    @Override
    public void update(UserInfo userInfo) {
        try {
            userInfoDao.update(userInfo);
        } catch (Exception e) {
            throw new ServiceException("更新用户信息失败");
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        try {
            userInfoDao.deleteById(userId);
        } catch (Exception e) {
            throw new ServiceException("删除用户信息失败");
        }
    }
    
    @Override
    public List<UserInfoDto> findAll() {
        return null;
    }

    @Override
    public Boolean isExist(String userId) {
        UserInfo userInfo = userInfoDao.selectById(userId);
        return userInfo != null;
    }

    @Override
    public void register(String email, String username, String password) {
        UserInfo user = findByEmail(email);
        if (user != null) {
            throw new ServiceException("该邮箱已被注册");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setEmail(email);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setJoinTime(new Timestamp(System.currentTimeMillis()));
        save(userInfo);
    }

}
