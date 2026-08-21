package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {
  @Test
  void shouldCreateAValidPassword(){
    Password password = new Password("Matheus123");
    assertEquals("Matheus123", password.getValue());
  }

  @Test
  void shouldRejectPasswordWithNullValue(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Password(null)
    );
  }

  @Test
  void shouldRejectBlankPassword(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Password("")
    );
  }

  @Test
  void shouldRejectPasswordWithLessThan8Characters(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Password("Matheu1")
    );
  }

  @Test
  void shouldRejectPasswordThatDoesNotContainAnUppercaseLetter(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Password("matheus123")
    );
  }

  @Test
  void shouldRejectPasswordThatDoesNotContainNumbers(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Password("Matheus")
    );
  }
}
