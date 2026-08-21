package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Password;
import com.cortinovis.GameMarketPlace.domain.valueObjects.UserName;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Date;

@Getter
public class User {
  Integer id;
  @Setter
  UserName name;
  @Setter
  CPF cpf;
  @Setter
  Email email;
  @Setter
  Password password;
  @Setter
  boolean isActive;
  @Setter
  Date created_at;
  @Setter
  Date updated_at;

  public User(Integer id,UserName name, CPF cpf, Email email, Password password, boolean isActive, Date created_at, Date updated_at){
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.password = password;
    this.isActive = isActive;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  @Contract(value = "_, _, _, _, _ -> new", pure = true)
  public static @NonNull User create(UserName userName, CPF cpf, Email email, Password password, boolean isActive){
    return new User(null,userName, cpf, email, password, isActive, null, null);
  }

  @Contract(value = "_, _, _, _, _, _, _, _ -> new", pure = true)
  public static @NonNull User restore(int id, UserName userName, CPF cpf, Email email, Password password, boolean isActive, Date created_at, Date updated_at){
    return new User(id, userName, cpf, email, password, isActive, created_at, updated_at);
  }

  public @Nullable Object getIsActive() {
    return isActive;
  }
}
