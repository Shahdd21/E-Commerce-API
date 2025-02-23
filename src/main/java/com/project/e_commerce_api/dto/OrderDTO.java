package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Integer orderId;
    private String orderStatus;
    private BigDecimal totalPrice;
    private String createdAt;
    private PaymentDTO payment;
    private CustomerDTO customer;
    private List<OrderProductDTO> products;

    public OrderDTO(Order order){
        this.orderId = order.getOrder_id();
        this.orderStatus = order.getOrder_status().name();
        this.totalPrice = order.getTotal_price();
        this.createdAt = order.getCreated_at()+"";
        this.payment = (order.getPayment() != null) ? new PaymentDTO(order.getPayment()) : null;
        this.customer = new CustomerDTO(order.getCustomer());
        this.products = order.getProducts().stream().map(OrderProductDTO::new).toList();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalPrice=" + totalPrice +
                ", createdAt='" + createdAt + '\'' +
                ", payment=" + payment +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
