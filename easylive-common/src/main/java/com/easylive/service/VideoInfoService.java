package com.easylive.service;

import java.util.List;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 视频信息Service
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfo> findListByParam(VideoInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(VideoInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfo> findListByPage(VideoInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(VideoInfo bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<VideoInfo> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<VideoInfo> listBean);

	/**
	 * 根据VideoId查询
	 */
	VideoInfo selectByVideoId(String videoId);

	/**
	 * 根据VideoId更新
	 */
	Integer updateByVideoId(VideoInfo bean, String videoId);

	/**
	 * 根据VideoId删除
	 */
	Integer deleteByVideoId(String videoId);

}