package com.project.e_commerce_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId orderProductId;

    @ManyToOne
    @MapsId("orderId") // maps the orderId in the composite key
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId") // maps the productId in the composite key
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderProduct(){

    }

    public OrderProduct(Order order, Product product, Integer quantity) {
        this.orderProductId = new OrderProductId(order.getOrder_id(), product.getProduct_id());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProductId getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(OrderProductId orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
