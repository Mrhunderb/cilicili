package com.chameleon.cilicili.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chameleon.cilicili.dao.impl.UserInfoDaoImpl;
import com.chameleon.cilicili.model.entity.UserInfo;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDaoImpl userInfoDao;

    public UserInfo findByUserId(String userId) {
        return userInfoDao.selectById(userId);
    }

    public void save(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }

    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }

    public void deleteByUserId(String userId) {
        userInfoDao.deleteById(userId);
    }
    
    public List<UserInfo> findAll() {
        return userInfoDao.selectAll();
    }

}
