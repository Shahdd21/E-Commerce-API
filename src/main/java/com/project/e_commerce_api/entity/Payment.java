package com.project.e_commerce_api.entity;

import com.project.e_commerce_api.enums.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int payment_id;

    @Column(name = "payment_method")
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod payment_method;

    @Column(name = "payment_status")
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus payment_status;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDate created_at;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "order_id") // the name of the fk in the database
    private Order order;

    public Payment(){

    }

    public Payment(PaymentMethod payment_method, PaymentStatus payment_status,
                   BigDecimal amount, LocalDate created_at, Order order) {
        this.payment_method = payment_method;
        this.payment_status = payment_status;
        this.amount = amount;
        this.created_at = created_at;
        this.order = order;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", payment_method='" + payment_method + '\'' +
                ", payment_status=" + payment_status +
                ", amount=" + amount +
                ", created_at=" + created_at +
                ", order=" + order +
                '}';
    }
}
