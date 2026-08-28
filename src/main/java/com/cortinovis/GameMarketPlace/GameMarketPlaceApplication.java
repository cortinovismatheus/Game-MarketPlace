package com.cortinovis.GameMarketPlace;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrderInput;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.ProductItem;
import com.cortinovis.GameMarketPlace.domain.entities.Product;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.infra.repositories.OrderRepository;
import com.cortinovis.GameMarketPlace.infra.repositories.ProducRepository;
import com.cortinovis.GameMarketPlace.infra.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GameMarketPlaceApplication {

	public static void main(String[] args) throws AccountNotFoundException {
		IUserRepository userRepository = new UserRepository();
		IProductRepository productRepository = new ProducRepository();
		IOrderRepository orderRepository = new OrderRepository();

		CreateOrder createOrder = new CreateOrder(userRepository, productRepository, orderRepository);

		ProductItem product = new ProductItem(1, 2);
		List<ProductItem> productItem = List.of(product);

		CreateOrderInput createOrderInput = new CreateOrderInput(1, productItem);

		createOrder.run(createOrderInput);

		SpringApplication.run(GameMarketPlaceApplication.class, args);

	}

}
