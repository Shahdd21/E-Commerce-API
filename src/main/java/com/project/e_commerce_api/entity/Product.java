package com.project.e_commerce_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;


    @ManyToMany
    @JoinTable(
            name="product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL) // the field name in OrderProduct
    private List<OrderProduct> boughtInOrders;


    @ManyToMany
    @JoinTable(
            name="product_vendor",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "vendor_id")
    )
    private List<Vendor> vendors;

    @ManyToMany
    @JoinTable(
            name="wishlist_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Wishlist> foundInWishlists;

    public Product(){

    }

    public Product(String name, String description, BigDecimal price
            , List<Category> categories, List<Vendor> vendors) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vendors = vendors;
        this.categories = categories;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        if(reviews == null) reviews = new ArrayList<>();

        reviews.add(review);
    }

    public List<OrderProduct> getBoughtInOrders() {
        return boughtInOrders;
    }

    public void setBoughtInOrders(List<OrderProduct> boughtInOrders) {
        this.boughtInOrders = boughtInOrders;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addOrder(OrderProduct order){
        if(boughtInOrders == null) boughtInOrders = new ArrayList<>();

        boughtInOrders.add(order);
    }

    public void addCategory(Category category){
        if(categories == null) categories = new ArrayList<>();

        categories.add(category);
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public List<Wishlist> getFoundInWishlists() {
        return foundInWishlists;
    }

    public void setFoundInWishlists(List<Wishlist> foundInWishlists) {
        this.foundInWishlists = foundInWishlists;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", vendors=" + vendors +
                ", reviews=" + reviews +
                '}';
    }
}
