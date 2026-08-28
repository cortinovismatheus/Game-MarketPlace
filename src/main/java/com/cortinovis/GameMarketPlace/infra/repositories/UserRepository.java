package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.User;
import com.cortinovis.GameMarketPlace.domain.ports.IUserRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Password;
import com.cortinovis.GameMarketPlace.domain.valueObjects.UserName;

import java.util.Date;
import java.util.Optional;

public class UserRepository implements IUserRepository {
  @Override
  public Optional<User> getById(Integer id) {
    User user = User.restore(id,
            new UserName("Matheus"),
            new CPF("141.050.179-50"),
            new Email("matheus@gmail.com"),
            new Password("Senha123"),
            true,
            new Date(),
            new Date());
    return Optional.of(user);
  }
}
