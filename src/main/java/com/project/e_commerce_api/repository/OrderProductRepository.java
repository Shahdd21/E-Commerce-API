package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.OrderProduct;
import com.project.e_commerce_api.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
