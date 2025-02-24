package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Payment;

import java.math.BigDecimal;

public class PaymentDTO {

    private Integer paymentId;
    private String paymentMethod;
    private String paymentStatus;
    private BigDecimal amount;
    private String createdAt;
    private Integer orderId;

    public PaymentDTO(Payment payment){

        this.paymentId = payment.getPayment_id();
        this.paymentMethod = payment.getPayment_method().name();
        this.paymentStatus = payment.getPayment_status().name();
        this.amount = payment.getAmount();
        this.createdAt = payment.getCreated_at()+"";
        this.orderId = payment.getOrder().getOrder_id();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId=" + paymentId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amount=" + amount +
                ", createdAt='" + createdAt + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
