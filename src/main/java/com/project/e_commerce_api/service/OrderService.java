package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.OrderDTO;
import com.project.e_commerce_api.entity.OrderRequest;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderDTO placeOrder(User user, OrderRequest orderRequest);

    List<OrderDTO> getOrders(User user);

    OrderDTO findById(Integer orderId);

    List<OrderDTO> getAllOrders(User user);

    String cancelOrder(Integer orderId);

    String updateStatus(Integer orderId, Map<String,String> orderStatus);
}
