package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.Order;

import java.util.List;

public interface IOrderRepository {
  public Integer save(Order order);
  public List<Order> get();
}
