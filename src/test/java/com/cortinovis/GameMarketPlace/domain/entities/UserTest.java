package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

  private User createValidUser(){
    return new User(
            "matheus",
            new CPF("529.982.247-25"),
            new Email("matheus@gmail.com"),
            "senha123"
    );
  }

  @Test
  void shouldCreateValidUser(){
    User user = createValidUser();
    assertAll(
            () -> assertNotNull(user),
            () -> assertEquals("matheus", user.getName()),
            () -> assertEquals(new CPF("529.982.247-25"), user.getCpf()),
            () -> assertEquals(new Email("matheus@gmail.com"), user.getEmail()),
            () -> assertEquals("senha123", user.getPassword()),
            () -> assertTrue(user.isActive())
    );
  }

  @Test
  void shouldNotAllowNullName(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setName(null));
  }

  @Test
  void shouldNotAllowEmptyName(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setName(""));
  }

  @Test
  void shouldNotAllowNullCPF(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setCpf(null));
  }

  @Test
  void shouldNotAllowEmptyCPF(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setCpf(""));
  }

  @Test
  void shouldNotAllowNullEmail(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setEmail(null));
  }

  @Test
  void shouldNotAllowEmptyEmail(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setEmail(""));
  }

  @Test
  void shouldNotAllowNullPassword(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setPassword(null));
  }

  @Test
  void shouldNotAllowEmptyPassword(){
    User user = createValidUser();
    assertThrows(IllegalArgumentException.class,
            () -> user.setPassword(""));
  }
}
