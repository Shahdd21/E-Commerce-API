package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.dto.ProductAddRequest;
import com.project.e_commerce_api.dto.ProductUpdateRequest;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.exception.ProductNotFoundException;
import com.project.e_commerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable Integer productId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        return new ProductDTO(product);
    }


    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductAddRequest productAddRequest){

        return productService.add(productAddRequest);
    }


    @PatchMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable Integer productId, @RequestBody ProductUpdateRequest updatedProduct){

        Product isFoundProduct = productService.findById(productId);

        if(isFoundProduct == null) throw new ProductNotFoundException("Product not found");

        return productService.update(productId, updatedProduct);
    }


    @PutMapping("/{productId}")
    public ProductDTO fullUpdateProduct(@PathVariable Integer productId, @RequestBody ProductAddRequest updatedProduct){

        Product isFoundProduct = productService.findById(productId);

        if(isFoundProduct == null) throw new ProductNotFoundException("Product not found");

        //updatedProduct.setProduct_id(productId); // to ensure the id stays the same
        return productService.fullUpdate(productId,updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        productService.deleteById(productId);

        return "Deleted product: "+product;
    }

    @PutMapping("/{productId}/category/{categoryId}")
    public ProductDTO addCategoryToProduct(@PathVariable Integer productId,
                                           @PathVariable Integer categoryId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        return productService.addCategoryToProduct(productId, categoryId);
    }

    @DeleteMapping("/{productId}/category/{categoryId}")
    public String deleteCategoryFromProduct(@PathVariable Integer productId,
                                                @PathVariable Integer categoryId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        return productService.deleteCategoryFromProduct(productId, categoryId);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Integer categoryId){

        return productService.getProductsByCategory(categoryId);
    }

    @PutMapping("/{productId}/vendor/{vendorId}")
    public ProductDTO addVendorToProduct(@PathVariable Integer productId,
                                         @PathVariable Integer vendorId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        return productService.addVendorToProduct(productId, vendorId);
    }

    @DeleteMapping("/{productId}/vendor/{vendorId}")
    public String deleteVendorFromProduct(@PathVariable Integer productId,
                                         @PathVariable Integer vendorId){

        Product product = productService.findById(productId);

        if (product == null) throw new ProductNotFoundException("Didn't find product with id - "+productId);

        return productService.deleteVendorFromProduct(productId, vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<ProductDTO> getProductsByVendor(@PathVariable Integer vendorId){
        return productService.getProductsByVendor(vendorId);
    }
}
