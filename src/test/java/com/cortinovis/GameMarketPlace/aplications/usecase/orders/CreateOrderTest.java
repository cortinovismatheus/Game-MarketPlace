package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.Product;
import com.cortinovis.GameMarketPlace.domain.entities.User;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateOrderTest {

  private IUserRepository userRepo;
  private IProductRepository productRepo;
  private IOrderRepository orderRepo;
  private CreateOrder createOrder;

  @BeforeEach
  void setUp() {
    userRepo = mock(IUserRepository.class);
    productRepo = mock(IProductRepository.class);
    orderRepo = mock(IOrderRepository.class);
    createOrder = new CreateOrder(userRepo, productRepo, orderRepo);
  }

  @Test
  void shouldCreateOrderWithMultipleProductsSuccessfully() throws Exception {
    User user = mock(User.class);
    Product product1 = mock(Product.class);
    Product product2 = mock(Product.class);

    Price price1 = mock(Price.class);
    when(price1.getValue()).thenReturn(5000);

    Price price2 = mock(Price.class);
    when(price2.getValue()).thenReturn(1500);

    when(userRepo.getById(1)).thenReturn(Optional.of(user));

    when(productRepo.getById(10)).thenReturn(Optional.of(product1));
    when(product1.getOwnerId()).thenReturn(5);
    when(product1.getPrice()).thenReturn(price1);

    when(productRepo.getById(20)).thenReturn(Optional.of(product2));
    when(product2.getOwnerId()).thenReturn(6);
    when(product2.getPrice()).thenReturn(price2);

    when(orderRepo.save(any(Order.class))).thenReturn(200);

    CreateOrderInput input = new CreateOrderInput(
            1,
            List.of(
                    new ProductItem(10, 2),
                    new ProductItem(20, 1)
            )
    );

    CreateOrderOutput output = createOrder.run(input);

    assertNotNull(output);
    assertEquals(200, output.getOrderId());

    verify(userRepo).getById(1);
    verify(productRepo).getById(10);
    verify(productRepo).getById(20);
    verify(orderRepo).save(any(Order.class));
  }

  @Test
  void shouldThrowExceptionWhenProductListIsEmpty() {
    User user = mock(User.class);
    when(userRepo.getById(1)).thenReturn(Optional.of(user));

    CreateOrderInput input = new CreateOrderInput(1, Collections.emptyList());

    assertThrows(IllegalArgumentException.class, () -> createOrder.run(input));
    verify(orderRepo, never()).save(any());
  }
}