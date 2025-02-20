package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Review;

public class ReviewDTO {

    private Integer reviewId;
    private String content;
    private int rating;
    private String authorName;
    private String productName;

    public ReviewDTO(Review review){
        this.reviewId = review.getReview_id();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.authorName = review.getCustomer().getFirst_name()+" "+review.getCustomer().getLast_name();
        this.productName = review.getProduct().getName();
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", authorName='" + authorName + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
