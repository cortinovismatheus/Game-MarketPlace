package com.cortinovis.GameMarketPlace.domain.valueObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderItem {
  private final Integer productId;
  @Setter
  private ProductName productName;
  @Setter
  private QuantifyProduct quantityProduct;
  @Setter
  private Price unitPrice;

  public OrderItem(Integer productId, ProductName productName, QuantifyProduct quantifyProduct, Price unitPrice){
    this.productId = productId;
    this.productName = productName;
    this.quantityProduct = quantifyProduct;
    this.unitPrice = unitPrice;
  }
}
