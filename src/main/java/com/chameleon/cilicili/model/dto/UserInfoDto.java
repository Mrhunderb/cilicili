package com.chameleon.cilicili.model.dto;

import java.sql.Timestamp;

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

}
