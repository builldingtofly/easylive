package com.easylive.service;

import java.util.List;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 视频文件信息Service
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public interface VideoInfoFileService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfoFile> findListByParam(VideoInfoFileQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(VideoInfoFileQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfoFile> findListByPage(VideoInfoFileQuery query);

	/**
	 * 新增
	 */
	Integer add(VideoInfoFile bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<VideoInfoFile> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<VideoInfoFile> listBean);

	/**
	 * 根据FileId查询
	 */
	VideoInfoFile selectByFileId(String fileId);

	/**
	 * 根据FileId更新
	 */
	Integer updateByFileId(VideoInfoFile bean, String fileId);

	/**
	 * 根据FileId删除
	 */
	Integer deleteByFileId(String fileId);

}