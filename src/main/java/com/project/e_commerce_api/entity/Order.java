package com.project.e_commerce_api.entity;

import com.project.e_commerce_api.enums.OrderStatus;
import com.project.e_commerce_api.enums.OrderStatusConverter;
import com.project.e_commerce_api.enums.UserRoleConverter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "order_status")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus order_status;

    @Column(name = "total_price")
    private BigDecimal total_price = BigDecimal.ZERO;

    @Column(name = "created_at")
    private LocalDate created_at;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // "order" property in Payment class
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // the field name in OrderProduct
    private List<OrderProduct> orderProducts;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Order(){

    }

    public Order(OrderStatus order_status, BigDecimal total_price,
                 LocalDate created_at, Payment payment, List<OrderProduct> products) {
        this.order_status = order_status;
        this.total_price = total_price;
        this.created_at = created_at;
        this.payment = payment;
        this.orderProducts = products;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public OrderStatus getOrder_status() {
        return order_status;
    }

    public void setOrder_status(OrderStatus order_status) {
        this.order_status = order_status;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderProduct> getProducts() {
        return orderProducts;
    }

    public void setProducts(List<OrderProduct> products) {
        this.orderProducts = products;
    }

    public void addProduct(Product product, int quantity){
        if(orderProducts == null) orderProducts = new ArrayList<>();

        OrderProduct orderProduct = new OrderProduct(this, product, quantity);
        orderProducts.add(orderProduct);

        // Update total price
        this.total_price = this.total_price.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_status=" + order_status +
                ", total_price=" + total_price +
                ", created_at=" + created_at +
                ", payment=" + payment +
                ", products=" + orderProducts +
                ", customer=" + customer +
                '}';
    }
}
