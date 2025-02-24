package com.project.e_commerce_api.service;

import com.project.e_commerce_api.entity.Wallet;
import com.project.e_commerce_api.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class WalletServiceImp implements WalletService{

    private final WalletRepository walletRepository;


    public WalletServiceImp(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    @Override
    public Wallet getWalletByCustomerId(Integer customerId) {
        return walletRepository.findByCustomer_id(customerId).get();
    }

    @Override
    public boolean withdrawMoney(Integer walletId, BigDecimal amount) {

        Wallet wallet = walletRepository.findById(walletId).get();

        if(wallet.getBalance().compareTo(amount) < 0 ) return false;

        BigDecimal newBalance = wallet.getBalance().subtract(amount);

        wallet.setBalance(newBalance);
        wallet.setUpdatedAt(LocalDate.now());

        walletRepository.save(wallet);

        return true;
    }

    @Override
    public String topUpWallet(Integer walletId, BigDecimal amount) {

        Wallet wallet = walletRepository.findById(walletId).get();

        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        wallet.setUpdatedAt(LocalDate.now());

        Wallet updatedWallet = walletRepository.save(wallet);

        return "Your wallet is topped up! your new balance: "+ updatedWallet.getBalance();
    }
}
