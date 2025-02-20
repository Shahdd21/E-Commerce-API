package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Product;

import java.net.Inet4Address;
import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(Integer id);

    Product find(Integer id);

    ProductDTO add(Product product);

    ProductDTO update(Product product);

    void deleteById(Integer id);

    ProductDTO update(Integer id, Product product);

    ProductDTO addCategoryToProduct(Integer productId, Integer categoryId);

    List<ProductDTO> getProductsByCategory(Integer categoryId);

    List<ProductDTO> getProductsByVendor(Integer vendorId);

    ProductDTO addVendorToProduct(Integer productId, Integer vendorId);
}
