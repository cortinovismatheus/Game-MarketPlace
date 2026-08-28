package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import com.cortinovis.GameMarketPlace.domain.entities.Product;
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
