package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems;

import com.cortinovis.GameMarketPlace.domain.Exceptions.NotFoundException;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderItemsRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetOrderItems {
  private IOrderItemsRepository orderItemsRepo;

  public GetOrderItems(IOrderItemsRepository orderItemsRepo){
    this.orderItemsRepo = orderItemsRepo;
  }

  public GetOrderItemsOutput run(){
    List<OrderItem> orderItems = orderItemsRepo.getOrderItems();

    if(orderItems.isEmpty()){
        throw new NotFoundException("OrderItems not found!");
    }

    return new GetOrderItemsOutput(orderItems);
  }
}
