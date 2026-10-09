package com.cortinovis.GameMarketPlace.infra.http.OrderItem;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems.GetOrderItems;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems.GetOrderItemsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class GetOrderItemsRoute {

  private final GetOrderItems getOrderItems;

  public GetOrderItemsRoute(GetOrderItems getOrderItems) {
    this.getOrderItems = getOrderItems;
  }

  @GetMapping
  public ResponseEntity<List<GetOrderItemsOutput>> getOrderItems() {
    List<GetOrderItemsOutput> orders = getOrderItems.run();

    return ResponseEntity.ok(orders);
  }
}