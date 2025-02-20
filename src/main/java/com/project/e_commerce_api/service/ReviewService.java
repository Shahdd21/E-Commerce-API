package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ReviewDTO;
import com.project.e_commerce_api.entity.Review;
import com.project.e_commerce_api.entity.User;

import java.net.Inet4Address;
import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getReviewsForProduct(Integer productId);

    ReviewDTO addReviewToProduct(Integer productId, Review review, User user);

    Review findById(Integer reviewId);

    void deleteReviewById(Integer reviewId);

    ReviewDTO modifyReview(Integer reviewId, Review updatedReview);
}
