package com.dilshan.wallet.service;


import com.dilshan.wallet.dto.WalletDto;
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
        // get the wallet
        Wallet wallet = entityManager.find(Wallet.class, walletDto.getWalletId());
        entityManager.lock(wallet, LockModeType.OPTIMISTIC);
        // check wallet balance
        if (wallet.getAmount() < walletDto.getAmount()) {
            throw new IllegalArgumentException("insufficient balance");
        }
        // debit wallet amount
        wallet.setAmount(wallet.getAmount() - walletDto.getAmount());

        wallet = entityManager.merge(wallet);

        return WalletDto.builder().walletId(wallet.getId()).amount(wallet.getAmount()).build();
    }

    public boolean create(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        wallet.setAmount(walletDto.getAmount());
        entityManager.persist(wallet);

        return true;
    }
}
