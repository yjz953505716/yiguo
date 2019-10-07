package com.qfedu.yiguo.dao;

import com.qfedu.yiguo.entity.Category;

import java.util.List;

public interface CategoryDao {

    public Category findAll(Integer id);

    public List<Category> categoryList();

}
