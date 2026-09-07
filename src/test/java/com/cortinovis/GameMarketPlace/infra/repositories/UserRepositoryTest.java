package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

  @Test
  void shouldReturnUserWhenUserExists() {

    // Arrange
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    UserRepository repository = new UserRepository(jdbcTemplate);

    Timestamp now = new Timestamp(System.currentTimeMillis());

    when(jdbcTemplate.query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(1)
    )).thenAnswer(invocation -> {

      ResultSetExtractor<?> extractor = invocation.getArgument(1);

      ResultSet resultSet = mock(ResultSet.class);

      when(resultSet.next()).thenReturn(true);

      when(resultSet.getInt("id")).thenReturn(1);
      when(resultSet.getString("name")).thenReturn("Matheus");
      when(resultSet.getString("cpf")).thenReturn("141.050.179-50");
      when(resultSet.getString("email")).thenReturn("matheus@gmail.com");
      when(resultSet.getString("password")).thenReturn("Senha123");
      when(resultSet.getBoolean("active")).thenReturn(true);
      when(resultSet.getTimestamp("created_at")).thenReturn(now);
      when(resultSet.getTimestamp("updated_at")).thenReturn(now);

      return extractor.extractData(resultSet);
    });

    // Act
    Optional<User> result = repository.getById(1);

    // Assert
    assertTrue(result.isPresent());

    User user = result.get();

    assertEquals(1, user.getId());
    assertEquals("Matheus", user.getName().getValue());
    assertEquals("matheus@gmail.com", user.getEmail().getValue());
    assertTrue((Boolean) user.isActive());

    // Verifica se o JdbcTemplate foi chamado
    verify(jdbcTemplate, times(1)).query(
            anyString(),
            any(ResultSetExtractor.class),
            eq(1)
    );
  }
}