package com.cortinovis.GameMarketPlace.infra.http.Order;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById.GetOrderById;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById.GetOrderOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/{id}")
public class GetOrderByIdRoute {
  private final GetOrderById getOrderById;

  public GetOrderByIdRoute(GetOrderById getOrderById){
    this.getOrderById = getOrderById;
  }

  @GetMapping
  public ResponseEntity<GetOrderOutput> getOrder(@PathVariable Integer id){
    GetOrderOutput order = getOrderById.run(id);

    return ResponseEntity.ok(order);
  }
}
