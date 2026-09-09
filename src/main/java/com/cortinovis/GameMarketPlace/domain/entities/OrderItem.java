package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public record OrderItem(Integer productId, ProductName productName, QuantifyProduct quantityProduct, Price unitPrice) {

  @Contract("_, _ -> new")
  public static @NonNull OrderItem create(@NonNull Product product, QuantifyProduct quantityProduct) {
    return new OrderItem(
            product.getId(),
            product.getName(),
            quantityProduct,
            product.getPrice()
    );
  }

  @Contract("_, _, _, _ -> new")
  public static @NonNull OrderItem restore(Integer productId, ProductName productName, QuantifyProduct quantityProduct, Price unitPrice) {
    return new OrderItem(
            productId,
            productName,
            quantityProduct,
            unitPrice
    );
  }

  public @NonNull Price calculateTotalPrice() {
    int total = quantityProduct.getValue() * unitPrice.getValue();

    return new Price(total);
  }

}