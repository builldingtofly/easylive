package com.easylive.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constans.Constans;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.easylive.enums.PageSize;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.service.CategoryInfoService;
import com.easylive.mappers.CategoryInfoMapper;

/**
 * 分类信息ServiceImpl
 * @auther: ccy
 * @date: 2025-07-10 23:58
 */
@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {

	@Resource
	private CategoryInfoMapper<CategoryInfo, CategoryInfoQuery> categoryInfoMapper;

	@Resource
	private RedisComponent redisComponent;
	/**
	 * 根据条件查询列表
	 */
	public List<CategoryInfo> findListByParam(CategoryInfoQuery query) {
		List<CategoryInfo> categoryInfoList = this.categoryInfoMapper.selectList(query);
		System.out.println(categoryInfoList);
		if(query.getConvertToTree()!=null&&query.getConvertToTree()){
            return convertLineToTree(categoryInfoList, Constans.ZERO);
		}
		return categoryInfoList;
	}

	private List<CategoryInfo> convertLineToTree(List<CategoryInfo> dataList,Integer pId){
		List<CategoryInfo> children=new ArrayList<>();
		for (CategoryInfo m : dataList) {
			if(m.getCategoryId()!=null&&m.getpCategoryId()!=null && m.getpCategoryId().equals(pId) ){
				m.setChildren(convertLineToTree(dataList,m.getCategoryId()));
				children.add(m);
			}
		}
		System.out.println(children);
		return children;
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(CategoryInfoQuery query) {
		return this.categoryInfoMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<CategoryInfo> findListByPage(CategoryInfoQuery query) {
		Integer count = this.findCountByParam(query);
		SimplePage page = new SimplePage(query.getPageNo(), query.getPageSize(), count);
		query.setSimplePage(page);
		List<CategoryInfo> list = this.findListByParam(query);
		PaginationResultVO<CategoryInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getCountPage(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(CategoryInfo bean) {
		return this.categoryInfoMapper.insert(bean);
	}

	/**
	 * 新增批量
	 */
	public Integer addBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertBatch(listBean);
	}

	/**
	 * 新增批量或修改
	 */
	public Integer addOrUpdateBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据CategoryId查询
	 */
	public CategoryInfo selectByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.selectByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryId更新
	 */
	public Integer updateByCategoryId(CategoryInfo bean, Integer categoryId) {
		return this.categoryInfoMapper.updateByCategoryId(bean, categoryId);
	}

	/**
	 * 根据CategoryId删除
	 */
	public Integer deleteByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.deleteByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryCode查询
	 */
	public CategoryInfo selectByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.selectByCategoryCode(categoryCode);
	}

	/**
	 * 根据CategoryCode更新
	 */
	public Integer updateByCategoryCode(CategoryInfo bean, String categoryCode) {
		return this.categoryInfoMapper.updateByCategoryCode(bean, categoryCode);
	}

	/**
	 * 根据CategoryCode删除
	 */
	public Integer deleteByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.deleteByCategoryCode(categoryCode);
	}

    @Override
    public void saveCategory(CategoryInfo bean) {
		CategoryInfo dbBean = categoryInfoMapper.selectByCategoryCode(bean.getCategoryCode());
		if
//		新增情况判断
		(bean.getCategoryId()==null&&dbBean!=null||
//		修改情况判断
				bean.getCategoryId()!=null&&dbBean!=null&&!bean.getCategoryId().equals(dbBean.getCategoryId())
		){
			throw new BusinessException("编码已存在");
		}
		//判断新增与更新
		if (bean.getCategoryId() == null) {
			//添加sort值，正常逻辑应该是新增放到现有的后面，又因sort值越大越靠后，则应该找到添加到这个类别的最大值再+1即可
			Integer maxSort = this.categoryInfoMapper.selectSortMax(bean.getpCategoryId());
			bean.setSort(maxSort+1);
			//新增分类
			this.categoryInfoMapper.insert(bean);
		}else{
			this.categoryInfoMapper.updateByCategoryId(bean, bean.getCategoryId());
		}
		saveCategoryInfoToRedis();
	}

	@Override
	public void delCategory(Integer categoryId) {
		//TODO 查询分类下是否有视频 有视频则不可删除

		CategoryInfoQuery categoryInfoQuery =new CategoryInfoQuery();
		categoryInfoQuery.setCategoryIdorPCategoryId(categoryId);
		this.categoryInfoMapper.deletebyByParam(categoryInfoQuery);

		//TODO 刷新缓存
		saveCategoryInfoToRedis();
	}

	@Override
	public void changeSort(Integer pCategoryId, String categoryIds) {
		String[] categoryIdList =categoryIds.split(",");
		List<CategoryInfo> categoryInfoList = new ArrayList<>();
		Integer sort=0;
		for(String m:categoryIdList){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setCategoryId(Integer.valueOf(m));
			categoryInfo.setpCategoryId(pCategoryId);
			categoryInfo.setSort(++sort);
			categoryInfoList.add(categoryInfo);
		}
		this.categoryInfoMapper.updataSortBatch(categoryInfoList);

		// TODO 刷新缓存
		saveCategoryInfoToRedis();

	}

    @Override
    public List<CategoryInfo> getAIICategoryList() {
		List<CategoryInfo> categoryInfoList = redisComponent.getCategoryInfoList();
		if(categoryInfoList==null||categoryInfoList.isEmpty()){
			saveCategoryInfoToRedis();
		}
		return categoryInfoList;
	}

    private void saveCategoryInfoToRedis(){
		CategoryInfoQuery categoryInfoQuery=new CategoryInfoQuery();
		categoryInfoQuery.setOrderBy("sort asc");
		categoryInfoQuery.setConvertToTree(true);
		List<CategoryInfo> listByParam = findListByParam(categoryInfoQuery);
		redisComponent.saveCategoryInfoToRedis(listByParam);
	}
}