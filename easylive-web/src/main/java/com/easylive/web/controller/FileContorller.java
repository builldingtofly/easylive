package com.easylive.web.controller;

import com.easylive.component.RedisComponent;
import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.enums.DateTimePatternEnum;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.utils.DateUtil;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/file")
@Validated
@RequiredArgsConstructor
public class FileContorller extends BaseController {
    private final Appconfig  appconfig;
    private final RedisComponent redisComponent;
    private final FFmpegUtils fFmpegUtils;
    @RequestMapping("/uploadImage")
    public ResponseVO uploadImage(@NotNull @RequestParam("file") MultipartFile imageFile, @NotNull Boolean createThumbnail){
//        做出目录
        String date=DateUtil.format(new Date(), DateTimePatternEnum.YYYY_MM.getPattern());
        String folder=appconfig.getProjectFolder()+Constans.FILE_FOLDER+Constans.FILE_FOLDER_CONVER+"/"+date;
        File file=new File(folder);
        if(!file.exists()){
            file.mkdirs();
        }
//        重新更名
        String originalFilename = imageFile.getOriginalFilename();
        String suffix=StringTools.getFileSuffix(originalFilename);
        String realName=StringTools.getRandomString(10)+"."+suffix;

        String filePath=folder+"/"+realName;
        //存放文件
        try {
            imageFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(createThumbnail){
            fFmpegUtils.createImageThumbnall(filePath);
        }
        return getSuccessResponseVO(Constans.FILE_FOLDER_CONVER+"/"+date+"/"+realName);
    }
    @RequestMapping("/getResource")
    public void getResource(HttpServletResponse response,@NotNull String sourceName){
        if(!StringTools.pathIsOk(sourceName)){
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        String suffix=StringTools.getFileSuffix(sourceName);
        response.setContentType("image/"+suffix.replace(".",""));
        response.setHeader("Cache-Control","max-age=259200");
        readFile(response,sourceName);
    }
    protected void readFile(HttpServletResponse response, String fileName) {
        File file=new File(appconfig.getProjectFolder()+Constans.FILE_FOLDER+fileName);
        if(!file.exists()){
            return;
        }
        try (OutputStream out=response.getOutputStream(); FileInputStream in =new FileInputStream(file)){
            byte[] buffer=new byte[1024];
            int len=0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            throw new BusinessException("读取文件异常");
        } catch (IOException e) {
            throw new BusinessException("IO异常");
        }
    }
    @RequestMapping("/preUploadVideo")
    public ResponseVO preUploadVideo(@NotBlank String fileName, @NotNull Integer chunks){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDtoFromHeader();
        /*
        * 将preloading信息保存到redis中
        * */
        String uploadId = redisComponent.savePreVideoFileInfo(tokenUserInfoDto.getUserId(), fileName, chunks);
        return getSuccessResponseVO(uploadId);
    }
    @RequestMapping("/uploadVideo")
    public ResponseVO uploadVideo(@NotNull MultipartFile chunkFile,@NotBlank String uploadId,@NotNull Integer chunkIndex){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDtoFromHeader();
        UploadingFileDto preVideoFileInfo = redisComponent.getPreVideoFileInfo(tokenUserInfoDto.getUserId(), uploadId);
        SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();
        if(preVideoFileInfo==null){
            throw new BusinessException("文件不存在，请重新上传");
        }
//        判断分片
        if((preVideoFileInfo.getChunkIndex()-1)>chunkIndex){
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
//        判断文件大小
        if(preVideoFileInfo.getFileSize()> (long) sysSettingDto.getVideoSize() *Constans.MB_SIZE){
            throw new BusinessException("文件过大，我放不下");
        }
        String folder = appconfig.getProjectFolder() + Constans.FILE_FOLDER + Constans.FILE_FOLDER_TEMP +"/"+ preVideoFileInfo.getFilePath();
        File targetFile = new File(folder+"/"+chunkIndex);
        try {
            chunkFile.transferTo(targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        preVideoFileInfo.setChunkIndex(chunkIndex);
        preVideoFileInfo.setFileSize(preVideoFileInfo.getFileSize()+chunkFile.getSize());
        redisComponent.updatePreVideoFileInfo(tokenUserInfoDto.getUserId(),preVideoFileInfo);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/delUploadVideo")
    public ResponseVO delUploadVideo(@NotBlank String uploadId){
        TokenUserInfoDto tokenUserInfoDto=getTokenUserInfoDtoFromHeader();
        UploadingFileDto preVideoFileInfo = redisComponent.getPreVideoFileInfo(tokenUserInfoDto.getUserId(), uploadId);
        String folder = appconfig.getProjectFolder() + Constans.FILE_FOLDER + Constans.FILE_FOLDER_TEMP +"/"+ preVideoFileInfo.getFilePath();
        /*
        * 删除文件
        * */
        File filefolder=new File(folder);
        boolean result = recursionDelete(filefolder);
        /*
         * 删除redis中的信息
         * */
        redisComponent.delPreVideoFileInfo(tokenUserInfoDto.getUserId(), uploadId);
        return getSuccessResponseVO(uploadId);
    }
    public static boolean recursionDelete(File file){
        if(file.isDirectory()&&file.exists()){
            File[] files=file.listFiles();
            if (files!=null){
                for (File f:files){
                    recursionDelete(f);
                }
            }
        }
        return file.delete();
    }
}
