package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetOrderById {

  private final IOrderRepository orderRepo;

  public GetOrderById(IOrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  public GetOrderOutput run(Integer id) {
    Optional<Order> order = orderRepo.getById(id);

    return new GetOrderOutput(order);
  }
}