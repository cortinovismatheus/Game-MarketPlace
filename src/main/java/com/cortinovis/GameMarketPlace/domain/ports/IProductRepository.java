package com.cortinovis.GameMarketPlace.domain.ports;

import com.cortinovis.GameMarketPlace.domain.entities.Product;

import java.util.Optional;

public interface IProductRepository {
  public Optional<Product> getById(Integer id);
}
