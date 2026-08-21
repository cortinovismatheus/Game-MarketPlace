package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductNameTest {
  @Test
  void shouldCreateProductNameValid(){
    ProductName productName = new ProductName("Nome do produto 01");
    assertEquals("Nome do produto 01", productName.getValue());
  }

  @Test
  void shouldRejectProductNameIsNull(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductName(null)
    );
  }

  @Test
  void shouldRejectProductNameIsBlank(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductName("")
    );
  }

  @Test
  void shouldRejectProductNameWithLessThan4Characters(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductName("Mas")
    );
  }

  @Test
  void shouldRejectProductNameWithMoreThan100Characters(){
    String name = "a".repeat(101);
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductName(name)
    );
  }

  @Test
  void shouldRejectProductNamethatContainsOnlyNumbers(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductName("12345")
    );
  }
}

