package com.qfedu.yiguo.service.impl;

import com.qfedu.yiguo.dao.CategoryDao;
import com.qfedu.yiguo.entity.Category;
import com.qfedu.yiguo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryDao categoryDao;

    @Override
    public Category findAll(Integer id) {
        Category category = categoryDao.findAll(id);
        if (category == null){
            throw new RuntimeException("加载异常");
        }
        return category;
    }

    @Override
    public List<Category> categoryList() {
        List<Category> categories = categoryDao.categoryList();
        return categories;
    }
}
