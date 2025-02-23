package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final VendorService vendorService;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, CategoryService categoryService, VendorService vendorService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.vendorService = vendorService;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> dtos = products.stream().map(ProductDTO::new).toList();

        return dtos;
    }

    @Override
    public ProductDTO findById(Integer id) {

        Optional<Product> result = productRepository.findById(id);

        ProductDTO product;

        if(result.isPresent()){
            product = new ProductDTO(result.get());
        }

        else throw new RuntimeException("Didn't find product with id - "+id);

        return product;
    }

    @Override
    public Product find(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDTO add(Product product) {

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

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO update(Product updatedProduct) {

        return new ProductDTO(productRepository.save(updatedProduct));
    }

    @Override
    public void deleteById(Integer id) {
         productRepository.deleteById(id);
    }

    @Override
    public ProductDTO update(Integer productId, Product updatedProduct) {

        Product existingProduct = productRepository.getReferenceById(productId);

        if(updatedProduct.getName() != null) existingProduct.setName(updatedProduct.getName());

        if(updatedProduct.getVendors() != null) existingProduct.setVendors(updatedProduct.getVendors());

        if(updatedProduct.getCategories() != null) existingProduct.setCategories(updatedProduct.getCategories());

        if(updatedProduct.getDescription() != null) existingProduct.setDescription(updatedProduct.getDescription());

        if(updatedProduct.getPrice() != null) existingProduct.setPrice(updatedProduct.getPrice());

        return new ProductDTO(productRepository.save(existingProduct));
    }

    @Override
    public ProductDTO addCategoryToProduct(Integer productId, Integer categoryId) {

        Product product = productRepository.getReferenceById(productId);
        Category category = categoryService.findById(categoryId);

        product.getCategories().add(category);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Integer categoryId) {

        Category category = categoryService.findById(categoryId);

        if(category == null) throw new RuntimeException("No category with id - "+categoryId);

        List<Product> products = category.getProducts();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    public List<ProductDTO> getProductsByVendor(Integer vendorId) {

        Vendor vendor = vendorService.findById(vendorId);

        if(vendor == null) throw new RuntimeException("No vendor with id - "+vendorId);

        List<Product> products = vendor.getProducts();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    public ProductDTO addVendorToProduct(Integer productId, Integer vendorId) {

        Product product = productRepository.findById(productId).get();

        Vendor vendor = vendorService.findById(vendorId);

        if(vendor == null) throw new RuntimeException("No vendor with id - "+vendorId);

        product.getVendors().add(vendor);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public List<Product> findAllById(List<Integer> products) {
        return productRepository.findAllById(products);
    }
}
