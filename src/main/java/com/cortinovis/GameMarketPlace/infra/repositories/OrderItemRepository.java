package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.OrderItem;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.QuantifyProduct;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderItemsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository implements IOrderItemsRepository {

  private final JdbcTemplate jdbcTemplate;

  public OrderItemRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<OrderItem> getOrderItems() {

    String sql = """
                SELECT
                    id,
                    order_id,
                    product_id,
                    quantity,
                    unit_price
                FROM order_items
                """;

    return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> OrderItem.restore(
                    rs.getInt("product_id"),
                    new QuantifyProduct(rs.getInt("quantity")),
                    new Price(rs.getInt("unit_price"))
            )
    );
  }
}