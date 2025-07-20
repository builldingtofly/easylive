package com.easylive.mappers;

import com.easylive.entity.query.CategoryInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类信息Mapper
 * @auther: ccy
 * @date: 2025-07-10 23:58
 */
public interface CategoryInfoMapper<T, P> extends BaseMapper {

	/**
	 * 根据CategoryId查询
	 */
	T selectByCategoryId (@Param("categoryId") Integer categoryId);

	/**
	 * 根据CategoryId更新
	 */
	Integer updateByCategoryId (@Param("bean") T t, @Param("categoryId") Integer categoryId);

	/**
	 * 根据CategoryId删除
	 */
	Integer deleteByCategoryId (@Param("categoryId") Integer categoryId);

	/**
	 * 根据CategoryCode查询
	 */
	T selectByCategoryCode (@Param("categoryCode") String categoryCode);

	/**
	 * 根据CategoryCode更新
	 */
	Integer updateByCategoryCode (@Param("bean") T t, @Param("categoryCode") String categoryCode);

	/**
	 * 根据CategoryCode删除
	 */
	Integer deleteByCategoryCode (@Param("categoryCode") String categoryCode);

	Integer selectSortMax(@Param("pCategoryId")Integer pCategoryId);

	void deletebyByParam(@Param("query") CategoryInfoQuery categoryInfoQuery);

    void updataSortBatch(@Param("categoryInfoList") List<T> categoryInfoList);
}