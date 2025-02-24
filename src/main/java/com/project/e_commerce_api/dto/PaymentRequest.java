package com.project.e_commerce_api.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private String paymentMethod;
    private Integer orderId;

    public PaymentRequest(){

    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
