package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.valueObjects.OrderItem;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
  @Contract(" -> new")
  private @NonNull OrderItem createValidOrderItem() {
    return new OrderItem(1,new ProductName("Produto"), new QuantifyProduct(1), new Price(100));
  }

  private @NonNull Order createValidOrder() {
    List<OrderItem> items = new ArrayList<>();

    items.add(createValidOrderItem());

    return Order.create(items);
  }

  @Test
  void shouldCreateOrderWithValidData(){
    Order order = createValidOrder();
    assertNotNull(order);
    assertNull(order.getId());
    assertNull(order.getBuyerId());
    assertNotNull(order.getItems());
    assertNotNull(order.getTotalPrice());
    assertEquals(OrderStatus.PENDING, order.getStatus());
    assertNull(order.getCreated_at());
    assertNull(order.getUpdated_at());
  }

  @Test
  void shouldRestoreOrder() {
    Integer id = 1;
    Integer buyerId = 10;

    List<OrderItem> items = List.of(
            createValidOrderItem()
    );

    Price totalPrice = new Price(1000);

    Date createdAt = new Date();
    Date updatedAt = new Date();

    Order order = Order.restore(
            id,
            buyerId,
            items,
            totalPrice,
            OrderStatus.CONFIRMED,
            createdAt,
            updatedAt
    );

    assertEquals(id, order.getId());
    assertEquals(buyerId, order.getBuyerId());
    assertEquals(items, order.getItems());
    assertEquals(totalPrice, order.getTotalPrice());
    assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    assertEquals(createdAt, order.getCreated_at());
    assertEquals(updatedAt, order.getUpdated_at());
  }

  @Test
  void shouldCalculateTotalPrice() {
    List<OrderItem> items = List.of(
            new OrderItem(
                    1,
                    new ProductName("Produto 1"),
                    new QuantifyProduct(2),
                    new Price(100)
            ),
            new OrderItem(
                    2,
                    new ProductName("Produto 2"),
                    new QuantifyProduct(3),
                    new Price(50)
            )
    );

    Order order = Order.create(items);

    assertEquals(350, order.getTotalPrice().getValue());
  }

  @Test
  void shouldConfirmPendingOrder() {
    Order order = createValidOrder();

    order.confirm();

    assertEquals(OrderStatus.CONFIRMED, order.getStatus());
  }

  @Test
  void shouldRejectConfirmationWhenOrderIsNotPending() {
    Order order = createValidOrder();

    order.confirm();

    assertThrows(
            IllegalStateException.class,
            order::confirm
    );
  }

  @Test
  void shouldCompleteConfirmedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.complete();

    assertEquals(OrderStatus.COMPLETED, order.getStatus());
  }

  @Test
  void shouldRejectCompletionWhenOrderIsNotConfirmed() {
    Order order = createValidOrder();

    assertThrows(
            IllegalStateException.class,
            order::complete
    );
  }

  @Test
  void shouldNotCompletePendingOrder() {
    Order order = createValidOrder();

    assertThrows(
            IllegalStateException.class,
            order::complete
    );

    assertEquals(OrderStatus.PENDING, order.getStatus());
  }

  @Test
  void shouldCancelPendingOrder() {
    Order order = createValidOrder();

    order.cancel();

    assertEquals(OrderStatus.CANCELLED, order.getStatus());
  }

  @Test
  void shouldCancelConfirmedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.cancel();

    assertEquals(OrderStatus.CANCELLED, order.getStatus());
  }

  @Test
  void shouldNotCancelCompletedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.complete();

    assertThrows(
            IllegalStateException.class,
            order::cancel
    );
  }

  @Test
  void shouldNotCancelCancelledOrder() {
    Order order = createValidOrder();

    order.cancel();

    assertThrows(
            IllegalStateException.class,
            order::cancel
    );
  }

  @Test
  void shouldAddItemAndUpdateTotalPrice() {
    Order order = createValidOrder();

    OrderItem item = new OrderItem(
            2,
            new ProductName("Produto 2"),
            new QuantifyProduct(2),
            new Price(50)
    );

    order.addItem(item);

    assertEquals(2, order.getItems().size());
    assertEquals(200, order.getTotalPrice().getValue());
  }

  @Test
  void shouldRemoveItemAndUpdateTotalPrice() {
    OrderItem item1 = createValidOrderItem();

    OrderItem item2 = new OrderItem(
            2,
            new ProductName("Produto 2"),
            new QuantifyProduct(2),
            new Price(50)
    );

    Order order = Order.create(List.of(item1, item2));

    order.removeItem(2);

    assertEquals(1, order.getItems().size());
    assertEquals(100, order.getTotalPrice().getValue());
  }

  @Test
  void shouldNotChangeOrderWhenRemovingNonExistingItem() {
    Order order = createValidOrder();

    order.removeItem(999);

    assertEquals(1, order.getItems().size());
    assertEquals(100, order.getTotalPrice().getValue());
  }
}
