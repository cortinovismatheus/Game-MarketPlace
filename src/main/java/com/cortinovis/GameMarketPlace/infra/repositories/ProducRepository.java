package com.cortinovis.GameMarketPlace.infra.repositories;

import com.cortinovis.GameMarketPlace.domain.entities.Product;
import com.cortinovis.GameMarketPlace.domain.ports.IProductRepository;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Price;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductDescription;
import com.cortinovis.GameMarketPlace.domain.valueObjects.ProductName;

import java.util.Date;
import java.util.Optional;

public class ProducRepository implements IProductRepository {
  @Override
  public Optional<Product> getById(Integer id) {
    Product productTest = new Product(1,
            1,
            new ProductName("produto"),
            new ProductDescription("desc"),
            new Price(1000),
            true,
            new Date(),
            new Date());

    return Optional.of(productTest);
  }
}
