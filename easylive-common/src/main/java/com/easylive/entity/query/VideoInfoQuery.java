package com.easylive.entity.query;

import java.util.Date;
/**
 * 视频信息查询对象
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public class VideoInfoQuery extends BaseQuery {

	/**
	 * 视频id
	 */
	private String videoId;

	/**
	 * 视频封面
	 */
	private String videoCover;

	/**
	 * 视频名称
	 */
	private String videoName;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后更新实践
	 */
	private Date lastUpdateTime;

	/**
	 * 父级分类id
	 */
	private Integer pCategoryId;

	/**
	 * 分类id
	 */
	private Integer categoryId;

	/**
	 * 0：转码中 1转码失败 2待审核 3审核成功 4审核失败
	 */
	private Integer status;

	/**
	 * 0：自制作 1：转载
	 */
	private Integer postType;

	/**
	 * 原资源说明
	 */
	private String originInfo;

	/**
	 * 标签
	 */
	private String tags;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 互动设置
	 */
	private String interaction;

	/**
	 * 持续时间（秒）
	 */
	private Integer duration;

	/**
	 * 播放数量
	 */
	private Integer playCount;

	/**
	 * 点赞数量
	 */
	private Integer likeCount;

	/**
	 * 弹幕数量
	 */
	private Integer danmuCount;

	/**
	 * 评论数量
	 */
	private Integer commentCount;

	/**
	 * 硬币数量
	 */
	private Integer coinCount;

	/**
	 * 收藏数量
	 */
	private Integer collectCount;

	/**
	 * 0未推荐 1已推荐
	 */
	private Integer recommendType;

	/**
	 * 最后播放时间
	 */
	private Date lastPlayTime;


	private String videoIdFuzzy;

	private String videoCoverFuzzy;

	private String videoNameFuzzy;

	private String userIdFuzzy;

	private String createTimeStart;

	private String createTimeEnd;

	private String lastUpdateTimeStart;

	private String lastUpdateTimeEnd;

	private String originInfoFuzzy;

	private String tagsFuzzy;

	private String introductionFuzzy;

	private String interactionFuzzy;

	private String lastPlayTimeStart;

	private String lastPlayTimeEnd;


	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoId() {
		return videoId;
	}

	public void setVideoCover(String videoCover) {
		this.videoCover = videoCover;
	}
	public String getVideoCover() {
		return videoCover;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoName() {
		return videoName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setPCategoryId(Integer pCategoryId) {
		this.pCategoryId = pCategoryId;
	}
	public Integer getPCategoryId() {
		return pCategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}

	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public Integer getPostType() {
		return postType;
	}

	public void setOriginInfo(String originInfo) {
		this.originInfo = originInfo;
	}
	public String getOriginInfo() {
		return originInfo;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIntroduction() {
		return introduction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	public String getInteraction() {
		return interaction;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getDuration() {
		return duration;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}
	public Integer getPlayCount() {
		return playCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setDanmuCount(Integer danmuCount) {
		this.danmuCount = danmuCount;
	}
	public Integer getDanmuCount() {
		return danmuCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCoinCount(Integer coinCount) {
		this.coinCount = coinCount;
	}
	public Integer getCoinCount() {
		return coinCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setRecommendType(Integer recommendType) {
		this.recommendType = recommendType;
	}
	public Integer getRecommendType() {
		return recommendType;
	}

	public void setLastPlayTime(Date lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
	public Date getLastPlayTime() {
		return lastPlayTime;
	}


	public void setVideoIdFuzzy(String videoIdFuzzy) {
		this.videoIdFuzzy = videoIdFuzzy;
	}
	public String getVideoIdFuzzy() {
		return videoIdFuzzy;
	}

	public void setVideoCoverFuzzy(String videoCoverFuzzy) {
		this.videoCoverFuzzy = videoCoverFuzzy;
	}
	public String getVideoCoverFuzzy() {
		return videoCoverFuzzy;
	}

	public void setVideoNameFuzzy(String videoNameFuzzy) {
		this.videoNameFuzzy = videoNameFuzzy;
	}
	public String getVideoNameFuzzy() {
		return videoNameFuzzy;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}
	public String getUserIdFuzzy() {
		return userIdFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setLastUpdateTimeStart(String lastUpdateTimeStart) {
		this.lastUpdateTimeStart = lastUpdateTimeStart;
	}
	public String getLastUpdateTimeStart() {
		return lastUpdateTimeStart;
	}

	public void setLastUpdateTimeEnd(String lastUpdateTimeEnd) {
		this.lastUpdateTimeEnd = lastUpdateTimeEnd;
	}
	public String getLastUpdateTimeEnd() {
		return lastUpdateTimeEnd;
	}

	public void setOriginInfoFuzzy(String originInfoFuzzy) {
		this.originInfoFuzzy = originInfoFuzzy;
	}
	public String getOriginInfoFuzzy() {
		return originInfoFuzzy;
	}

	public void setTagsFuzzy(String tagsFuzzy) {
		this.tagsFuzzy = tagsFuzzy;
	}
	public String getTagsFuzzy() {
		return tagsFuzzy;
	}

	public void setIntroductionFuzzy(String introductionFuzzy) {
		this.introductionFuzzy = introductionFuzzy;
	}
	public String getIntroductionFuzzy() {
		return introductionFuzzy;
	}

	public void setInteractionFuzzy(String interactionFuzzy) {
		this.interactionFuzzy = interactionFuzzy;
	}
	public String getInteractionFuzzy() {
		return interactionFuzzy;
	}

	public void setLastPlayTimeStart(String lastPlayTimeStart) {
		this.lastPlayTimeStart = lastPlayTimeStart;
	}
	public String getLastPlayTimeStart() {
		return lastPlayTimeStart;
	}

	public void setLastPlayTimeEnd(String lastPlayTimeEnd) {
		this.lastPlayTimeEnd = lastPlayTimeEnd;
	}
	public String getLastPlayTimeEnd() {
		return lastPlayTimeEnd;
	}


}