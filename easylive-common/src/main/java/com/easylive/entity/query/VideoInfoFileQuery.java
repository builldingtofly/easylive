package com.easylive.entity.query;

/**
 * 视频文件信息查询对象
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public class VideoInfoFileQuery extends BaseQuery {

	/**
	 * 文件id
	 */
	private String fileId;

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
	 * 持续实践（秒）
	 */
	private Integer duration;


	private String fileIdFuzzy;

	private String userIdFuzzy;

	private String videoIdFuzzy;

	private String fileNameFuzzy;

	private String filePathFuzzy;


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileId() {
		return fileId;
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

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getDuration() {
		return duration;
	}


	public void setFileIdFuzzy(String fileIdFuzzy) {
		this.fileIdFuzzy = fileIdFuzzy;
	}
	public String getFileIdFuzzy() {
		return fileIdFuzzy;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}
	public String getUserIdFuzzy() {
		return userIdFuzzy;
	}

	public void setVideoIdFuzzy(String videoIdFuzzy) {
		this.videoIdFuzzy = videoIdFuzzy;
	}
	public String getVideoIdFuzzy() {
		return videoIdFuzzy;
	}

	public void setFileNameFuzzy(String fileNameFuzzy) {
		this.fileNameFuzzy = fileNameFuzzy;
	}
	public String getFileNameFuzzy() {
		return fileNameFuzzy;
	}

	public void setFilePathFuzzy(String filePathFuzzy) {
		this.filePathFuzzy = filePathFuzzy;
	}
	public String getFilePathFuzzy() {
		return filePathFuzzy;
	}


}