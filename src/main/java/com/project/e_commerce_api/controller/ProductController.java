package com.project.e_commerce_api.controller;


import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.service.CategoryService;
import com.project.e_commerce_api.service.ProductService;
import com.project.e_commerce_api.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final VendorService vendorService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, VendorService vendorService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.vendorService = vendorService;
    }

    @GetMapping("/products")
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public ProductDTO findById(@PathVariable Integer productId){

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){

        //in case they put id in json
        // set id to null to force insertion of new item not update
        product.setProduct_id(null);

        //check for the dependencies (relationships), if new save them first in their tables
        //then save the product along with its dependencies

        //check for categories
        List<Category> existingCategories = new ArrayList<>();
        for(Category category : product.getCategories()){
            Category isFoundCategory = categoryService.findById(category.getCategory_id());

            if(isFoundCategory != null){
                existingCategories.add(isFoundCategory);
            }

            else{
                existingCategories.add(categoryService.save(category));
            }
        }
        product.setCategories(existingCategories);

        //check for vendors
        List<Vendor> existingVendors = new ArrayList<>();
        for(Vendor vendor : product.getVendors()){
            Vendor isFoundVendor = vendorService.findById(vendor.getVendor_id());

            if(isFoundVendor != null){
                existingVendors.add(isFoundVendor);
            }

            else{
                existingVendors.add(vendorService.save(vendor));
            }
        }
        product.setVendors(existingVendors);


        Product dbProduct = productService.save(product);
        return dbProduct; // with the updated id
    }

    //oops not working !!
    @PatchMapping("/products/{productId}")
    public Product updateProduct(@PathVariable Integer productId, @RequestBody Product product){

        ProductDTO isFoundProduct = productService.findById(productId);

        if(isFoundProduct == null) throw new RuntimeException("Product not found");

        

        Product dbProduct = productService.save(product);
        return dbProduct;
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Integer productId){

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        productService.deleteById(productId);

        return "Deleted product: "+product;
    }
}
