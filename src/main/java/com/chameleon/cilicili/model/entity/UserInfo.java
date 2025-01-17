package com.chameleon.cilicili.model.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 业务id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 0：未知，1：男，2：女
     */
    private Byte sex;

    /**
     * 用户介绍
     */
    private String intro;

    /**
     * 入站时间
     */
    private Timestamp joinTime;

    /**
     * 最后登录时间
     */
    private Timestamp lastLogin;

    /**
     * 硬币数量
     */
    private Integer coinNum;

    /**
     * 状态 0：正常，2：禁用
     */
    private Byte status;

    /**
     * 等级 level 0-5
     */
    private Byte level;

    /**
     * 经验值
     */
    private Integer xp;

}

