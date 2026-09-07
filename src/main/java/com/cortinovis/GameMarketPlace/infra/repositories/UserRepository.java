package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.User;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Password;
import com.cortinovis.GameMarketPlace.domain.valueObjects.UserName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<User> getById(Integer id) {

    String sql = """
                SELECT id, name, cpf, email, password, is_active, created_at, updated_at
                FROM users
                WHERE id = ?
                """;

    return jdbcTemplate.query(
            sql,
            rs -> {
              if (rs.next()) {

                User user = User.restore(
                        rs.getInt("id"),
                        new UserName(rs.getString("name")),
                        new CPF(rs.getString("cpf")),
                        new Email(rs.getString("email")),
                        new Password(rs.getString("password")),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                return Optional.of(user);
              }

              return Optional.empty();
            },
            id
    );
  }
}