package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.WishlistDTO;
import com.project.e_commerce_api.entity.Wishlist;

public interface WishlistService {

    WishlistDTO getWishlist(Integer userId);

    String addProductToWishlist(Integer userId, Integer productId);

    String deleteProductFromWishlist(Integer userId, Integer productId);
}
