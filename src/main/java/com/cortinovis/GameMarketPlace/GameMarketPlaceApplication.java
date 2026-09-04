package com.cortinovis.GameMarketPlace;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrderInput;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.ProductItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@SpringBootApplication
public class GameMarketPlaceApplication {

	public static void main(String[] args) throws AccountNotFoundException {

		var context = SpringApplication.run(
						GameMarketPlaceApplication.class,
						args
		);

		CreateOrder createOrder = context.getBean(CreateOrder.class);

		ProductItem product = new ProductItem(2, 2);

		List<ProductItem> productItems = List.of(product);

		CreateOrderInput input = new CreateOrderInput(
						1,
						productItems
		);

		createOrder.run(input);
	}
}