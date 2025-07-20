package com.easylive.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUserInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String nickName;
    private String avatar;
    private Integer expireAt;
    private String token;

    private Integer fansCount;
    private Integer CurrentCoinCount;
    private Integer focusCount;

}
