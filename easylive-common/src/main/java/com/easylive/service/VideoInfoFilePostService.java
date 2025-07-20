package com.easylive.service;

import java.util.List;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 视频文件信息Service
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoFilePostService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfoFilePost> findListByParam(VideoInfoFilePostQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(VideoInfoFilePostQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfoFilePost> findListByPage(VideoInfoFilePostQuery query);

	/**
	 * 新增
	 */
	Integer add(VideoInfoFilePost bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<VideoInfoFilePost> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<VideoInfoFilePost> listBean);

	/**
	 * 根据FileId查询
	 */
	VideoInfoFilePost selectByFileId(String fileId);

	/**
	 * 根据FileId更新
	 */
	Integer updateByFileId(VideoInfoFilePost bean, String fileId);

	/**
	 * 根据FileId删除
	 */
	Integer deleteByFileId(String fileId);

	/**
	 * 根据UploadIdAndUserId查询
	 */
	VideoInfoFilePost selectByUploadIdAndUserId(String uploadId, String userId);

	/**
	 * 根据UploadIdAndUserId更新
	 */
	Integer updateByUploadIdAndUserId(VideoInfoFilePost bean, String uploadId, String userId);

	/**
	 * 根据UploadIdAndUserId删除
	 */
	Integer deleteByUploadIdAndUserId(String uploadId, String userId);

}