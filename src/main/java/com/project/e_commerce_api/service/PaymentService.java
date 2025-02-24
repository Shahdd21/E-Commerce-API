package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.PaymentDTO;
import com.project.e_commerce_api.dto.PaymentRequest;
import com.project.e_commerce_api.entity.User;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentDTO makePayment(Integer orderId, PaymentRequest paymentRequest, User user);

    PaymentDTO getPaymentDetailsOfOrder(Integer orderId, Integer customerId);
}
