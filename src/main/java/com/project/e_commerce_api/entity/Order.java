package com.project.e_commerce_api.entity;

import com.project.e_commerce_api.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    @Column(name = "total_price")
    private BigDecimal total_price;

    @Column(name = "created_at")
    private LocalDate created_at;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // "order" property in Payment class
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name="order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order(){

    }

    public Order(OrderStatus order_status, BigDecimal total_price,
                 LocalDate created_at, Payment payment, List<Product> products) {
        this.order_status = order_status;
        this.total_price = total_price;
        this.created_at = created_at;
        this.payment = payment;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if(products == null) products = new ArrayList<>();

        products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_status=" + order_status +
                ", total_price=" + total_price +
                ", created_at=" + created_at +
                ", payment_details=" + payment +
                ", products_list=" + products +
                '}';
    }
}
