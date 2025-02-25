package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.OrderDTO;
import com.project.e_commerce_api.dto.OrderRequest;
import com.project.e_commerce_api.entity.Order;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderDTO placeOrder(User user, OrderRequest orderRequest);

    List<OrderDTO> getOrders(User user);

    Order findById(Integer orderId);

    List<OrderDTO> getAllOrders(User user);

    String cancelOrder(Integer orderId);

    String updateStatus(Integer orderId, Map<String,String> orderStatus);

    void updateStatus(Integer orderId, OrderStatus orderStatus);

    Order save(Order order);
}
