package com.easylive.enums;

import org.apache.catalina.User;

public enum UserStatusEnum {
    ENABLE(1, "正常"),
    DISABLE(0, "禁用");

    private Integer status;
    private String desc;

    UserStatusEnum( int status,String desc) {
        this.desc = desc;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
//    public static UserStatusEnum getByStatus(Integer status){
//
//    }
}
