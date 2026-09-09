package com.cortinovis.GameMarketPlace.infra.http;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder.CreateOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder.GetOrder;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.infra.repositories.OrderRepository;
import com.cortinovis.GameMarketPlace.infra.repositories.ProductRepository;
import com.cortinovis.GameMarketPlace.infra.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;


public class MainHttp {

  public static void run() {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    IUserRepository userRepository = new UserRepository(jdbcTemplate);
    IProductRepository productRepository = new ProductRepository(jdbcTemplate);
    IOrderRepository orderRepository = new OrderRepository(jdbcTemplate);

    CreateOrder createOrder = new CreateOrder(userRepository, productRepository, orderRepository);
    GetOrder getOrder = new GetOrder(orderRepository);

    new CreateOrderRoute(createOrder);
    new GetOrderRoute(getOrder);
  }
}