package com.chameleon.cilicili.model.dto;

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

    private Integer coinNum;

    private Byte level;

    private Integer xp;

    private String token;

    private Long expireTime;

    public static UserInfoDto fromEntity(UserInfo user) {
        return UserInfoDto.builder().
            userId(user.getUserId()).
            username(user.getUsername()).
            email(user.getEmail()).
            sex(user.getSex()).
            intro(user.getIntro()).
            coinNum(user.getCoinNum()).
            level(user.getLevel()).
            xp(user.getXp()).
        build();
    }

}
