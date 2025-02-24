package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Wallet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WalletDTO {

    private Integer walletId;
    private Integer customerId;
    private BigDecimal balance;
    private LocalDate updatedAt;

    public WalletDTO(Wallet wallet){

        this.walletId = wallet.getWalletId();
        this.customerId = wallet.getCustomer().getCustomer_id();
        this.balance = wallet.getBalance();
        this.updatedAt = wallet.getUpdatedAt();
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
