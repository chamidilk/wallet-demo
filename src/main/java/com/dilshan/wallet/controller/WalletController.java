package com.dilshan.wallet.controller;

import com.dilshan.wallet.dto.WalletDto;
import com.dilshan.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    public boolean create(@RequestBody WalletDto walletDto) {

        if(walletDto.getAmount() < 0){
            throw new IllegalArgumentException("initial wallet amount cannot be negative");
        }

        // invoke deposit service
        // return updated wallet

        return walletService.create(walletDto);

    }

    @PostMapping("/deposit")
    public WalletDto deposit(@RequestBody WalletDto walletDto) {

        if(walletDto.getAmount() < 0){
            throw new IllegalArgumentException("deposit wallet amount cannot be negative");
        }

        // invoke deposit service
        // return updated wallet

        return walletService.deposit(walletDto);

    }

    @PostMapping("/withdraw")
    public WalletDto withdraw(@RequestBody WalletDto walletDto) {

        if(walletDto.getAmount() < 0){
            throw new IllegalArgumentException("withdraw wallet amount cannot be negative");
        }

        // invoke deposit service
        // return updated wallet

        return walletService.withdraw(walletDto);

    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String handleException(Exception e) {
        //
        return e.getMessage();
    }
}
