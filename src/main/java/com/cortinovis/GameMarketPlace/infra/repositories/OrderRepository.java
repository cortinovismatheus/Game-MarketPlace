package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.enums.OrderStatus;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
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
import java.util.*;

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
  public Optional<Order> getById(Integer id) {

    String sql = """
            SELECT
                id,
                seller_id,
                buyer_id,
                total_price,
                status,
                created_at,
                updated_at
            FROM orders
            WHERE id = ?
            """;

    Order result = jdbcTemplate.query(
            sql,
            rs -> {

              if (!rs.next()) {
                System.out.println("NÃO ENCONTROU ORDER");
                return null;
              }

              System.out.println("ENCONTROU ORDER ID: " + rs.getInt("id"));
              System.out.println("SELLER: " + rs.getInt("seller_id"));
              System.out.println("BUYER: " + rs.getInt("buyer_id"));
              System.out.println("TOTAL: " + rs.getInt("total_price"));
              System.out.println("STATUS" + rs.getString("status"));
              System.out.println("CREATED" + rs.getTimestamp("created_at"));
              System.out.println("UPDATED" + rs.getTimestamp("updated_at"));

              return Order.restore(
                      rs.getInt("id"),
                      rs.getInt("seller_id"),
                      rs.getInt("buyer_id"),
                      new ArrayList<>(),
                      new Price(rs.getInt("total_price")),
                      OrderStatus.valueOf(rs.getString("status")),
                      rs.getTimestamp("created_at"),
                      rs.getTimestamp("updated_at")
              );
            },
            id
    );
    return Optional.of(result);
  }

  private @NonNull List<OrderItem> getOrderItems(Integer orderId) {

    String sql = """
        SELECT
            order_id,
            product_id,
            quantity,
            unit_price
        FROM order_items
        WHERE order_id = ?
        """;

    return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> {

              Integer productId = rs.getInt("product_id");
              Integer quantity = rs.getInt("quantity");
              Price unitPrice = new Price(
                      rs.getInt("unit_price")
              );

              return OrderItem.restore(
                      productId,
                      new QuantifyProduct(quantity),
                      unitPrice
              );
            },
            orderId
    );
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