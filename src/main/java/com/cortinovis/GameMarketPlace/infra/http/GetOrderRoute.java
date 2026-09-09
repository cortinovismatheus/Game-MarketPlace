package com.cortinovis.GameMarketPlace.infra.http;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder.GetOrder;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder.GetOrderOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class GetOrderRoute {
  private final GetOrder getOrder;

  public GetOrderRoute(GetOrder getOrder){
    this.getOrder = getOrder;
  }

  @GetMapping
  public ResponseEntity<GetOrderOutput> getOrder(){
    GetOrderOutput getOrders = getOrder.run();
    return ResponseEntity.status(201).body(getOrders);
  }
}
