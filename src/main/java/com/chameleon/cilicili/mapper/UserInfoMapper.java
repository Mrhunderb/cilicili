package com.chameleon.cilicili.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chameleon.cilicili.model.entity.UserInfo;

@Mapper
public interface UserInfoMapper {

    UserInfo selectByUserId(String userId);

    UserInfo selectByUsername(String username);

    UserInfo selectByEmail(String email);

    int insert(UserInfo userInfo);

    int update(UserInfo userInfo);

    int deleteByUserId(String userId);
    
}
