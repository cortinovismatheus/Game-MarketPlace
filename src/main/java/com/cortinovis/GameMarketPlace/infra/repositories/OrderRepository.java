package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository {

  private final JdbcTemplate jdbcTemplate;

  public OrderRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  @Transactional
  public Integer save(Order order) {
    String orderSql = "INSERT INTO orders (seller_id, buyer_id, total_price, status, created_at, updated_at) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    Date now = new Date();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, order.getSellerId());
      ps.setInt(2, order.getBuyerId());
      ps.setInt(3, order.getTotalPrice().getValue());
      ps.setString(4, order.getStatus().name());
      ps.setTimestamp(5, new Timestamp(now.getTime()));
      ps.setTimestamp(6, new Timestamp(now.getTime()));
      return ps;
    }, keyHolder);

    Integer generatedOrderId = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : null;

    if (generatedOrderId != null && order.getItems() != null && !order.getItems().isEmpty()) {
      saveItems(generatedOrderId, order);
    }

    return generatedOrderId;
  }

  @Override
  public List<Order> get() {
    return List.of();
  }


  private void saveItems(Integer orderId, @NonNull Order order) {
    String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

    for (OrderItem item : order.getItems()) {
      jdbcTemplate.update(
              itemSql,
              orderId,
              item.productId(),
              item.quantityProduct().getValue(),
              item.unitPrice().getValue()
      );
    }
  }


}