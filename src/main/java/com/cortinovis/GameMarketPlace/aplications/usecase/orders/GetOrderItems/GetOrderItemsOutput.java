package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetOrderItemsOutput {
  List<OrderItem> orderItems;

  public GetOrderItemsOutput(List<OrderItem> orderItems){
    this.orderItems = orderItems;
  }
}
