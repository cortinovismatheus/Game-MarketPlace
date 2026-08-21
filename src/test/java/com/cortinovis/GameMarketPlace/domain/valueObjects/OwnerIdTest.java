package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerIdTest {
  @Test
  void shouldCreateValidOwnerId(){
    OwnerId ownerId = new OwnerId(1);
    assertEquals(1, ownerId.getValue());
  }

  @Test
  void shouldRejectOwnerIdLessThanZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new OwnerId(-1)
    );
  }

  @Test
  void shouldRejectOwnerIdEqualsZero(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new OwnerId(0)
    );
  }
}
