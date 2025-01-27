package com.chameleon.cilicili.service;

import java.util.List;

import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;

public interface UserInfoService {

    public UserInfo findByUserId(String userId);

    public UserInfo findByEmail(String email);

    public UserInfo findByUsername(String username);

    public void save(UserInfo userInfo);

    public void update(UserInfo userInfo);

    public void deleteByUserId(String userId);

    public List<UserInfoDto> findAll();

    public Boolean isExist(String userId);

    public void register(String email, String username, String password);

    public void login(String email, String password);

}
