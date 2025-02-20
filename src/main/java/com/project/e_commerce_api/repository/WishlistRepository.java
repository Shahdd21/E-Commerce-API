package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
