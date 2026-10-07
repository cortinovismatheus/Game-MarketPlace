package com.cortinovis.GameMarketPlace.aplications.usecase.orders;

import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById.GetOrderById;
import com.cortinovis.GameMarketPlace.aplications.usecase.orders.GetOrderById.GetOrderOutput;
import com.cortinovis.GameMarketPlace.domain.entities.Order;
import com.cortinovis.GameMarketPlace.domain.ports.IOrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetOrderByIdTest {

  @Test
  void shouldReturnOrderWhenIdExists() {

    IOrderRepository orderRepo = mock(IOrderRepository.class);
    GetOrderById getOrderById = new GetOrderById(orderRepo);

    Integer id = 1;
    Order order = mock(Order.class);

    when(orderRepo.getById(id))
            .thenReturn(Optional.of(order));

    GetOrderOutput output = getOrderById.run(id);

    assertNotNull(output);
    assertEquals(Optional.of(order), output.getOrder());

    verify(orderRepo).getById(id);
  }

  @Test
  void shouldReturnEmptyWhenOrderDoesNotExist() {

    IOrderRepository orderRepo = mock(IOrderRepository.class);
    GetOrderById getOrderById = new GetOrderById(orderRepo);

    Integer id = 999;

    when(orderRepo.getById(id))
            .thenReturn(Optional.empty());

    GetOrderOutput output = getOrderById.run(id);

    assertNotNull(output);
    assertEquals(Optional.empty(), output.getOrder());

    verify(orderRepo).getById(id);
  }

  @Test
  void shouldCallRepositoryWithCorrectId() {

    IOrderRepository orderRepo = mock(IOrderRepository.class);
    GetOrderById getOrderById = new GetOrderById(orderRepo);

    Integer id = 10;

    when(orderRepo.getById(id))
            .thenReturn(Optional.empty());

    getOrderById.run(id);

    verify(orderRepo).getById(id);
    verify(orderRepo, times(1)).getById(id);
  }
}