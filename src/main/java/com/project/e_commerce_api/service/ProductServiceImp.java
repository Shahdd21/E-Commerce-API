package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
         productRepository.deleteById(id);
    }
}
