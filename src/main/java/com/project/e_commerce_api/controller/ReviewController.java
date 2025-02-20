package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.ReviewDTO;
import com.project.e_commerce_api.entity.Review;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{productId}")
    public List<ReviewDTO> getReviewsForProduct(@PathVariable Integer productId){

        return reviewService.getReviewsForProduct(productId);
    }

    @PostMapping("/{productId}")
    public ReviewDTO addReviewToProduct(@PathVariable Integer productId,
                                        @RequestBody Review review,
                                        @AuthenticationPrincipal User user){

        return reviewService.addReviewToProduct(productId, review, user);
    }

    @PatchMapping("/{reviewId}")
    public ReviewDTO modifyReview(@PathVariable Integer reviewId,
                                  @RequestBody Review updatedReview){

        Review review = reviewService.findById(reviewId);

        if(review == null) throw new RuntimeException("No review with id - "+reviewId);

        return reviewService.modifyReview(reviewId, updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Integer reviewId){

        Review review = reviewService.findById(reviewId);

        if(review == null) throw new RuntimeException("No review with id - "+reviewId);

        reviewService.deleteReviewById(reviewId);

        return "Deleted review : "+ new ReviewDTO(review);
    }
}
