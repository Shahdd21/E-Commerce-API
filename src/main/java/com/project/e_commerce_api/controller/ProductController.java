package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Product;
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

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        return product;
    }

    //seems to have a problem :))
    @PostMapping
    public ProductDTO addProduct(@RequestBody Product product){

        ProductDTO dbProduct = productService.add(product);
        return dbProduct; // with the updated id
    }


    @PatchMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable Integer productId, @RequestBody Product updatedProduct){

        ProductDTO isFoundProduct = productService.findById(productId);

        if(isFoundProduct == null) throw new RuntimeException("Product not found");

        return productService.update(productId, updatedProduct);
    }

    //i have a problem with this !!!!
    @PutMapping("/{productId}")
    public ProductDTO fullUpdateProduct(@PathVariable Integer productId, @RequestBody Product updatedProduct){

        ProductDTO isFoundProduct = productService.findById(productId);

        if(isFoundProduct == null) throw new RuntimeException("Product not found");

        updatedProduct.setProduct_id(productId); // to ensure the id stays the same
        return productService.update(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId){

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        productService.deleteById(productId);

        return "Deleted product: "+product;
    }

    @PutMapping("/{productId}/category/{categoryId}")
    public ProductDTO addCategoryToProduct(@PathVariable Integer productId,
                                           @PathVariable Integer categoryId){

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        return productService.addCategoryToProduct(productId, categoryId);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Integer categoryId){

        return productService.getProductsByCategory(categoryId);
    }

    @PutMapping("/{productId}/vendor/{vendorId}")
    public ProductDTO addVendorToProduct(@PathVariable Integer productId,
                                         @PathVariable Integer vendorId){

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("Didn't find product with id - "+productId);

        return productService.addVendorToProduct(productId, vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<ProductDTO> getProductsByVendor(@PathVariable Integer vendorId){
        return productService.getProductsByVendor(vendorId);
    }
}
