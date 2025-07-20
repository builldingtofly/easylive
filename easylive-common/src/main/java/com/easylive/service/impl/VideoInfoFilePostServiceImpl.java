package com.easylive.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.easylive.enums.PageSize;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.mappers.VideoInfoFilePostMapper;

/**
 * 视频文件信息ServiceImpl
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
@Service("videoInfoFilePostService")
public class VideoInfoFilePostServiceImpl implements VideoInfoFilePostService {

	@Resource
	private VideoInfoFilePostMapper<VideoInfoFilePost, VideoInfoFilePostQuery> videoInfoFilePostMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<VideoInfoFilePost> findListByParam(VideoInfoFilePostQuery query) {
		return this.videoInfoFilePostMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(VideoInfoFilePostQuery query) {
		return this.videoInfoFilePostMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<VideoInfoFilePost> findListByPage(VideoInfoFilePostQuery query) {
		Integer count = this.findCountByParam(query);
		SimplePage page = new SimplePage(query.getPageNo(), query.getPageSize(), count);
		query.setSimplePage(page);
		List<VideoInfoFilePost> list = this.findListByParam(query);
		PaginationResultVO<VideoInfoFilePost> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getCountPage(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(VideoInfoFilePost bean) {
		return this.videoInfoFilePostMapper.insert(bean);
	}

	/**
	 * 新增批量
	 */
	public Integer addBatch(List<VideoInfoFilePost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFilePostMapper.insertBatch(listBean);
	}

	/**
	 * 新增批量或修改
	 */
	public Integer addOrUpdateBatch(List<VideoInfoFilePost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFilePostMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据FileId查询
	 */
	public VideoInfoFilePost selectByFileId(String fileId) {
		return this.videoInfoFilePostMapper.selectByFileId(fileId);
	}

	/**
	 * 根据FileId更新
	 */
	public Integer updateByFileId(VideoInfoFilePost bean, String fileId) {
		return this.videoInfoFilePostMapper.updateByFileId(bean, fileId);
	}

	/**
	 * 根据FileId删除
	 */
	public Integer deleteByFileId(String fileId) {
		return this.videoInfoFilePostMapper.deleteByFileId(fileId);
	}

	/**
	 * 根据UploadIdAndUserId查询
	 */
	public VideoInfoFilePost selectByUploadIdAndUserId(String uploadId, String userId) {
		return this.videoInfoFilePostMapper.selectByUploadIdAndUserId(uploadId, userId);
	}

	/**
	 * 根据UploadIdAndUserId更新
	 */
	public Integer updateByUploadIdAndUserId(VideoInfoFilePost bean, String uploadId, String userId) {
		return this.videoInfoFilePostMapper.updateByUploadIdAndUserId(bean, uploadId, userId);
	}

	/**
	 * 根据UploadIdAndUserId删除
	 */
	public Integer deleteByUploadIdAndUserId(String uploadId, String userId) {
		return this.videoInfoFilePostMapper.deleteByUploadIdAndUserId(uploadId, userId);
	}

}