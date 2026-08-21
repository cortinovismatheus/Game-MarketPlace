package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.*;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

  private @NonNull Product createValidProduct() {
    return Product.create(
            new OwnerId(1),
            new ProductName("Minecraft"),
            new ProductDescription("Conta de Minecraft"),
            new Price(100),
            true
    );
  }

  @Test
  void shouldCreateOrderItemUsingProductData() {
    Product product = createValidProduct();
    QuantifyProduct quantity = new QuantifyProduct(2);

    OrderItem orderItem = OrderItem.create(product, quantity);

    assertEquals(product.getId(), orderItem.getProductId());
    assertEquals(product.getName(), orderItem.getProductName());
    assertEquals(quantity, orderItem.getQuantityProduct());
    assertEquals(product.getPrice(), orderItem.getUnitPrice());
  }

  @Test
  void shouldCalculateTotalPrice() {
    Product product = createValidProduct();
    QuantifyProduct quantity = new QuantifyProduct(2);

    OrderItem orderItem = OrderItem.create(product, quantity);

    Price totalPrice = orderItem.calculateTotalPrice();

    assertEquals(200, totalPrice.getValue());
  }

  @Test
  void shouldRestoreOrderItem() {
    ProductName productName = new ProductName("Minecraft");
    QuantifyProduct quantity = new QuantifyProduct(2);
    Price unitPrice = new Price(100);

    OrderItem orderItem = OrderItem.restore(
            10,
            productName,
            quantity,
            unitPrice
    );

    assertEquals(10, orderItem.getProductId());
    assertEquals(productName, orderItem.getProductName());
    assertEquals(quantity, orderItem.getQuantityProduct());
    assertEquals(unitPrice, orderItem.getUnitPrice());
  }
}