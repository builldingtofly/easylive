package com.easylive.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.easylive.enums.PageSize;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.service.VideoInfoFileService;
import com.easylive.mappers.VideoInfoFileMapper;

/**
 * 视频文件信息ServiceImpl
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
@Service("videoInfoFileService")
public class VideoInfoFileServiceImpl implements VideoInfoFileService {

	@Resource
	private VideoInfoFileMapper<VideoInfoFile, VideoInfoFileQuery> videoInfoFileMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<VideoInfoFile> findListByParam(VideoInfoFileQuery query) {
		return this.videoInfoFileMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(VideoInfoFileQuery query) {
		return this.videoInfoFileMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<VideoInfoFile> findListByPage(VideoInfoFileQuery query) {
		Integer count = this.findCountByParam(query);
		SimplePage page = new SimplePage(query.getPageNo(), query.getPageSize(), count);
		query.setSimplePage(page);
		List<VideoInfoFile> list = this.findListByParam(query);
		PaginationResultVO<VideoInfoFile> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getCountPage(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(VideoInfoFile bean) {
		return this.videoInfoFileMapper.insert(bean);
	}

	/**
	 * 新增批量
	 */
	public Integer addBatch(List<VideoInfoFile> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFileMapper.insertBatch(listBean);
	}

	/**
	 * 新增批量或修改
	 */
	public Integer addOrUpdateBatch(List<VideoInfoFile> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFileMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据FileId查询
	 */
	public VideoInfoFile selectByFileId(String fileId) {
		return this.videoInfoFileMapper.selectByFileId(fileId);
	}

	/**
	 * 根据FileId更新
	 */
	public Integer updateByFileId(VideoInfoFile bean, String fileId) {
		return this.videoInfoFileMapper.updateByFileId(bean, fileId);
	}

	/**
	 * 根据FileId删除
	 */
	public Integer deleteByFileId(String fileId) {
		return this.videoInfoFileMapper.deleteByFileId(fileId);
	}

}