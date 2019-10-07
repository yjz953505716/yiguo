package com.qfedu.yiguo.service;

import com.qfedu.yiguo.entity.Category;

import java.util.List;

public interface CategoryService {

    public Category findAll(Integer id);

    public List<Category> categoryList();

}
