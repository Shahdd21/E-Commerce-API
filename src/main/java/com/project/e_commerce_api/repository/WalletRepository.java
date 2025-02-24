package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    @Query("SELECT w FROM `Wallet` w WHERE w.customer.customer_id = :customerId")
    Optional<Wallet> findByCustomer_id(Integer customerId);
}
