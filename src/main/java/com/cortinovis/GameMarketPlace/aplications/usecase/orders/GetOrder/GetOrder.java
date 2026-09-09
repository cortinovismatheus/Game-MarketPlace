package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrder;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrder {

  private final IOrderRepository orderRepo;

  public GetOrder(IOrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  public GetOrderOutput run() {
    List<Order> orders = orderRepo.get();
    return new GetOrderOutput(orders);
  }
}