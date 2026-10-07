package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;

import java.util.List;

public interface IOrderItemsRepository {
  public List<OrderItem> getOrderItems();
}
