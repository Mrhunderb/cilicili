package com.chameleon.cilicili.service;

import java.util.List;

import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;

public interface UserInfoService {

    public UserInfo findByUserId(String userId);

    public void save(UserInfo userInfo);

    public void update(UserInfo userInfo);

    public void deleteByUserId(String userId);

    public List<UserInfoDto> findAll();

}
