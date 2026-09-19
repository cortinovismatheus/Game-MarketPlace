package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import org.springframework.jdbc.support.KeyHolder;

class OrderRepositoryTest {
  @Test
  void shouldReturnOrderWhenIdExists() throws SQLException {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    ResultSet resultSet = mock(ResultSet.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    when(resultSet.next()).thenReturn(true);

    when(resultSet.getInt("id")).thenReturn(1);
    when(resultSet.getInt("seller_id")).thenReturn(10);
    when(resultSet.getInt("buyer_id")).thenReturn(20);
    when(resultSet.getInt("total_price")).thenReturn(100);
    when(resultSet.getString("status")).thenReturn("PENDING");

    Timestamp createdAt = Timestamp.valueOf("2026-09-19 10:00:00");
    Timestamp updatedAt = Timestamp.valueOf("2026-09-19 10:00:00");

    when(resultSet.getTimestamp("created_at")).thenReturn(createdAt);
    when(resultSet.getTimestamp("updated_at")).thenReturn(updatedAt);

    when(jdbcTemplate.query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(1)
    )).thenAnswer(invocation -> {

      ResultSetExtractor<Order> extractor =
              invocation.getArgument(1);

      return extractor.extractData(resultSet);
    });

    Optional<Order> result = repository.getById(1);

    assertTrue(result.isPresent());

    assertEquals(1, result.get().getId());
    assertEquals(10, result.get().getSellerId());
    assertEquals(20, result.get().getBuyerId());
    assertEquals(100, result.get().getTotalPrice().getValue());
    assertEquals(OrderStatus.PENDING, result.get().getStatus());

    verify(jdbcTemplate).query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(1)
    );
  }

  @Test
  void shouldReturnEmptyWhenOrderDoesNotExist() throws SQLException {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    ResultSet resultSet = mock(ResultSet.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    try {
      when(resultSet.next()).thenReturn(false);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    when(jdbcTemplate.query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(999)
    )).thenAnswer(invocation -> {

      ResultSetExtractor<Order> extractor =
              invocation.getArgument(1);

      return extractor.extractData(resultSet);
    });

    Optional<Order> result = repository.getById(999);

    assertTrue(result.isEmpty());

    verify(jdbcTemplate).query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(999)
    );
  }

  @Test
  void shouldSearchUsingCorrectId() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    when(jdbcTemplate.query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(15)
    )).thenReturn(null);

    repository.getById(15);

    verify(jdbcTemplate).query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(15)
    );
  }

  @Test
  void shouldSaveOrderWithoutItems() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    Order order = mock(Order.class);

    when(order.getSellerId()).thenReturn(1);
    when(order.getBuyerId()).thenReturn(2);
    when(order.getTotalPrice()).thenReturn(new Price(100));
    when(order.getStatus()).thenReturn(OrderStatus.PENDING);
    when(order.getItems()).thenReturn(Collections.emptyList());

    doAnswer(invocation -> {

      KeyHolder keyHolder = invocation.getArgument(1);

      keyHolder.getKeyList().add(
              Map.of("GENERATED_KEY", 1L)
      );

      return 1;

    }).when(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );

    Integer result = repository.save(order);

    assertEquals(1, result);

    verify(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );
  }

  @Test
  void shouldSaveOrderWithItems() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    Order order = mock(Order.class);
    OrderItem item = mock(OrderItem.class);

    when(order.getSellerId()).thenReturn(1);
    when(order.getBuyerId()).thenReturn(2);
    when(order.getTotalPrice()).thenReturn(new Price(200));
    when(order.getStatus()).thenReturn(OrderStatus.PENDING);

    when(order.getItems()).thenReturn(List.of(item));

    when(item.productId()).thenReturn(10);
    when(item.quantityProduct())
            .thenReturn(new QuantifyProduct(2));

    when(item.unitPrice())
            .thenReturn(new Price(100));

    doAnswer(invocation -> {

      KeyHolder keyHolder = invocation.getArgument(1);

      keyHolder.getKeyList().add(
              Map.of("GENERATED_KEY", 1L)
      );

      return 1;

    }).when(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );

    Integer result = repository.save(order);

    assertEquals(1, result);

    verify(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );

    verify(jdbcTemplate).update(
            eq("INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)"),
            eq(1),
            eq(10),
            eq(2),
            eq(100)
    );
  }

  @Test
  void shouldNotSaveItemsWhenOrderHasNoItems() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    Order order = mock(Order.class);

    when(order.getSellerId()).thenReturn(1);
    when(order.getBuyerId()).thenReturn(2);
    when(order.getTotalPrice()).thenReturn(new Price(100));
    when(order.getStatus()).thenReturn(OrderStatus.PENDING);
    when(order.getItems()).thenReturn(Collections.emptyList());

    doAnswer(invocation -> {

      KeyHolder keyHolder = invocation.getArgument(1);

      keyHolder.getKeyList().add(
              Map.of("GENERATED_KEY", 1L)
      );

      return 1;

    }).when(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );

    repository.save(order);

    verify(jdbcTemplate, never()).update(
            eq("INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)"),
            any(),
            any(),
            any(),
            any()
    );
  }

  @Test
  void shouldNotSaveItemsWhenItemsAreNull() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    OrderRepository repository = new OrderRepository(jdbcTemplate);

    Order order = mock(Order.class);

    when(order.getSellerId()).thenReturn(1);
    when(order.getBuyerId()).thenReturn(2);
    when(order.getTotalPrice()).thenReturn(new Price(100));
    when(order.getStatus()).thenReturn(OrderStatus.PENDING);
    when(order.getItems()).thenReturn(null);

    doAnswer(invocation -> {

      KeyHolder keyHolder = invocation.getArgument(1);

      keyHolder.getKeyList().add(
              Map.of("GENERATED_KEY", 1L)
      );

      return 1;

    }).when(jdbcTemplate).update(
            any(),
            any(KeyHolder.class)
    );

    Integer result = repository.save(order);

    assertEquals(1, result);

    verify(jdbcTemplate, never()).update(
            eq("INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)"),
            any(),
            any(),
            any(),
            any()
    );
  }
}