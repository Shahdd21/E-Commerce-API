package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.ProductDTO;
import com.project.e_commerce_api.dto.ReviewDTO;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.Review;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    @Autowired
    public ReviewServiceImp(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public List<ReviewDTO> getReviewsForProduct(Integer productId) {

        ProductDTO product = productService.findById(productId);

        if (product == null) throw new RuntimeException("No product with id - "+ productId);

        List<Review> reviews = reviewRepository.findByProduct_Id(productId);

//        List<Review> productReviews = reviews.stream()
//                .filter(review -> review.getProduct().getProduct_id() == productId)
//                .toList();

        return reviews.stream().map(ReviewDTO::new).toList();
    }

    @Override
    public ReviewDTO addReviewToProduct(Integer productId, Review review, User user) {

        Product product = productService.find(productId);

        if (product == null) throw new RuntimeException("No product with id - "+ productId);

        review.setProduct(product);
        review.setCustomer(user.getCustomer());

        return new ReviewDTO(reviewRepository.save(review));
    }

    @Override
    public Review findById(Integer reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public void deleteReviewById(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewDTO modifyReview(Integer reviewId, Review updatedReview) {

        Review existingReview = reviewRepository.findById(reviewId).get();

        if(updatedReview.getContent() != null) existingReview.setContent(updatedReview.getContent());

        if(updatedReview.getRating() != null) existingReview.setRating(updatedReview.getRating());

        return new ReviewDTO(reviewRepository.save(existingReview));
    }
}
