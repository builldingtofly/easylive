package com.easylive.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easylive.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 用户信息
 * @auther: ccy
 * @date: 2025-07-05 00:19
 */
public class UserInfo implements Serializable {

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 0：女 1：男 2：未知
	 */
	private Integer sex;

	/**
	 * 出生日期
	 */
	private String birthday;

	/**
	 * 学校名称
	 */
	private String school;

	/**
	 * 个人简介
	 */
	private String personIntroduction;

	/**
	 * 加入时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	/**
	 * 最后登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 最后登录ip
	 */
	private String lastLoginIp;

	/**
	 * 0:禁用 1：正常
	 */
	@JsonIgnore
	private Integer status;

	/**
	 * 空间公告
	 */
	private String noticeInfo;

	/**
	 * 硬币总数量
	 */
	private Integer totalCoinCount;

	/**
	 * 目前硬币数量
	 */
	private Integer currentCoinCount;

	/**
	 * 主题
	 */
	private Integer theme;


	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getSex() {
		return sex;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
	public void setPersonIntroduction(String personIntroduction) {
		this.personIntroduction = personIntroduction;
	}
	public String getPersonIntroduction() {
		return personIntroduction;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setTotalCoinCount(Integer totalCoinCount) {
		this.totalCoinCount = totalCoinCount;
	}
	public Integer getTotalCoinCount() {
		return totalCoinCount;
	}
	public void setCurrentCoinCount(Integer currentCoinCount) {
		this.currentCoinCount = currentCoinCount;
	}
	public Integer getCurrentCoinCount() {
		return currentCoinCount;
	}
	public void setTheme(Integer theme) {
		this.theme = theme;
	}
	public Integer getTheme() {
		return theme;
	}

	@Override
	public String toString() {
		return "用户id:" + (userId == null ? "空" : userId) + ",昵称:" + (nickName == null ? "空" : nickName) + ",邮箱:" + (email == null ? "空" : email) + ",密码:" + (password == null ? "空" : password) + ",0：女 1：男 2：未知:" + (sex == null ? "空" : sex) + ",出生日期:" + (birthday == null ? "空" : birthday) + ",学校名称:" + (school == null ? "空" : school) + ",个人简介:" + (personIntroduction == null ? "空" : personIntroduction) + ",加入时间:" + (joinTime == null ? "空" : DateUtil.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",最后登录时间:" + (lastLoginTime == null ? "空" : DateUtil.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",最后登录ip:" + (lastLoginIp == null ? "空" : lastLoginIp) + ",0:禁用 1：正常:" + (status == null ? "空" : status) + ",空间公告:" + (noticeInfo == null ? "空" : noticeInfo) + ",硬币总数量:" + (totalCoinCount == null ? "空" : totalCoinCount) + ",目前硬币数量:" + (currentCoinCount == null ? "空" : currentCoinCount) + ",主题:" + (theme == null ? "空" : theme);
	}

}