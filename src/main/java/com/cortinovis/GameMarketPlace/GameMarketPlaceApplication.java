package com.cortinovis.GameMarketPlace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AccountNotFoundException;

@SpringBootApplication
public class GameMarketPlaceApplication {

	public static void main(String[] args) throws AccountNotFoundException {
		SpringApplication.run(GameMarketPlaceApplication.class, args);
	}
}