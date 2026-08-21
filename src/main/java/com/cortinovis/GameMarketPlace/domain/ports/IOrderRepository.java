package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.Order;

public interface IOrderRepository {
  public Integer save(Order order);
}
