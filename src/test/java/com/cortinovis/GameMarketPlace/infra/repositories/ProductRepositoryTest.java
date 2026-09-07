package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

  @Test
  void shouldReturnProductWhenProductExists() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    ProductRepository repository = new ProductRepository(jdbcTemplate);

    when(jdbcTemplate.query(
            anyString(),
            any(org.springframework.jdbc.core.ResultSetExtractor.class),
            eq(2)
    )).thenAnswer(invocation -> {

      var extractor = invocation.getArgument(
              1,
              org.springframework.jdbc.core.ResultSetExtractor.class
      );

      var resultSet = mock(java.sql.ResultSet.class);

      when(resultSet.next()).thenReturn(true);

      when(resultSet.getInt("id")).thenReturn(2);
      when(resultSet.getInt("owner_id")).thenReturn(1);
      when(resultSet.getString("name")).thenReturn("Produto");
      when(resultSet.getString("description")).thenReturn("Desc");
      when(resultSet.getInt("price")).thenReturn(1000);
      when(resultSet.getBoolean("is_enable")).thenReturn(true);

      var now = new java.sql.Timestamp(System.currentTimeMillis());

      when(resultSet.getTimestamp("created_at")).thenReturn(now);
      when(resultSet.getTimestamp("updated_at")).thenReturn(now);

      return extractor.extractData(resultSet);
    });

    Optional<Product> result = repository.getById(2);

    assertTrue(result.isPresent());

    Product product = result.get();

    assertEquals(2, product.getId());
    assertEquals(1, product.getOwnerId());

    verify(jdbcTemplate).query(
            anyString(),
            any(org.springframework.jdbc.core.ResultSetExtractor.class),
            eq(2)
    );
  }
}