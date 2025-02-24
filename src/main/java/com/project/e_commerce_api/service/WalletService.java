package com.project.e_commerce_api.service;

import com.project.e_commerce_api.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService{

    Wallet getWalletByCustomerId(Integer customerId);

    boolean withdrawMoney(Integer walletId, BigDecimal amount);

    String topUpWallet(Integer walletId, BigDecimal amount);
}
