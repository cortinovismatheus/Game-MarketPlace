package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

public record ProductItem(
  Integer productId,
  Integer quantity) {
  public Integer getProductId() {
    return productId;
  }

  public Integer getQuantityProduct(){
    return quantity;
  }
}
