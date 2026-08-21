package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.ProductItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
public class Order {
  Integer id;
  Integer sellerId;
  Integer buyerId;
  List<OrderItem> items;
  Price totalPrice;
  OrderStatus status;
  @Setter
  Date created_at;
  @Setter
  Date updated_at;

  public Order(Integer id, Integer sellerId,Integer buyerId, List<OrderItem> items, Price totalPrice, OrderStatus status,Date created_at, Date updated_at){
    this.id = id;
    this.sellerId = sellerId;
    this.buyerId = buyerId;
    this.items = items;
    this.totalPrice = totalPrice;
    this.status = status;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  @Contract(value = "_, _, _ -> new", pure = true)
  public static @NonNull Order create(Integer sellerId,Integer buyerId, List<OrderItem> items) {
    Order order = new Order(
            null,
            sellerId,
            buyerId,
            new ArrayList<>(items),
            null,
            OrderStatus.PENDING,
            null,
            null
    );

    order.totalPrice = order.calculateTotalPrice();

    return order;
  }

  @Contract(value = "_, _, _, _, _, _, _, _ -> new", pure = true)
  public static @NonNull Order restore(Integer id , Integer sellerId,Integer buyerId, List<OrderItem> items, Price totalPrice, OrderStatus status, Date created_at, Date updated_at){
    return new Order(id, sellerId,buyerId, new ArrayList<>(items), totalPrice, status,created_at, updated_at);
  }

  public void confirm() {
    if (status != OrderStatus.PENDING) {
      throw new IllegalStateException("Only pending orders can be confirmed");
    }

    status = OrderStatus.CONFIRMED;
  }

  public void complete() {
    if (status != OrderStatus.CONFIRMED) {
      throw new IllegalStateException("Only confirmed orders can be completed");
    }

    status = OrderStatus.COMPLETED;
  }

  public void cancel() {
    if (status != OrderStatus.PENDING && status != OrderStatus.CONFIRMED) {
      throw new IllegalStateException("Only pending or confirmed orders can be cancelled");
    }

    status = OrderStatus.CANCELLED;
  }

  private @NonNull Price calculateTotalPrice() {
    int total = 0;

    for (OrderItem item : items) {
      total += item.calculateTotalPrice().getValue();
    }

    return new Price(total);
  }

  public void addItem(OrderItem item) {
    if (status != OrderStatus.PENDING) {
      throw new IllegalStateException(
              "Items can only be added to pending orders"
      );
    }
    items.add(item);
    totalPrice = calculateTotalPrice();
  }

  public void removeItem(Integer productId) {
    items.removeIf(item ->
            Objects.equals(item.getProductId(), productId)
    );

    totalPrice = calculateTotalPrice();
  }
}
