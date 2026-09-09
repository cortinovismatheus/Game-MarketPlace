package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

  @Contract("_, _, _, _ -> new")
  private @NonNull OrderItem createValidOrderItem(
          Integer productId,
          String productName,
          int quantity,
          int unitPrice
  ) {
    return new OrderItem(
            productId,
            new ProductName(productName),
            new QuantifyProduct(quantity),
            new Price(unitPrice)
    );
  }

  private Order createValidOrder() {
    OrderItem item = createValidOrderItem(1, "Produto 1", 1, 100);

    return Order.create(
            10,
            20,
            List.of(item)
    );
  }

  @Test
  void shouldCreateOrderSuccessfully() {
    OrderItem item = createValidOrderItem(1, "Produto 1", 2, 100);

    Order order = Order.create(10, 20, List.of(item));

    assertNull(order.getId());
    assertEquals(10, order.getSellerId());
    assertEquals(20, order.getBuyerId());
    assertEquals(OrderStatus.PENDING, order.getStatus());

    assertEquals(1, order.getItems().size());
    assertEquals(200, order.getTotalPrice().getValue());
  }

  @Test
  void shouldCalculateTotalPriceCorrectly() {
    OrderItem item1 = createValidOrderItem(1, "Produto 1", 2, 100);

    OrderItem item2 = createValidOrderItem(2, "Produto 2", 3, 50);

    Order order = Order.create(
            10,
            20,
            List.of(item1, item2)
    );

    assertEquals(350, order.getTotalPrice().getValue());
  }

  @Test
  void shouldStartWithPendingStatus() {
    Order order = createValidOrder();

    assertEquals(OrderStatus.PENDING, order.getStatus());
  }

  @Test
  void shouldConfirmPendingOrder() {
    Order order = createValidOrder();

    order.confirm();

    assertEquals(
            OrderStatus.CONFIRMED,
            order.getStatus()
    );
  }

  @Test
  void shouldNotConfirmNonPendingOrder() {
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
  void shouldNotCompletePendingOrder() {
    Order order = createValidOrder();

    assertThrows(
            IllegalStateException.class,
            order::complete
    );
  }

  @Test
  void shouldNotCompleteCancelledOrder() {
    Order order = createValidOrder();

    order.cancel();

    assertThrows(
            IllegalStateException.class,
            order::complete
    );
  }

  @Test
  void shouldNotCompleteCompletedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.complete();

    assertThrows(
            IllegalStateException.class,
            order::complete
    );
  }

  @Test
  void shouldCancelPendingOrder() {
    Order order = createValidOrder();

    order.cancel();

    assertEquals(
            OrderStatus.CANCELLED,
            order.getStatus()
    );
  }

  @Test
  void shouldCancelConfirmedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.cancel();

    assertEquals(
            OrderStatus.CANCELLED,
            order.getStatus()
    );
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
  void shouldAddItemToPendingOrder() {
    Order order = createValidOrder();

    OrderItem item = createValidOrderItem(2, "Produto 2", 2, 100);

    order.addItem(item);

    assertEquals(2, order.getItems().size());

    assertEquals(300, order.getTotalPrice().getValue());
  }

  @Test
  void shouldNotAddItemToConfirmedOrder() {
    Order order = createValidOrder();

    order.confirm();

    OrderItem item = createValidOrderItem(2, "Produto 2", 1, 100);

    assertThrows(
            IllegalStateException.class,
            () -> order.addItem(item)
    );
  }

  @Test
  void shouldNotAddItemToCancelledOrder() {
    Order order = createValidOrder();

    order.cancel();

    OrderItem item = createValidOrderItem(2, "Produto 2", 1, 100);

    assertThrows(
            IllegalStateException.class,
            () -> order.addItem(item)
    );
  }

  @Test
  void shouldNotAddItemToCompletedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.complete();

    OrderItem item = createValidOrderItem(2, "Produto 2", 1, 100);

    assertThrows(
            IllegalStateException.class,
            () -> order.addItem(item)
    );
  }

  @Test
  void shouldRemoveItemFromPendingOrder() {
    OrderItem item1 = createValidOrderItem(1, "Produto 1", 2, 100);

    OrderItem item2 = createValidOrderItem(2, "Produto 2", 1, 50);

    Order order = Order.create(10, 20, List.of(item1, item2));

    order.removeItem(1);

    assertEquals(1, order.getItems().size());

    assertEquals(50, order.getTotalPrice().getValue());

    assertEquals(2, order.getItems().getFirst().productId());
  }

  @Test
  void shouldNotRemoveItemFromConfirmedOrder() {
    Order order = createValidOrder();

    order.confirm();

    assertThrows(
            IllegalStateException.class,
            () -> order.removeItem(1)
    );
  }

  @Test
  void shouldNotRemoveItemFromCancelledOrder() {
    Order order = createValidOrder();

    order.cancel();

    assertThrows(
            IllegalStateException.class,
            () -> order.removeItem(1)
    );
  }

  @Test
  void shouldNotRemoveItemFromCompletedOrder() {
    Order order = createValidOrder();

    order.confirm();
    order.complete();

    assertThrows(
            IllegalStateException.class,
            () -> order.removeItem(1)
    );
  }

  @Test
  void shouldRestoreOrderSuccessfully() {
    OrderItem item = createValidOrderItem(1, "Produto 1", 2, 100);

    Order order = Order.restore(
            1,
            10,
            20,
            List.of(item),
            new Price(200),
            OrderStatus.CONFIRMED,
            null,
            null
    );

    assertEquals(1, order.getId());

    assertEquals(10, order.getSellerId());

    assertEquals(20, order.getBuyerId());

    assertEquals(OrderStatus.CONFIRMED, order.getStatus());

    assertEquals(200, order.getTotalPrice().getValue());

    assertEquals(1, order.getItems().size());
  }
}