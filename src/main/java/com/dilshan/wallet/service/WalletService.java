package com.dilshan.wallet.service;


import com.dilshan.wallet.dto.UserDto;
import com.dilshan.wallet.dto.WalletDto;
import com.dilshan.wallet.exception.InsufficientAmountException;
import com.dilshan.wallet.exception.NegativeAmountException;
import com.dilshan.wallet.model.User;
import com.dilshan.wallet.model.Wallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Service
@AllArgsConstructor
public class WalletService {

    private final EntityManager entityManager;

    @Transactional
    public WalletDto deposit(WalletDto walletDto) {
        // check deposit amount
        if (walletDto.getAmount() < 0) {
            throw new NegativeAmountException("deposit wallet amount cannot be negative");
        }
        // get the wallet
        Wallet wallet = entityManager.find(Wallet.class, walletDto.getWalletId());
        entityManager.lock(wallet, LockModeType.OPTIMISTIC);
        // credit wallet amount
        wallet.setAmount(wallet.getAmount() + walletDto.getAmount());

        wallet = entityManager.merge(wallet);

        return WalletDto.builder().walletId(wallet.getId()).amount(wallet.getAmount()).build();
    }

    @Transactional
    public WalletDto withdraw(WalletDto walletDto) {
        // check withdraw amount
        if (walletDto.getAmount() < 0) {
            throw new NegativeAmountException("withdraw wallet amount cannot be negative");
        }
        // get the wallet
        Wallet wallet = entityManager.find(Wallet.class, walletDto.getWalletId());
        entityManager.lock(wallet, LockModeType.OPTIMISTIC);
        // check wallet balance
        if (wallet.getAmount() < walletDto.getAmount()) {
            throw new InsufficientAmountException("insufficient balance");
        }
        // debit wallet amount
        wallet.setAmount(wallet.getAmount() - walletDto.getAmount());

        wallet = entityManager.merge(wallet);

        return WalletDto.builder().walletId(wallet.getId()).amount(wallet.getAmount()).build();
    }
}
