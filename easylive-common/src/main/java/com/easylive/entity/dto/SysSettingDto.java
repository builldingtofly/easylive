package com.easylive.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSettingDto implements Serializable {
    //注册送积分
    private Integer registerCoinCount=10;
    //发布视频奖励积分
    private Integer postVodeiCoinCount=5;
    //文件大小（m）
    private Integer videoSize=10;
    //最高P数
    private Integer videoPCount=10;
    //文件数
    private Integer videoCount=10;
    private Integer commentCount=20;
    private Integer danmuCount=20;

}
