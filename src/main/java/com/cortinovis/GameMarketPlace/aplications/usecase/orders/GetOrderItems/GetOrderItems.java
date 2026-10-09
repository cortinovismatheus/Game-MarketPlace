package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderItemsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderItems {
  private final IOrderItemsRepository orderItemsRepo;

  public GetOrderItems(IOrderItemsRepository orderItemsRepo){
    this.orderItemsRepo = orderItemsRepo;
  }

  public List<GetOrderItemsOutput> run() {
    List<OrderItem> items = orderItemsRepo.getOrderItems();

    return items.stream()
            .map(item -> new GetOrderItemsOutput(
                    item.productId(),
                    item.quantityProduct().getValue(),
                    item.unitPrice().getValue()
            ))
            .toList();
  }
}
