package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.CategoryDTO;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.exception.CategoryNotFoundException;
import com.project.e_commerce_api.exception.GlobalExceptionHandler;
import com.project.e_commerce_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> findAll(){
        return categoryService.findAll();
    }

    // for admins
    @PostMapping
    public String addCategory(@RequestBody Map<String, String> category) throws Exception {

        String categoryName = category.get("category");

        if (categoryName == null || categoryName.isBlank()) {
            throw new Exception("Category name cannot be null or empty");
        }

        return categoryService.addCategory(categoryName);
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId){

        Category category = categoryService.findById(categoryId);

        if(category == null) throw new CategoryNotFoundException("no category with id - "+categoryId);

        return categoryService.deleteCategory(categoryId);
    }

    @PatchMapping("/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId,
                                 @RequestBody Map<String, String> categoryRequest) throws Exception {

        Category category = categoryService.findById(categoryId);

        if(category == null) throw new CategoryNotFoundException("no category with id - "+categoryId);

        String categoryName = categoryRequest.get("category");


        if (categoryName == null || categoryName.isBlank()) {
            throw new Exception("Category name cannot be null or empty");
        }

        return categoryService.updateCategory(categoryName, categoryId);
    }
}
