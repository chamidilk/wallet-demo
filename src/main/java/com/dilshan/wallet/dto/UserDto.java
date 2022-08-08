package com.dilshan.wallet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {



    private int amount;
    private String currency;
}
