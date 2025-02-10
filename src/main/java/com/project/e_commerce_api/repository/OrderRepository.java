package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
