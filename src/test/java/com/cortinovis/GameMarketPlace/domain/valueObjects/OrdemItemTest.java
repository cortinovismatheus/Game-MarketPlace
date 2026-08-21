package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrdemItemTest {
  @Contract(" -> new")
  private @NonNull OrderItem createValidOrderItem() {
    return new OrderItem(1, new ProductName("Produto"), new QuantifyProduct(1), new Price(100));
  }

  @Test
  void shouldCreateOrdemItemWithValidData(){
    OrderItem orderItem = createValidOrderItem();
    assertEquals(1, orderItem.getProductId());
    assertEquals("Produto", orderItem.getProductName().getValue());
    assertEquals(1, orderItem.getQuantityProduct().getValue());
    assertEquals(100, orderItem.getUnitPrice().getValue());
  }
}
