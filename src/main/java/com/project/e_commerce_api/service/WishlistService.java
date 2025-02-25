package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.WishlistDTO;

public interface WishlistService {

    WishlistDTO getWishlist(Integer customerId);

    String addProductToWishlist(Integer customerId, Integer productId);

    String deleteProductFromWishlist(Integer customerId, Integer productId);
}
