package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.OrderDTO;
import com.project.e_commerce_api.dto.OrderProductDTO;
import com.project.e_commerce_api.entity.OrderRequest;
import com.project.e_commerce_api.entity.Order;
import com.project.e_commerce_api.entity.OrderProduct;
import com.project.e_commerce_api.entity.Product;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.OrderStatus;
import com.project.e_commerce_api.repository.OrderProductRepository;
import com.project.e_commerce_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
    }

    @Override
    public OrderDTO placeOrder(User user, OrderRequest orderRequest) {

        System.out.println("INSIDE PLACE ORDER METHOD..");

        System.out.println("🟡 OrderRequest: " + orderRequest);
        System.out.println("🟡 OrderRequest Products: " + orderRequest.getOrderProducts());

        List<Integer> orderProductsIds = orderRequest.getOrderProducts()
                .stream()
                .map(OrderRequest.OrderProductRequest::getProductId)
                .toList();

        List<Product> foundProducts = productService.findAllById(orderProductsIds);

        if(foundProducts.size() != orderProductsIds.size())
            throw new RuntimeException("Some products are not found in the database");

        Order order = new Order();

        order.setCustomer(user.getCustomer());
        order.setCreated_at(LocalDate.now());
        order.setOrder_status(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        Map<Integer, Product> productMap = foundProducts.stream()
                .collect(Collectors.toMap(Product::getProduct_id, Function.identity()));

        List<OrderProduct> orderProducts = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderRequest.OrderProductRequest request : orderRequest.getOrderProducts()){

            System.out.println("✅ OrderRequest Products: " + orderRequest.getOrderProducts());

            Product product = productMap.get(request.getProductId());

            OrderProduct orderProduct = new OrderProduct(order,product, request.getQuantity());
            orderProduct.setOrder(order);

            orderProducts.add(orderProduct);

            sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        }

        var savedOrderProducts = orderProductRepository.saveAll(orderProducts);
        System.out.println("ORDER PRODUCTS: "+ savedOrderProducts);

        savedOrder.setProducts(savedOrderProducts);
        savedOrder.setTotal_price(sum);

        Order finalOrder = orderRepository.save(savedOrder);

        System.out.println("✅ Final Order: " + new OrderDTO(finalOrder));
        System.out.println("✅ Order Products: " + finalOrder.getProducts()
                .stream()
                .map(OrderProductDTO::new)
                .toList());

        return new OrderDTO(finalOrder);
    }

    @Override
    public List<OrderDTO> getOrders(User user) {

        List<Order> orders = orderRepository.findByCustomer_id(user.getCustomer().getCustomer_id());

        return orders.stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Override
    public OrderDTO findById(Integer orderId) {
        return new OrderDTO(orderRepository.findById(orderId).orElse(null));
    }

    @Override
    public List<OrderDTO> getAllOrders(User user) {
        return orderRepository.findAll().stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Override
    public String cancelOrder(Integer orderId) {

        Order order = orderRepository.findById(orderId).get();

        if(order.getOrder_status() == OrderStatus.SHIPPED){
            return "The order is shipped. it cannot be cancelled";
        }

        order.setOrder_status(OrderStatus.CANCELLED);
        orderRepository.save(order);

        return "Order with id "+ orderId+" is cancelled";
    }

    @Override
    public String updateStatus(Integer orderId, Map<String,String> orderStatus) {

        Order order = orderRepository.findById(orderId).get();

        OrderStatus oldStatus = order.getOrder_status();

        String newStatus = orderStatus.get("orderStatus").toUpperCase();
        order.setOrder_status(OrderStatus.valueOf(newStatus));
        orderRepository.save(order);

        return "Order with id "+ orderId+
                " changed from "+ oldStatus
                +"---> to "+ newStatus;
    }
}
