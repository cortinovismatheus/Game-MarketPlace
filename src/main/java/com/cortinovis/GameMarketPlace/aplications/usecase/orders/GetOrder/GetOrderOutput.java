package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class GetOrderOutput {
  Optional<Order> order;

  public GetOrderOutput(Optional<Order> order) {
    this.order = order;
  }
}
