package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceTest {
  @Test
  void shouldCreateAValidatePrice(){
    Price price = new Price(1000);
    assertEquals(1000, price.getValue());
  }

  @Test
  void shouldRejectPriceLessThanZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Price(-1)
    );
  }

  @Test
  void shouldRejectPriceEqualsZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Price(0)
    );
  }
}
