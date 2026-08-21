package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.entities.Product;
import com.cortinovis.GameMarketPlace.domain.entities.User;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jspecify.annotations.NonNull;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateOrder {

  private final IUserRepository userRepo;
  private final IProductRepository productRepo;
  private final IOrderRepository orderRepo;

  public CreateOrder(
          IUserRepository userRepo,
          IProductRepository productRepo,
          IOrderRepository orderRepo
  ) {
    this.userRepo = userRepo;
    this.productRepo = productRepo;
    this.orderRepo = orderRepo;
  }

  public CreateOrderOutput run(@NonNull CreateOrderInput input) throws AccountNotFoundException {
    Integer ownerId = 0;
    List<OrderItem> items = new ArrayList<>();

    Optional<User> buyer = this.userRepo.getById(input.buyerId);

    if (buyer.isEmpty()) {
      throw new AccountNotFoundException(
              "O comprador não existe"
      );
    }

    for (ProductItem productItem : input.products) {
      Optional<Product> product =
              this.productRepo.getById(productItem.productId());

      if (product.isEmpty()) {
        throw new IllegalArgumentException(
                "O produto " + productItem.productId() + " não existe"
        );
      }

      QuantifyProduct quantifyProduct = new QuantifyProduct(productItem.quantity());

      OrderItem orderItem = OrderItem.create(
              product.get(),
              quantifyProduct
      );

      items.add(orderItem);
      ownerId = product.get().getOwnerId();
    }

    Order order = Order.create(
            ownerId,
            input.buyerId,
            items
    );

    Integer orderId = orderRepo.save(order);

    return new CreateOrderOutput(ownerId);
  }
}