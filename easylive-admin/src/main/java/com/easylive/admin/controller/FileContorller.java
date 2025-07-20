package com.easylive.admin.controller;

import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.enums.DateTimePatternEnum;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.utils.DateUtil;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Validated
@RequiredArgsConstructor
public class FileContorller extends BaseController {
    private final Appconfig  appconfig;
    private final FFmpegUtils fFmpegUtils;
    @RequestMapping("/uploadImage")
    public ResponseVO uploadImage(@NotNull  MultipartFile file, @NotNull Boolean createThumbnail) throws IOException {
        //定义文件目录
        String mouth= DateUtil.format(new Date(), DateTimePatternEnum.YYYY_MM.getPattern());
        //获取文件配置路径
        String folder=appconfig.getProjectFolder()+ Constans.FILE_FOLDER+Constans.FILE_FOLDER_CONVER+"/"+mouth;
        //创建文件目录 若不存在则创建牡蛎
        File folderFile= new File(folder);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        //重定义文件名称
        String fileName = file.getOriginalFilename();
        //获取后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        //定义文件名
        String realFileName = StringTools.getRandomString(Constans.LENGTH_30)+fileSuffix;
        //定义文件路径
        String filePath = folder+"/"+realFileName;
        //创建文件
        file.transferTo(new File(filePath));
        //判断是否生成缩略图
        if(createThumbnail){
            //生成缩略图
            fFmpegUtils.createImageThumbnall(filePath);
        }
        return getSuccessResponseVO(Constans.FILE_FOLDER_CONVER+"/"+mouth+"/"+realFileName);
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
}
