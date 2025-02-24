package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.dto.ProductAddRequest;
import com.project.e_commerce_api.dto.ProductUpdateRequest;
import com.project.e_commerce_api.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    Product findById(Integer id);

    Product find(Integer id);

    ProductDTO add(ProductAddRequest productAddRequest);

    ProductDTO fullUpdate(Integer productId, ProductAddRequest product);

    void deleteById(Integer id);

    ProductDTO update(Integer id, ProductUpdateRequest product);

    ProductDTO addCategoryToProduct(Integer productId, Integer categoryId);

    List<ProductDTO> getProductsByCategory(Integer categoryId);

    List<ProductDTO> getProductsByVendor(Integer vendorId);

    ProductDTO addVendorToProduct(Integer productId, Integer vendorId);

    List<Product> findAllById(List<Integer> products);

    String deleteVendorFromProduct(Integer productId, Integer vendorId);

    String deleteCategoryFromProduct(Integer productId, Integer categoryId);
}
