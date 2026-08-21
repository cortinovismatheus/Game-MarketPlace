package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductDescriptionTest {
  @Test
  void shouldCreateValidProductDescription(){
    ProductDescription productDescription = new ProductDescription("Teste de descrição");
    assertEquals("Teste de descrição", productDescription.getValue());
  }

  @Test
  void shouldRejectProductDescriptionAsANull(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductDescription(null)
    );
  }

  @Test
  void shouldRejectProductDescriptionIsBlank(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductDescription("")
    );
  }

  @Test
  void shouldRejectDescriptionWithMoreThan100Characters(){
    String description = "a".repeat(101);
    assertThrows(
            IllegalArgumentException.class,
            () -> new ProductDescription(description)
    );
  }
}
