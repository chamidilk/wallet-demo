package com.dilshan.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WalletDto {
    private long walletId;


    private double amount;
}
