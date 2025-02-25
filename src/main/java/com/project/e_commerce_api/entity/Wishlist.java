package com.project.e_commerce_api.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    private Integer customerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany
    @JoinTable(
            name="wishlist_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Wishlist(){
        products = new ArrayList<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return "Wishlist{" +
                "customer=" + customer +
                "products=" + products +
                '}';
    }
}
