package com.easylive.web.controller;

import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.CategoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {
    private final CategoryInfoService categoryInfoService;

    @RequestMapping("/loadAllCategory")
    public ResponseVO loadCategory() {
        List<CategoryInfo> aiiCategoryList = categoryInfoService.getAIICategoryList();
        return getSuccessResponseVO(aiiCategoryList);
    }
}
