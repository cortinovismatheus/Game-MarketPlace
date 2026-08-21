package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.User;

import java.util.Optional;

public interface IUserRepository {
  public Optional<User> getById(Integer id);
}