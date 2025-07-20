package com.easylive.enums;

public enum UserSexEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    SECRECY(3,"保密");

    private int sexCode;
    private String sexName;

    UserSexEnum(int sexCode, String sexName) {
        this.sexCode = sexCode;
        this.sexName = sexName;
    }
    public int getSexCode() {
        return sexCode;
    }

    public String getSexName() {
        return sexName;
    }


}
