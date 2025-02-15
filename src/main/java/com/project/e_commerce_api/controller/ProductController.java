package com.project.e_commerce_api.controller;


import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }
}
