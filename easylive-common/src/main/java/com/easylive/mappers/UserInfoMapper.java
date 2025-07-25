package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 用户信息Mapper
 * @auther: ccy
 * @date: 2025-07-05 00:19
 */
public interface UserInfoMapper<T, P> extends BaseMapper {

	/**
	 * 根据UserId查询
	 */
	T selectByUserId (@Param("userId") String userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateByUserId (@Param("bean") T t, @Param("userId") String userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteByUserId (@Param("userId") String userId);

	/**
     * 根据Email查询
     */
	T selectByEmail (@Param("email") String email);

	/**
	 * 根据Email更新
	 */
	Integer updateByEmail (@Param("bean") T t, @Param("email") String email);

	/**
	 * 根据Email删除
	 */
	Integer deleteByEmail (@Param("email") String email);

	/**
	 * 根据NickName查询
	 */
	T selectByNickName (@Param("nickName") String nickName);

	/**
	 * 根据NickName更新
	 */
	Integer updateByNickName (@Param("bean") T t, @Param("nickName") String nickName);

	/**
	 * 根据NickName删除
	 */
	Integer deleteByNickName (@Param("nickName") String nickName);

}