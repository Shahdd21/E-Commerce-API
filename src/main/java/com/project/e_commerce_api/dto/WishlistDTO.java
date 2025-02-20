package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Wishlist;

import java.util.List;

public class WishlistDTO {

    private Integer customerId;

    private List<ProductDTO> products;

    public WishlistDTO(Wishlist wishlist){
        this.customerId = wishlist.getCustomer().getCustomer_id();
        this.products = wishlist.getProducts().stream().map(ProductDTO::new).toList();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "customerId=" + customerId +
                ", products=" + products +
                '}';
    }
}
