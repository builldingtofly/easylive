package com.easylive.component;

import co.elastic.clients.util.DateTime;
import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.enums.DateTimePatternEnum;
import com.easylive.redis.RedisUtils;

import com.easylive.utils.DateUtil;
import com.easylive.utils.StringTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisComponent {
    private final RedisUtils redisUtils;
    private final Appconfig appconfig;

    public String saveCheckCode(String code) {
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setex(Constans.REDIS_KEY_CHECK_CODE + checkCodeKey, code, Constans.REDIS_KEY_EXPIRES_ONE_MIN * 10);
        return checkCodeKey;
    }

    public String getCheckCode(String checkCodeKey, String code) {
        return (String) redisUtils.get(Constans.REDIS_KEY_CHECK_CODE + checkCodeKey);
    }

    public void cleanCheckCode(String checkCodeKey) {
        redisUtils.delete(Constans.REDIS_KEY_CHECK_CODE + checkCodeKey);
    }

    public void saveTokenInfo(TokenUserInfoDto tokenUserInfoDto) {
        String token = UUID.randomUUID().toString();
        tokenUserInfoDto.setToken(token);
        tokenUserInfoDto.setExpireAt(Constans.TIME_SECONDS_DAY);
        redisUtils.setex(Constans.REDIS_KEY_TOKEN_WEB + token, tokenUserInfoDto, Constans.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }

    public void cleanTokenInfo(String token) {
        redisUtils.delete(Constans.REDIS_KEY_TOKEN_WEB + token);
    }

    public TokenUserInfoDto getTokenInfoDto(String token) {
        if(token==null){
            throw new RuntimeException("token is null");
        }
        return (TokenUserInfoDto) redisUtils.get(Constans.REDIS_KEY_TOKEN_WEB +token);
    }


    public String saveTokenInfo4Admin(String account) {
        String token = UUID.randomUUID().toString();
        redisUtils.setex(Constans.REDIS_KEY_TOKEN_ADMIN + token, account, Constans.REDIS_KEY_EXPIRES_ONE_DAY);
        return token;
    }
    public void cleanTokenInfo4Admin(String token) {
        redisUtils.delete(Constans.REDIS_KEY_TOKEN_ADMIN + token);
    }
    public String getTokenInfo4Admin(String token) {
        redisUtils.setex(Constans.REDIS_KEY_TOKEN_ADMIN + token, token, Constans.REDIS_KEY_EXPIRES_ONE_DAY);
        return token;
    }

    public void saveCategoryInfoToRedis(List<CategoryInfo> categoryInfo) {
        redisUtils.set(Constans.REDIS_KEY_CATEGORY_LIST , categoryInfo);
    }
    public List<CategoryInfo> getCategoryInfoList() {
        return (List<CategoryInfo>)redisUtils.get(Constans.REDIS_KEY_CATEGORY_LIST);
    }
    public String savePreVideoFileInfo(String userId,String fileName,Integer chunks) {
        //定义uploadId
        String uploadId = StringTools.getRandomString(Constans.LENGTH_15);
        //生成目录
        String dir =DateUtil.format(new Date(), DateTimePatternEnum.YYYY_MM_DD.getPattern());
        String filePath=dir+"/"+userId+uploadId;
        String folder=appconfig.getProjectFolder()+Constans.FILE_FOLDER+Constans.FILE_FOLDER_TEMP+"/"+filePath;
        File folderFile=new File(folder);
        if(!folderFile.exists()){
            if(!folderFile.mkdirs()){
                log.error("创建目录失败");
                throw new RuntimeException("创建目录失败");
            }
        }
        UploadingFileDto uploadingFileDto=new UploadingFileDto();
        uploadingFileDto.setChunks(chunks);
        uploadingFileDto.setFileName(fileName);
        uploadingFileDto.setUploadId(uploadId);
        uploadingFileDto.setChunkIndex(0);
        uploadingFileDto.setFilePath(filePath);
        redisUtils.setex(Constans.REDIS_KEY_UPLOADING_FILE+userId+uploadId,uploadingFileDto,Constans.REDIS_KEY_EXPIRES_ONE_DAY);
        return uploadId;
    }
    public UploadingFileDto getPreVideoFileInfo(String userId,String uploadId) {
        return (UploadingFileDto)redisUtils.get(Constans.REDIS_KEY_UPLOADING_FILE+userId+uploadId);
    }
    public void updatePreVideoFileInfo(String userId,UploadingFileDto uploadingFileDto) {
        redisUtils.setex(Constans.REDIS_KEY_UPLOADING_FILE+userId+uploadingFileDto.getUploadId(),uploadingFileDto,Constans.REDIS_KEY_EXPIRES_ONE_DAY);
    }
    public SysSettingDto getSysSettingDto() {
        SysSettingDto sysSettingDto = (SysSettingDto) redisUtils.get(Constans.REDIS_KEY_SYS_SETTING);
        if(sysSettingDto==null){
            //没有拿到给默认
            return new SysSettingDto();
        }
        return sysSettingDto;
    }

    public void delPreVideoFileInfo(String userId, String uploadId) {
        redisUtils.delete(Constans.REDIS_KEY_UPLOADING_FILE+userId+uploadId);
    }
}
