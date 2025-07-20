package com.easylive.service;

import java.util.List;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 分类信息Service
 * @auther: ccy
 * @date: 2025-07-10 23:58
 */
public interface CategoryInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<CategoryInfo> findListByParam(CategoryInfoQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(CategoryInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<CategoryInfo> findListByPage(CategoryInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(CategoryInfo bean);

	/**
	 * 新增批量
	 */
	Integer addBatch(List<CategoryInfo> listBean);

	/**
	 * 新增批量或修改
	 */
	Integer addOrUpdateBatch(List<CategoryInfo> listBean);

	/**
	 * 根据CategoryId查询
	 */
	CategoryInfo selectByCategoryId(Integer categoryId);

	/**
	 * 根据CategoryId更新
	 */
	Integer updateByCategoryId(CategoryInfo bean, Integer categoryId);

	/**
	 * 根据CategoryId删除
	 */
	Integer deleteByCategoryId(Integer categoryId);

	/**
	 * 根据CategoryCode查询
	 */
	CategoryInfo selectByCategoryCode(String categoryCode);

	/**
	 * 根据CategoryCode更新
	 */
	Integer updateByCategoryCode(CategoryInfo bean, String categoryCode);

	/**
	 * 根据CategoryCode删除
	 */
	Integer deleteByCategoryCode(String categoryCode);

    void saveCategory(CategoryInfo categoryInfo);

	void delCategory(Integer categoryId);

	void changeSort(Integer pCategoryId, String categoryIds);
	List<CategoryInfo> getAIICategoryList();
}