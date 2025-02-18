package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
