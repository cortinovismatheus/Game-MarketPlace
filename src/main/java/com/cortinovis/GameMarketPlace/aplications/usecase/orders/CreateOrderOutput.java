package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
  @AllArgsConstructor
public class CreateOrderOutput {
  Integer orderId;
}
