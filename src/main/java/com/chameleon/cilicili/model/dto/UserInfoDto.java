package com.chameleon.cilicili.model.dto;

import java.sql.Timestamp;

import com.chameleon.cilicili.model.entity.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {

    private String userId;

    private String username;

    private String email;

    private Byte sex;

    private String intro;

    private Timestamp joinTime;

    private Timestamp lastLogin;

    private Integer coinNum;

    private Byte status;

    private Byte level;

    private Integer xp;

    public static UserInfoDto fromEntity(UserInfo user) {
        return UserInfoDto.builder().
            userId(user.getUserId()).
            username(user.getUsername()).
            email(user.getEmail()).
            sex(user.getSex()).
            intro(user.getIntro()).
            joinTime(user.getJoinTime()).
            lastLogin(user.getLastLogin()).
            coinNum(user.getCoinNum()).
            status(user.getStatus()).
            level(user.getLevel()).
            xp(user.getXp()).
        build();
    }

}
