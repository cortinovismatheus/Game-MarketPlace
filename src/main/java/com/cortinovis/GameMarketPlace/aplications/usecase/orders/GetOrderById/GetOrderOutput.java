package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
public class GetOrderOutput {

  private final Optional<Order> order;

  private final Integer id;
  private final Integer sellerId;
  private final Integer buyerId;
  private final List<OrderItem> items;
  private final Integer totalPrice;
  private final OrderStatus status;
  private final Date createdAt;
  private final Date updatedAt;

  public GetOrderOutput(@NonNull Optional<Order> order) {

    this.order = order;

    Order orderEntity = order.orElseThrow();

    this.id = orderEntity.getId();
    this.sellerId = orderEntity.getSellerId();
    this.buyerId = orderEntity.getBuyerId();
    this.items = orderEntity.getItems();
    this.totalPrice = orderEntity.getTotalPrice().getValue();
    this.status = orderEntity.getStatus();
    this.createdAt = orderEntity.getCreated_at();
    this.updatedAt = orderEntity.getUpdated_at();
  }
}