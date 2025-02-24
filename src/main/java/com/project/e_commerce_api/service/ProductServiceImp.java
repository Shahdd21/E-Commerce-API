package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.dto.ProductAddRequest;
import com.project.e_commerce_api.dto.ProductUpdateRequest;
import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.exception.CategoryNotFoundException;
import com.project.e_commerce_api.exception.ProductNotFoundException;
import com.project.e_commerce_api.exception.VendorNotFoundException;
import com.project.e_commerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product findById(Integer id) {

        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()){
            throw new ProductNotFoundException("Didn't find product with id - "+id);
        }

        return product.get();
    }

    @Override
    public Product find(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDTO add(ProductAddRequest productAddRequest) {

        Product product = new Product();
        product.setName(productAddRequest.getName());
        product.setDescription(productAddRequest.getDescription());
        product.setPrice(productAddRequest.getPrice());

        List<Integer> vendorIds = productAddRequest.getVendorIds();
        List<Vendor> vendors = vendorService.findAllById(vendorIds);

        if(vendors.size() != vendorIds.size())
            throw new VendorNotFoundException("Some vendors are not found in the database");

        product.setVendors(vendors);

        List<Integer> categoryIds = productAddRequest.getCategoryIds();
        List<Category> categories = categoryService.findAllById(categoryIds);

        if(categories.size() != categoryIds.size())
            throw new CategoryNotFoundException("Some categories are not found in the database");

        product.setCategories(categories);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO fullUpdate(Integer productId, ProductAddRequest updatedProduct) {

        Product product = productRepository.findById(productId).get();

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());

        List<Integer> vendorIds = updatedProduct.getVendorIds();
        List<Vendor> vendors = vendorService.findAllById(vendorIds);

        if(vendors.size() != vendorIds.size())
            throw new VendorNotFoundException("Some vendors are not found in the database");

        product.setVendors(vendors);

        List<Integer> categoryIds = updatedProduct.getCategoryIds();
        List<Category> categories = categoryService.findAllById(categoryIds);

        if(categories.size() != categoryIds.size())
            throw new CategoryNotFoundException("Some categories are not found in the database");

        product.setCategories(categories);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public void deleteById(Integer id) {
         productRepository.deleteById(id);
    }

    @Override
    public ProductDTO update(Integer productId, ProductUpdateRequest updatedProduct) {

        Product existingProduct = productRepository.findById(productId).get();

        if(updatedProduct.getName() != null) existingProduct.setName(updatedProduct.getName());

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

        if(category == null) throw new CategoryNotFoundException("No category with id - "+categoryId);

        List<Product> products = category.getProducts();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    public List<ProductDTO> getProductsByVendor(Integer vendorId) {

        Vendor vendor = vendorService.findById(vendorId);

        if(vendor == null) throw new VendorNotFoundException("No vendor with id - "+vendorId);

        List<Product> products = vendor.getProducts();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Override
    public ProductDTO addVendorToProduct(Integer productId, Integer vendorId) {

        Product product = productRepository.findById(productId).get();

        Vendor vendor = vendorService.findById(vendorId);

        if(vendor == null) throw new VendorNotFoundException("No vendor with id - "+vendorId);

        product.getVendors().add(vendor);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public List<Product> findAllById(List<Integer> products) {
        return productRepository.findAllById(products);
    }

    @Override
    public String deleteVendorFromProduct(Integer productId, Integer vendorId) {

        Vendor vendor = vendorService.findById(vendorId);
        Product product = productRepository.findById(productId).get();

        product.getVendors().remove(vendor);
        vendor.getProducts().remove(product);

        productRepository.save(product);
        vendorService.save(vendor);

        return "Vendor with id - "+ vendorId+" is removed successfully";
    }

    @Override
    public String deleteCategoryFromProduct(Integer productId, Integer categoryId) {

        Category category = categoryService.findById(categoryId);
        Product product = productRepository.findById(productId).get();

        product.getCategories().remove(category);
        category.getProducts().remove(product);

        productRepository.save(product);
        categoryService.save(category);

        return "Category with id - "+ categoryId+" is removed successfully";
    }
}
