package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);
}
