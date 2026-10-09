package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public record OrderItem(Integer productId, QuantifyProduct quantityProduct, Price unitPrice) {

  @Contract("_, _ -> new")
  public static @NonNull OrderItem create(@NonNull Product product, QuantifyProduct quantityProduct) {
    return new OrderItem(
            product.getId(),
            quantityProduct,
            product.getPrice()
    );
  }

  @Contract("_, _, _ -> new")
  public static @NonNull OrderItem restore(Integer productId, QuantifyProduct quantityProduct, Price unitPrice) {
    return new OrderItem(
            productId,
            quantityProduct,
            unitPrice
    );
  }

  public @NonNull Price calculateTotalPrice() {
    int total = quantityProduct.getValue() * unitPrice.getValue();

    return new Price(total);
  }

}