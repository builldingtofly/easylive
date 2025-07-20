package com.easylive.service;

import java.util.List;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 用户信息Service
 * @auther: ccy
 * @date: 2025-07-05 00:19
 */
public interface UserInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<UserInfo> findListByParam(UserInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(UserInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(UserInfo bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<UserInfo> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<UserInfo> listBean);

	/**
	 * 根据UserId查询
	 */
	UserInfo selectByUserId(String userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateByUserId(UserInfo bean, String userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteByUserId(String userId);

	/**
	 * 根据Email查询
	 */
	UserInfo selectByEmail(String email);

	/**
	 * 根据Email更新
	 */
	Integer updateByEmail(UserInfo bean, String email);

	/**
	 * 根据Email删除
	 */
	Integer deleteByEmail(String email);

	/**
	 * 根据NickName查询
	 */
	UserInfo selectByNickName(String nickName);

	/**
	 * 根据NickName更新
	 */
	Integer updateByNickName(UserInfo bean, String nickName);

	/**
	 * 根据NickName删除
	 */
	Integer deleteByNickName(String nickName);

    boolean register(String email, String nickName, String registerPwd);

	TokenUserInfoDto login(String email, String registerPwd, String ip);

	void updateUserInfo(TokenUserInfoDto tokenUserInfoDtoFromHeader, String ip);
}