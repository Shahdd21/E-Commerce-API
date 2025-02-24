package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.WalletDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.entity.Wallet;
import com.project.e_commerce_api.service.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public WalletDTO getWallet(@AuthenticationPrincipal User user) {
        return new WalletDTO(walletService.getWalletByCustomerId(user.getCustomer().getCustomer_id()));
    }

    @PostMapping("/topup")
    public String topUp(@AuthenticationPrincipal User user,
                        @RequestBody Map<String, BigDecimal> balance){

        Wallet wallet = walletService.getWalletByCustomerId(user.getCustomer().getCustomer_id());

        return walletService.topUpWallet(wallet.getWalletId(), balance.get("amount"));
    }
}
