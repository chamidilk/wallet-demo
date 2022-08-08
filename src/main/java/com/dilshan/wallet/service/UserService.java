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
public class UserService {

    private final EntityManager entityManager;


    @Transactional
    public boolean create(UserDto userDto) {
        // check initial deposit amount
        if (userDto.getAmount() < 0) {
            throw new NegativeAmountException("initial wallet amount cannot be negative");
        }
        User user = new User();
        Wallet wallet = new Wallet();
        wallet.setAmount(userDto.getAmount());
        wallet.setCurrency(userDto.getCurrency());
        user.getWallets().add(wallet);
        wallet.setUser(user);
        entityManager.persist(user);

        return true;
    }
}
