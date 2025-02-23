package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM `Order` o WHERE o.customer.customer_id = :customerId")
    List<Order> findByCustomer_id(@Param("customerId") Integer customerId);
}
