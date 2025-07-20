package com.easylive.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easylive.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 视频信息
 * @auther: ccy
 * @date: 2025-07-18 23:07
 */
public class VideoInfo implements Serializable {

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 最后更新实践
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	@JsonIgnore
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPlayTime;


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

	@Override
	public String toString() {
		return "视频id:" + (videoId == null ? "空" : videoId) + ",视频封面:" + (videoCover == null ? "空" : videoCover) + ",视频名称:" + (videoName == null ? "空" : videoName) + ",用户id:" + (userId == null ? "空" : userId) + ",创建时间:" + (createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",最后更新实践:" + (lastUpdateTime == null ? "空" : DateUtil.format(lastUpdateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",父级分类id:" + (pCategoryId == null ? "空" : pCategoryId) + ",分类id:" + (categoryId == null ? "空" : categoryId) + ",0：转码中 1转码失败 2待审核 3审核成功 4审核失败:" + (status == null ? "空" : status) + ",0：自制作 1：转载:" + (postType == null ? "空" : postType) + ",原资源说明:" + (originInfo == null ? "空" : originInfo) + ",标签:" + (tags == null ? "空" : tags) + ",简介:" + (introduction == null ? "空" : introduction) + ",互动设置:" + (interaction == null ? "空" : interaction) + ",持续时间（秒）:" + (duration == null ? "空" : duration) + ",播放数量:" + (playCount == null ? "空" : playCount) + ",点赞数量:" + (likeCount == null ? "空" : likeCount) + ",弹幕数量:" + (danmuCount == null ? "空" : danmuCount) + ",评论数量:" + (commentCount == null ? "空" : commentCount) + ",硬币数量:" + (coinCount == null ? "空" : coinCount) + ",收藏数量:" + (collectCount == null ? "空" : collectCount) + ",0未推荐 1已推荐:" + (recommendType == null ? "空" : recommendType) + ",最后播放时间:" + (lastPlayTime == null ? "空" : DateUtil.format(lastPlayTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}