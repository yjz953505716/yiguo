package com.qfedu.yiguo.controller;

import com.qfedu.yiguo.common.JsonResult;
import com.qfedu.yiguo.entity.Category;
import com.qfedu.yiguo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description ="商品类型")
@Controller
@ResponseBody
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value="类型商品展示(传入前台是category包含商品集合)", notes="需要传来 类型id")
    @ApiImplicitParam(name = "id", value = "商品类型id")
    @GetMapping("/goodsList")
    public JsonResult<Category> goodsList(Integer id){
        Category category = categoryService.findAll(id);
        if (category == null){
            return new JsonResult(1,"加载异常");
        }
        return new JsonResult(0,category);
    }

    @ApiOperation(value="类型列表展示(类型集合)")
    @GetMapping("/categoryList")
    public JsonResult<List<Category>> categoryList(){
        List<Category> categoryList = categoryService.categoryList();
        if (categoryList==null){
            return new JsonResult(1,"查询异常");
        }
        return new JsonResult(0,categoryList);
    }

}
