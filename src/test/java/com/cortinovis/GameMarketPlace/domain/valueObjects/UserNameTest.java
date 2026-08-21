package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserNameTest {
  @Test
  void shouldCreateValidName(){
    UserName name = new UserName("Matheus");
    assertEquals("Matheus", name.getValue());
  }

  @Test
  void shouldRejectNullName(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new UserName(null)
    );
  }

  @Test
  void shouldRejectNullIsBlank(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new UserName("")
    );
  }

  @Test
  void shouldRejectNameWithLessThan4Characters(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new UserName("Ooo")
    );
  }

  @Test
  void shouldRejectNameWithMoreThan30Characters(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new UserName("MatheusCortinovisMatheusCortinovis")
    );
  }

  @Test
  void shouldRejectNameContainingNumbers(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new UserName("Matheus1")
    );
  }
}
