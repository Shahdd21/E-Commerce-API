package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CategoryDTO;
import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryDTO::new).toList();
    }

    @Override
    public CategoryDTO findDtoById(Integer id){
        return new CategoryDTO(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllById(List<Integer> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }
}
