package com.dilshan.wallet;

import com.dilshan.wallet.model.Wallet;
import com.dilshan.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
@Slf4j
public class WalletApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WalletApplication.class, args);


    }


}
