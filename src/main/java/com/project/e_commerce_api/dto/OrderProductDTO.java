package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.OrderProduct;

import java.math.BigDecimal;

public class OrderProductDTO {

    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;

    public OrderProductDTO(OrderProduct orderProduct){
        this.productId = orderProduct.getProduct().getProduct_id();
        this.productName = orderProduct.getProduct().getName();
        this.productPrice = orderProduct.getProduct().getPrice();
        this.quantity = orderProduct.getQuantity();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
