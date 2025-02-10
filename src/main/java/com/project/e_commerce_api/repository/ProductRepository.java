package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
