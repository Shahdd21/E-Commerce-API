package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.WishlistDTO;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public WishlistDTO getWishlist(@AuthenticationPrincipal User loggedInUser){
        return wishlistService.getWishlist(loggedInUser.getUser_id());
    }

    @PostMapping("/{productId}")
    public String addProductToWishlist(@AuthenticationPrincipal User loggedInUser,
                                       @PathVariable Integer productId){

        return wishlistService.addProductToWishlist(loggedInUser.getUser_id(),productId);
    }

    @DeleteMapping("/{productId}")
    public String deleteProductFromWishlist(@AuthenticationPrincipal User loggedInUser,
                                            @PathVariable Integer productId){

        return wishlistService.deleteProductFromWishlist(loggedInUser.getUser_id(),productId);
    }
}
