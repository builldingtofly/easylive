package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 视频信息Mapper
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoPostMapper<T, P> extends BaseMapper {

	/**
	 * 根据VideoId查询
	 */
	T selectByVideoId (@Param("videoId") String videoId);

	/**
	 * 根据VideoId更新
	 */
	Integer updateByVideoId (@Param("bean") T t, @Param("videoId") String videoId);

	/**
	 * 根据VideoId删除
	 */
	Integer deleteByVideoId (@Param("videoId") String videoId);

}