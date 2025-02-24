package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM `Payment` p WHERE p.order.order_id = :orderId")
    Optional<Payment> findByOrder_id(Integer orderId);
}
