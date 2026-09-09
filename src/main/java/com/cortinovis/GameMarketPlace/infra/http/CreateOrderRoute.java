package com.cortinovis.GameMarketPlace.infra.http;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder.CreateOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder.CreateOrderInput;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder.CreateOrderOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/orders")
public class CreateOrderRoute {

  private final CreateOrder createOrder;

  public CreateOrderRoute(CreateOrder createOrder) {
    this.createOrder = createOrder;
  }

  @PostMapping
  public ResponseEntity<CreateOrderOutput> createOrder(@RequestBody CreateOrderInput createOrderInput) throws AccountNotFoundException {

    CreateOrderOutput createdOrder = createOrder.run(createOrderInput);

    return ResponseEntity.status(201).body(createdOrder);
  }
}