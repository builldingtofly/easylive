package com.easylive.web.controller;

import com.easylive.component.RedisComponent;
import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.utils.StringTools;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;

@RestController
@RequestMapping("/sysSetting")
@Validated
@RequiredArgsConstructor
public class SysSettingContorller extends BaseController {
    private final RedisComponent redisComponent;
    @RequestMapping("/getSetting")
    public ResponseVO getSetting(){
        return getSuccessResponseVO( redisComponent.getSysSettingDto());
    }
}
