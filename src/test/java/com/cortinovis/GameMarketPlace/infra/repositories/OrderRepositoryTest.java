package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {

  @Test
  void shouldSaveOrderAndItems() {

    // Arrange

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository =
            new OrderRepository(jdbcTemplate);

    OrderItem item = new OrderItem(
            1,
            new ProductName("Produto"),
            new QuantifyProduct(2),
            new Price(1000)
    );

    Order order = new Order(
            1,
            2,
            1,
            List.of(item),
            new Price(2000),
            OrderStatus.PENDING,
            new Date(),
            new Date()
    );

    doAnswer(invocation -> {

      KeyHolder keyHolder = invocation.getArgument(1);

      keyHolder.getKeyList().add(
              java.util.Map.of("id", 10)
      );

      return 1;

    }).when(jdbcTemplate).update(
            any(org.springframework.jdbc.core.PreparedStatementCreator.class),
            any(KeyHolder.class)
    );

    // Act

    Integer orderId = repository.save(order);

    // Assert

    assertEquals(10, orderId);

    verify(jdbcTemplate, times(1)).update(
            any(org.springframework.jdbc.core.PreparedStatementCreator.class),
            any(KeyHolder.class)
    );

    verify(jdbcTemplate, times(1)).update(
            anyString(),
            eq(10),
            eq(1),
            eq(2),
            eq(1000)
    );
  }
}