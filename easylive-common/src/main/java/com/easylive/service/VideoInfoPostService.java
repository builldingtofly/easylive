package com.easylive.service;

import java.util.List;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.VideoInfoPostQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 视频信息Service
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoPostService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfoPost> findListByParam(VideoInfoPostQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(VideoInfoPostQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfoPost> findListByPage(VideoInfoPostQuery query);

	/**
	 * 新增
	 */
	Integer add(VideoInfoPost bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<VideoInfoPost> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<VideoInfoPost> listBean);

	/**
	 * 根据VideoId查询
	 */
	VideoInfoPost selectByVideoId(String videoId);

	/**
	 * 根据VideoId更新
	 */
	Integer updateByVideoId(VideoInfoPost bean, String videoId);

	/**
	 * 根据VideoId删除
	 */
	Integer deleteByVideoId(String videoId);

}