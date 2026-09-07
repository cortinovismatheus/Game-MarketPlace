package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Product;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public ProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Product> getById(Integer id) {

    String sql = """
                SELECT id, owner_id, name, description, price, is_enable, created_at, updated_at
                FROM products WHERE id = ?""";

    return jdbcTemplate.query(
            sql, rs -> {
              if (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getInt("owner_id"),
                        new ProductName(rs.getString("name")),
                        new ProductDescription(rs.getString("description")),
                        new Price(rs.getInt("price")),
                        rs.getBoolean("is_enable"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                return Optional.of(product);
              }

              return Optional.empty();
            },
            id
    );
  }
}