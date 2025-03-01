package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.dto.WishlistDTO;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.Wishlist;
import com.project.e_commerce_api.exception.ProductNotFoundException;
import com.project.e_commerce_api.repository.ProductRepository;
import com.project.e_commerce_api.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImp implements WishlistService{

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WishlistServiceImp(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    @Override
    public WishlistDTO getWishlist(Integer customerId) {
        return new WishlistDTO(wishlistRepository.findById(customerId).get());
    }

    @Override
    public String addProductToWishlist(Integer customerId, Integer productId) {

        Wishlist wishlist = wishlistRepository.findById(customerId).get();

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) throw new ProductNotFoundException("No product with id - "+ product);

        wishlist.getProducts().add(product);

        wishlistRepository.save(wishlist);

        return "Added to wishlist product - " + new ProductDTO(product);
    }

    @Override
    public String deleteProductFromWishlist(Integer customerId, Integer productId) {

        Wishlist wishlist = wishlistRepository.findById(customerId).get();

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) throw new ProductNotFoundException("No product with id - "+ product);

        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);

        return "Deleted from wishlist product - " + new ProductDTO(product);
    }
}
