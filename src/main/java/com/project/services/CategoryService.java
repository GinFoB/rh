package com.project.services;

import com.project.entities.Category;

import java.util.List;

/**
 * Created by akramkhalifa on 13/07/16.
 */
public interface CategoryService {

    public Category addCategory(Category category);

    public List<Category> categoryList();


    public Category findCategory(Long id);
}
