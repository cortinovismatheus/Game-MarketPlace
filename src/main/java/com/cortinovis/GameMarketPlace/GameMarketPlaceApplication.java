package com.cortinovis.GameMarketPlace;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrderInput;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrderOutput;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.ProductItem;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
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

		IUserRepository userRepo =
						context.getBean(IUserRepository.class);

		IProductRepository productRepo =
						context.getBean(IProductRepository.class);

		IOrderRepository orderRepo =
						context.getBean(IOrderRepository.class);

		CreateOrder createOrder = new CreateOrder(
						userRepo,
						productRepo,
						orderRepo
		);

		ProductItem product = new ProductItem(2, 2);

		List<ProductItem> productItems = List.of(product);

		CreateOrderInput input = new CreateOrderInput(
						1,
						productItems
		);

		CreateOrderOutput output = createOrder.run(input);

		System.out.println("Order criada com ID: " + output);
	}
}