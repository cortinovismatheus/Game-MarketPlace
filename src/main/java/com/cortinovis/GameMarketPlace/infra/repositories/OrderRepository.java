package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;

public class OrderRepository implements IOrderRepository{
  @Override
  public Integer save(Order order) {
    return 0;
  }
}