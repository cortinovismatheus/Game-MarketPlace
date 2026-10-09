package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById;

import com.cortinovis.GameMarketPlace.domain.Exceptions.NotFoundException;
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
    Optional<Order> order = Optional.of(orderRepo.getById(id)
            .orElseThrow(() ->
                    new NotFoundException("Order not found!")
            ));

    return new GetOrderOutput(order.get());
  }
}