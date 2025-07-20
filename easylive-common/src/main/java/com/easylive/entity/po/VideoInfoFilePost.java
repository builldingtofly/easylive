package com.easylive.entity.po;

import java.io.Serializable;
/**
 * 视频文件信息
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public class VideoInfoFilePost implements Serializable {

	/**
	 * 文件id
	 */
	private String fileId;

	/**
	 * 上传id
	 */
	private String uploadId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 视频id
	 */
	private String videoId;

	/**
	 * 文件索引
	 */
	private Integer fileIndex;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 0：无更新 1：有更新
	 */
	private Integer updateType;

	/**
	 * 0转码中 1转码成功 2转码失败
	 */
	private Integer transferResult;

	/**
	 * 持续实践（秒）
	 */
	private Integer duration;


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setFileIndex(Integer fileIndex) {
		this.fileIndex = fileIndex;
	}
	public Integer getFileIndex() {
		return fileIndex;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}
	public Integer getUpdateType() {
		return updateType;
	}
	public void setTransferResult(Integer transferResult) {
		this.transferResult = transferResult;
	}
	public Integer getTransferResult() {
		return transferResult;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "文件id:" + (fileId == null ? "空" : fileId) + ",上传id:" + (uploadId == null ? "空" : uploadId) + ",用户id:" + (userId == null ? "空" : userId) + ",视频id:" + (videoId == null ? "空" : videoId) + ",文件索引:" + (fileIndex == null ? "空" : fileIndex) + ",文件名称:" + (fileName == null ? "空" : fileName) + ",文件大小:" + (fileSize == null ? "空" : fileSize) + ",文件路径:" + (filePath == null ? "空" : filePath) + ",0：无更新 1：有更新:" + (updateType == null ? "空" : updateType) + ",0转码中 1转码成功 2转码失败:" + (transferResult == null ? "空" : transferResult) + ",持续实践（秒）:" + (duration == null ? "空" : duration);
	}

}