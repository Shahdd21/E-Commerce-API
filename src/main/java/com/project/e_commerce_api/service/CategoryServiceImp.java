package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CategoryDTO;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.repository.CategoryRepository;
import com.project.e_commerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository,ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryDTO::new).toList();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllById(List<Integer> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }

    @Override
    public String addCategory(String categoryName) {

        Category category = new Category(categoryName);

        categoryRepository.save(category);

        return "Category: "+categoryName+" is added successfully";
    }

    @Override
    public String updateCategory(String categoryName, Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).get();

        String oldName = category.getName();

        category.setName(categoryName);
        Category updatedCategory = categoryRepository.save(category);

        return "Category name changed: "+ oldName+" --> "+ updatedCategory.getName();
    }

    @Override
    public String deleteCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).get();

        for (Product product : category.getProducts()) {
            product.getCategories().remove(category);
        }

        productRepository.saveAll(category.getProducts());

        categoryRepository.delete(category);

        return "Category: "+category.getName()+" is deleted successfully";
    }
}
