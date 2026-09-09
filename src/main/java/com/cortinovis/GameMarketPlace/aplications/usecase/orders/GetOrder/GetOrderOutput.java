package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder;

import com.cortinovis.GameMarketPlace.domain.entities.Order;

import java.util.List;

public class GetOrderOutput {
  List<Order> orderList;

  public GetOrderOutput(List<Order> orders) {
    this.orderList = orders;
  }
}
