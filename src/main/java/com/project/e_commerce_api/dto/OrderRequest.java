package com.project.e_commerce_api.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private List<OrderProductRequest> orderProducts;

    public OrderRequest() {
        orderProducts = new ArrayList<>();
    }

    public OrderRequest(List<OrderProductRequest> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<OrderProductRequest> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductRequest> orderProducts) {
        this.orderProducts = orderProducts;
    }


    /////////////////////////////////////////////////////////////////

    public static class OrderProductRequest{

        private int productId;
        private int quantity;

        public OrderProductRequest(){

        }

        public OrderProductRequest(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
