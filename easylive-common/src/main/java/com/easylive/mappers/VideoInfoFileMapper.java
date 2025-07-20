package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 视频文件信息Mapper
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoFileMapper<T, P> extends BaseMapper {

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

}