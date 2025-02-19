package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Category;

public class CategoryDTO {

    private int categoryId;
    private String name;

    public CategoryDTO(Category category){
        this.categoryId = category.getCategory_id();
        this.name = category.getName();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
