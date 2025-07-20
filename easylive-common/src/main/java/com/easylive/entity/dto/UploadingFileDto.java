package com.easylive.entity.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UploadingFileDto implements Serializable {
    private String fileName;
    private String uploadId;
    private Integer chunkIndex;
    private Integer chunks;
    private Long fileSize=0L;
    private String filePath;
}
