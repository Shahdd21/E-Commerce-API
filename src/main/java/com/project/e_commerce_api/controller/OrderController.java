package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.OrderDTO;
import com.project.e_commerce_api.entity.OrderRequest;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.OrderStatus;
import com.project.e_commerce_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDTO placeOrder(@AuthenticationPrincipal User user,
                               @RequestBody OrderRequest orderRequest){

        System.out.println("GOING TO PLACE ORDER METHOD..");
        return orderService.placeOrder(user, orderRequest);
    }

    @GetMapping
    public List<OrderDTO> getOrders(@AuthenticationPrincipal User user){
        return orderService.getOrders(user);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Integer orderId,
                                 @AuthenticationPrincipal User user){

        OrderDTO order = orderService.findById(orderId);

        if( !((order != null) && (order.getCustomer().getCustomer_id() == user.getCustomer().getCustomer_id())) )
            throw new RuntimeException("no order with id - "+ orderId);

        return order;
    }

    @PatchMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Integer orderId){

        OrderDTO order = orderService.findById(orderId);

        if(order == null) return "No order with id - "+orderId;

        return orderService.cancelOrder(orderId);
    }

    //for admins
    @GetMapping("/all")
    public List<OrderDTO> getAllOrders(@AuthenticationPrincipal User user){

        return orderService.getAllOrders(user);
    }

    @PatchMapping("/{orderId}/status")
    public String updateStatus(@PathVariable Integer orderId,
                               @RequestBody Map<String,String> orderStatus){

        OrderDTO order = orderService.findById(orderId);

        if(order == null) return "No order with id - "+orderId;

        return orderService.updateStatus(orderId, orderStatus);
    }
}
