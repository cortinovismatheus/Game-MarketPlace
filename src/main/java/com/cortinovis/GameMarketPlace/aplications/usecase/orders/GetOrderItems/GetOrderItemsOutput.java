package com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderItems;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public record GetOrderItemsOutput(
        Integer productId,
        Integer quantityProduct,
        Integer unitPrice
) {
  @Contract("_ -> new")
  public static @NonNull GetOrderItemsOutput from(@NonNull OrderItem item) {
    return new GetOrderItemsOutput(
            item.productId(),
            item.quantityProduct().getValue(),
            item.unitPrice().getValue()
    );
  }
}