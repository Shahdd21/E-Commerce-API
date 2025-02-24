package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CategoryDTO;
import com.project.e_commerce_api.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    Category findById(Integer id);

    Category save(Category category);

    List<Category> findAllById(List<Integer> categoryIds);
}
