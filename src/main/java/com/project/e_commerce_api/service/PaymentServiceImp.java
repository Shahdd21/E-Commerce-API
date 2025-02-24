package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.PaymentDTO;
import com.project.e_commerce_api.dto.PaymentRequest;
import com.project.e_commerce_api.entity.Order;
import com.project.e_commerce_api.entity.Payment;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.entity.Wallet;
import com.project.e_commerce_api.enums.OrderStatus;
import com.project.e_commerce_api.enums.PaymentMethod;
import com.project.e_commerce_api.enums.PaymentStatus;
import com.project.e_commerce_api.exception.OrderNotFoundException;
import com.project.e_commerce_api.exception.PaymentNotFoundException;
import com.project.e_commerce_api.exception.UnauthorizedAccessException;
import com.project.e_commerce_api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class PaymentServiceImp implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;
    private final WalletService walletService;

    @Autowired
    public PaymentServiceImp(PaymentRepository paymentRepository, OrderService orderService, WalletService walletService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.walletService = walletService;
    }

    @Override
    public PaymentDTO makePayment(Integer orderId, PaymentRequest paymentRequest,
                                  User user) {

        Order order = orderService.findById(orderId);

        if(order == null)
            throw new RuntimeException("No order with id - "+orderId);

        Payment payment = new Payment();

        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentRequest.getPaymentMethod().toUpperCase());
        payment.setPayment_method( paymentMethod);

        PaymentStatus status ;
        switch (paymentMethod){
            case WALLET :
                status = processWalletPayment(order.getTotal_price(),user.getCustomer().getCustomer_id());
                break;

            case CASH:
                status = PaymentStatus.PENDING;
                break;

            default:
                status = processCardsPayment(order.getTotal_price(), user.getCustomer().getCustomer_id());
                break;
        }

        if(status == PaymentStatus.COMPLETED){
            orderService.updateStatus(orderId, OrderStatus.CONFIRMED);
        }

        else if (status == PaymentStatus.FAILED){
            orderService.updateStatus(orderId, OrderStatus.FAILED);
        }

        payment.setPayment_status(status);

        payment.setAmount(order.getTotal_price());
        payment.setCreated_at(LocalDate.now());
        payment.setOrder(order);

        return new PaymentDTO(paymentRepository.save(payment));
    }

    @Override
    public PaymentDTO getPaymentDetailsOfOrder(Integer orderId, Integer customerId) {

        Order order = orderService.findById(orderId);

        if(order == null)
            throw new OrderNotFoundException("No order with id - "+orderId);

        Payment payment = paymentRepository.findByOrder_id(orderId).get();

        if(payment == null) throw new PaymentNotFoundException("No payment for this order");

        if( !(payment.getOrder().getCustomer().getCustomer_id() == customerId) )
            throw new UnauthorizedAccessException("You can only view your own payments");

        return new PaymentDTO(payment);
    }

    private PaymentStatus processCardsPayment(BigDecimal orderPrice, Integer customerId) {

        return Math.random() > 0.1 ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
    }

    private PaymentStatus processWalletPayment(BigDecimal orderPrice, Integer customerId){

        Wallet customerWallet = walletService.getWalletByCustomerId(customerId);

        if (customerWallet == null) return PaymentStatus.FAILED ;

        if(customerWallet.getBalance().compareTo(orderPrice) < 0 ) return PaymentStatus.FAILED ;

        walletService.withdrawMoney(customerWallet.getWalletId(), orderPrice);

        return PaymentStatus.COMPLETED;
    }
}
