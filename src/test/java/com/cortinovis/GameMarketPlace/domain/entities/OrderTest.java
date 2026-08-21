package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

  @Contract("_, _, _ -> new")
  private @NonNull Product createProduct(
          int ownerId,
          String name,
          int price
  ) {
    return Product.create(
            new OwnerId(ownerId),
            new ProductName(name),
            new ProductDescription("Descrição do produto"),
            new Price(price),
            true
    );
  }

  private @NonNull OrderItem createValidOrderItem() {
    Product product = createProduct(
            1,
            "Produto",
            100
    );

    return OrderItem.create(
            product,
            new QuantifyProduct(1)
    );
  }

  @Contract(" -> new")
  private @NonNull Order createValidOrder() {
    return Order.create(
            List.of(createValidOrderItem())
    );
  }

  @Test
  void shouldCreateOrderWithValidData() {
    Order order = createValidOrder();

    assertNotNull(order);
    assertNull(order.getId());
    assertNull(order.getBuyerId());
    assertNotNull(order.getItems());
    assertEquals(1, order.getItems().size());
    assertNotNull(order.getTotalPrice());
    assertEquals(100, order.getTotalPrice().getValue());
    assertEquals(OrderStatus.PENDING, order.getStatus());
    assertNull(order.getCreated_at());
    assertNull(order.getUpdated_at());
  }

  @Test
  void shouldRestoreOrder() {
    Integer id = 1;
    Integer buyerId = 10;

    List<OrderItem> items = List.of(
            OrderItem.restore(
                    1,
                    new ProductName("Produto"),
                    new QuantifyProduct(1),
                    new Price(100)
            )
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
  void shouldCalculateTotalPriceFromOrderItems() {
    Product product1 = createProduct(
            1,
            "Produto 1",
            100
    );

    Product product2 = createProduct(
            2,
            "Produto 2",
            50
    );

    OrderItem item1 = OrderItem.create(
            product1,
            new QuantifyProduct(2)
    );

    OrderItem item2 = OrderItem.create(
            product2,
            new QuantifyProduct(3)
    );

    Order order = Order.create(
            List.of(item1, item2)
    );

    /*
     * Produto 1: 2 × 100 = 200
     * Produto 2: 3 × 50  = 150
     * Total:             = 350
     */
    assertEquals(
            350,
            order.getTotalPrice().getValue()
    );
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

    assertEquals(
            OrderStatus.COMPLETED,
            order.getStatus()
    );
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
  void shouldAddItemAndUpdateTotalPrice() {
    Order order = createValidOrder();

    Product product = createProduct(
            2,
            "Produto 2",
            50
    );

    OrderItem item = OrderItem.create(
            product,
            new QuantifyProduct(2)
    );

    order.addItem(item);
    assertEquals(2, order.getItems().size());
    assertEquals(200, order.getTotalPrice().getValue());
  }

  @Test
  void shouldRemoveItemAndUpdateTotalPrice() {
    OrderItem item1 = OrderItem.restore(
            1,
            new ProductName("Produto 1"),
            new QuantifyProduct(1),
            new Price(100)
    );

    OrderItem item2 = OrderItem.restore(
            2,
            new ProductName("Produto 2"),
            new QuantifyProduct(2),
            new Price(50)
    );

    Order order = Order.create(
            List.of(item1, item2)
    );

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