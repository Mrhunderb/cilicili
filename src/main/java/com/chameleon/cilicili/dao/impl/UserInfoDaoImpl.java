package com.chameleon.cilicili.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chameleon.cilicili.dao.Dao;
import com.chameleon.cilicili.model.entity.UserInfo;

@Mapper
public interface UserInfoDaoImpl extends Dao {

    @Override
    public void insert(Object obj);

    @Override
    public void update(Object obj);

    @Override
    public UserInfo selectById(String id);

    public UserInfo selectByEmail(String email);

    @Override
    public void deleteById(String userId);

    @Override
    public List<UserInfo> selectAll();

    public UserInfo selectByUsername(String username);

}
