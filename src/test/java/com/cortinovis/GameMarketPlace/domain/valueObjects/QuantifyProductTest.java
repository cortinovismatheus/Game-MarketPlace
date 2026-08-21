package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantifyProductTest {
  @Test
  void shouldCreateValidateQuantifyProduct(){
    QuantifyProduct quantifyProduct = new QuantifyProduct(1);
    assertEquals(1, quantifyProduct.getValue());
  }

  @Test
  void shouldRejectQuantifyProductAsNull(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new QuantifyProduct(null)
    );
  }

  @Test
  void shouldRejectQuantifyProductLessAZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new QuantifyProduct(-1)
    );
  }

  @Test
  void shouldRejectQuantifyProductEqualsAZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new QuantifyProduct(0)
    );
  }
}
