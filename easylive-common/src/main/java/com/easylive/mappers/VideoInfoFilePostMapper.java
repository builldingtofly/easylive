package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 视频文件信息Mapper
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoFilePostMapper<T, P> extends BaseMapper {

	/**
	 * 根据FileId查询
	 */
	T selectByFileId (@Param("fileId") String fileId);

	/**
	 * 根据FileId更新
	 */
	Integer updateByFileId (@Param("bean") T t, @Param("fileId") String fileId);

	/**
	 * 根据FileId删除
	 */
	Integer deleteByFileId (@Param("fileId") String fileId);

	/**
	 * 根据UploadIdAndUserId查询
	 */
	T selectByUploadIdAndUserId (@Param("uploadId") String uploadId, @Param("userId") String userId);

	/**
	 * 根据UploadIdAndUserId更新
	 */
	Integer updateByUploadIdAndUserId (@Param("bean") T t, @Param("uploadId") String uploadId, @Param("userId") String userId);

	/**
	 * 根据UploadIdAndUserId删除
	 */
	Integer deleteByUploadIdAndUserId (@Param("uploadId") String uploadId, @Param("userId") String userId);

}