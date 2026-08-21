package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
  @Test
  void shouldCreateProductWithValidData(){
    Product product = Product.create(1, new ProductName("Conta de algo"), new ProductDescription("alguma coisa"), new Price(1000), true);
    assertNull(product.getId());
    assertNotNull(product.getOwnerId());
    assertNotNull(product.getName());
    assertNotNull(product.getDescription());
    assertNotNull(product.getPrice());
    assertNotNull(product.getIsEnable());
    assertNull(product.getCreated_at());
    assertNull(product.getUpdated_at());
  }

  @Test
  void shouldRestoreProductWithValidData(){
    Product product = Product.restore(1,1, new ProductName("Conta de algo"), new ProductDescription("alguma coisa"), new Price(1000), true, new Date(), new Date());
    assertNotNull(product.getId());
    assertNotNull(product.getOwnerId());
    assertNotNull(product.getName());
    assertNotNull(product.getDescription());
    assertNotNull(product.getPrice());
    assertNotNull(product.getIsEnable());
    assertNotNull(product.getCreated_at());
    assertNotNull(product.getUpdated_at());
  }
}
