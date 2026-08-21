package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderInput {
  public Integer buyerId;
  public List<ProductItem> products;
}
