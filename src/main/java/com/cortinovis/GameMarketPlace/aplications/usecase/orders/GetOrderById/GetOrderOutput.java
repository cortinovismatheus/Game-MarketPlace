package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.Date;
import java.util.List;

@Getter
public class GetOrderOutput {

  private final Integer id;
  private final Integer sellerId;
  private final Integer buyerId;
  private final List<OrderItem> items;
  private final Integer totalPrice;
  private final OrderStatus status;
  private final Date createdAt;
  private final Date updatedAt;

  public GetOrderOutput(@NonNull Order order) {
    this.id = order.getId();
    this.sellerId = order.getSellerId();
    this.buyerId = order.getBuyerId();
    this.items = order.getItems();
    this.totalPrice = order.getTotalPrice().getValue();
    this.status = order.getStatus();
    this.createdAt = order.getCreated_at();
    this.updatedAt = order.getUpdated_at();
  }
}