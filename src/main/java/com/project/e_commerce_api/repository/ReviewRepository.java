package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.product.product_id = :productId")
    List<Review> findByProduct_Id(@Param("productId") Integer productId);
}
