package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.Order;

import java.util.Optional;

public interface IOrderRepository {
  public Integer save(Order order);
  public Optional<Order> getById(Integer id);
}
