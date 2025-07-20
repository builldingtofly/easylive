package com.easylive.admin.controller;

import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.service.CategoryInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {
    private final CategoryInfoService categoryInfoService;

    @RequestMapping("/loadCategory")
    public ResponseVO loadCategory(CategoryInfoQuery categoryInfoQuery) {
        categoryInfoQuery.setOrderBy("sort asc");
        categoryInfoQuery.setConvertToTree(true);
        List<CategoryInfo> listByParam = categoryInfoService.findListByParam(categoryInfoQuery);
        return getSuccessResponseVO(listByParam);
    }

    @RequestMapping("/saveCategory")
    public ResponseVO saveCategory(
            @NotNull Integer pCategoryId,
            Integer categoryId,
            @NotEmpty String categoryCode,
            @NotEmpty String categoryName,
            String icon,
            String background
    ) {
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setpCategoryId(pCategoryId);
        categoryInfo.setCategoryId(categoryId);
        categoryInfo.setCategoryCode(categoryCode);
        categoryInfo.setCategoryName(categoryName);
        categoryInfo.setIcon(icon);
        categoryInfo.setBackground(background);
        categoryInfoService.saveCategory(categoryInfo);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/delCategory")
    public ResponseVO delCategory(Integer categoryId) {
        this.categoryInfoService.delCategory(categoryId);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/changeSort")
    public ResponseVO changeSort(
            @NotNull Integer pCategoryId,
            @NotEmpty String categoryIds) {

        this.categoryInfoService.changeSort(pCategoryId,categoryIds);
        return getSuccessResponseVO(null);
    }
}
