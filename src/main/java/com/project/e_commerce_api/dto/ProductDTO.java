package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Category;
import com.project.e_commerce_api.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {

    private Integer product_id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> categories;

    public ProductDTO(Product product){
        this.product_id = product.getProduct_id();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        Object category = null;
        this.categories = product.getCategories()
                .stream()
                .map(Category::getName)  // Convert to names only
                .collect(Collectors.toList());
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }
}
