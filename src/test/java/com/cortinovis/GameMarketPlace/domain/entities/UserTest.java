package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Password;
import com.cortinovis.GameMarketPlace.domain.valueObjects.UserName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  @Test
  void shouldCreateUserWithValidData(){
    User user = User.create(new UserName("Matheus"), new CPF("529.982.247-25"), new Email("matheus@gmail.com"), new Password("Matheus123"), true);
    assertNull(user.getId());
    assertNotNull(user.getName());
    assertNotNull(user.getCpf());
    assertNotNull(user.getEmail());
    assertNotNull(user.getPassword());
    assertNotNull(user.getIsActive());
    assertNull(user.getCreated_at());
    assertNull(user.getUpdated_at());
  }

  @Test
  void shouldRestoreUserWithValidData(){
    User user = User.restore(1, new UserName("Matheus"), new CPF("529.982.247-25"), new Email("matheus@gmail.com"), new Password("Matheus123"), true, new Date(), new Date());
    assertNotNull(user.getId());
    assertNotNull(user.getName());
    assertNotNull(user.getCpf());
    assertNotNull(user.getEmail());
    assertNotNull(user.getPassword());
    assertNotNull(user.getIsActive());
    assertNotNull(user.getCreated_at());
    assertNotNull(user.getUpdated_at());
  }
}
