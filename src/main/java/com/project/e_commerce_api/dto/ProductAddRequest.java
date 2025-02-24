package com.project.e_commerce_api.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductAddRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private List<Integer> categoryIds;
    private List<Integer> vendorIds;

    public ProductAddRequest(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<Integer> getVendorIds() {
        return vendorIds;
    }

    public void setVendorIds(List<Integer> vendorIds) {
        this.vendorIds = vendorIds;
    }
}
