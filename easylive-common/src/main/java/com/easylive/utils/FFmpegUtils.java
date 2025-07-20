package com.easylive.utils;

import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FFmpegUtils {
    private final Appconfig appconfig;
    public  void createImageThumbnall(String filePath){
        String CMD="ffmpeg -i \"%s\" -vf scale=200:-1 \"%s\"";
        CMD = String.format(CMD, filePath, filePath + Constans.IMAGE_THUMBNAIL_SUFFIX);
        ProcessUtils.executeCommand(CMD,appconfig.getShowLog());
    }
}
