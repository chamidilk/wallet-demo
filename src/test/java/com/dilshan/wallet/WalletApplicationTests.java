package com.dilshan.wallet;

import com.dilshan.wallet.controller.UserController;
import com.dilshan.wallet.controller.WalletController;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
class WalletApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private WalletController walletController;

	@Test
	void contextLoads() {
		Assertions.assertThat(userController).isNotNull();
		Assertions.assertThat(walletController).isNotNull();
	}

}
