package com.easylive.entity.query;

import lombok.Data;

/**
 * 分类信息查询对象
 * @auther: ccy
 * @date: 2025-07-10 23:58
 */
@Data
public class CategoryInfoQuery extends BaseQuery {

	/**
	 * 自增分类id
	 */
	private Integer categoryId;

	/**
	 * 分类编码
	 */
	private String categoryCode;

	/**
	 * 分类名称
	 */
	private String categoryName;

	/**
	 * 父级分类id
	 */
	private Integer pCategoryId;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 背景图
	 */
	private String background;

	/**
	 * 排序号
	 */
	private Integer sort;

	private Integer categoryIdorPCategoryId;

	private String categoryCodeFuzzy;

	private String categoryNameFuzzy;

	private String iconFuzzy;

	private String backgroundFuzzy;

	private Boolean convertToTree;
}