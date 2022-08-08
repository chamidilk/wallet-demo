package com.dilshan.wallet.controller;

import com.dilshan.wallet.dto.UserDto;
import com.dilshan.wallet.dto.WalletDto;
import com.dilshan.wallet.service.UserService;
import com.dilshan.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public boolean create(@RequestBody UserDto userDto) {



        // invoke deposit service
        // return updated wallet

        return userService.create(userDto);

    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String handleException(Exception e) {
        //
        return e.getMessage();
    }
}
