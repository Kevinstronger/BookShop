package com.test.dao;

import com.test.entity.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface CategoryDao {
    void addCategory(Category category);
    List<Category> findAllCategory();
    Category findById(String id);
}
