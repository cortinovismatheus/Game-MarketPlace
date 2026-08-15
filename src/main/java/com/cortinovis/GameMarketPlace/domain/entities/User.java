package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
  private static int nextId = 1;

  int id;
  String name;
  CPF cpf;
  Email email;
  String password;
  boolean isActive;

  public User(String name, CPF cpf, Email email, String password){
    if(name == null){
      throw new IllegalArgumentException("Name canot be empty");
    }

    if(password == null){
      throw new IllegalArgumentException("Password canot be empty");
    }

    this.id = nextId++;
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.password = password;
    this.isActive = true;
  }

  public void setName(String name){
    if(name == null || name.isBlank()){
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = name;
  }

  public void setCpf(String cpf){
    if(cpf == null || cpf.isBlank()){
      throw new IllegalArgumentException("CPF cannot be null");
    }
  }

  public void setEmail(String email){
    if(email == null || email.isBlank()){
      throw new IllegalArgumentException("Email cannot be null");
    }
  }

  public void setPassword(String password){
    if(password == null || password.isBlank()){
      throw new IllegalArgumentException("Password cannot be null");
    }
  }
}
