package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
  @Test
  void shouldCreateValidEmail(){
    Email email = new Email("matheus@gmail.com");
    assertEquals("matheus@gmail.com", email.getValue());
  }

  @Test
  void shouldNotAcceptNullEmail(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email(null)
    );
  }

  @Test
  void shouldNotAcceptEmailWithoutAt(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("matheusgmail.com")
    );
  }

  @Test
  void shouldNotAcceptEmailWithoutDot(){
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("matheus@gmailcom")
    );
  }

  @Test
  void shouldNotAcceptEmailWithMoreThanOneAt() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("joao@@gmail.com")
    );
  }

  @Test
  void shouldNotAcceptEmailStartingWithAt() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("@gmail.com")
    );
  }

  @Test
  void shouldNotAcceptEmailEndingWithAt() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("joao@")
    );
  }

  @Test
  void shouldNotAcceptEmailWithoutDomain() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new Email("joao@.com")
    );
  }
}
