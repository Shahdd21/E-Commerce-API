package com.project.e_commerce_api.service;

import com.project.e_commerce_api.entity.Category;

public interface CategoryService {

    Category findById(Integer id);

    Category save(Category category);
}
