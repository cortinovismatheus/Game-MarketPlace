package com.cortinovis.GameMarketPlace.aplications.usecase.orders.CreateOrder;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.ProductItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderInput {
  public Integer buyerId;
  public List<ProductItem> products;

  public CreateOrderInput(Integer buyerId, List<ProductItem> productItems) {
    this.buyerId = buyerId;
    this.products = productItems;
  }
}
