package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

@Getter
public class OrderItem {

  private final Integer productId;
  private final ProductName productName;
  private final QuantifyProduct quantityProduct;
  private final Price unitPrice;

  public OrderItem(Integer productId, ProductName productName, QuantifyProduct quantityProduct, Price unitPrice) {
    this.productId = productId;
    this.productName = productName;
    this.quantityProduct = quantityProduct;
    this.unitPrice = unitPrice;
  }

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