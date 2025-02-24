package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.PaymentDTO;
import com.project.e_commerce_api.dto.PaymentRequest;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.PaymentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public PaymentDTO makePayment(@PathVariable Integer orderId,
                                  @RequestBody PaymentRequest paymentRequest,
                                  @AuthenticationPrincipal User user){

        return paymentService.makePayment(orderId, paymentRequest, user);
    }

    @GetMapping("/{orderId}")
    public PaymentDTO getPaymentDetailsOfOrder(@PathVariable Integer orderId, @AuthenticationPrincipal User user){

        return paymentService.getPaymentDetailsOfOrder(orderId, user.getCustomer().getCustomer_id());
    }
}
