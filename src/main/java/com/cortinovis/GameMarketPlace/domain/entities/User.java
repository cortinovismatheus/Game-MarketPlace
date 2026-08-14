package com.cortinovis.GameMarketPlace.domain.entities;

import com.cortinovis.GameMarketPlace.domain.valueObjects.CPF;
import com.cortinovis.GameMarketPlace.domain.valueObjects.Email;

public class User {
  int id;
  String name;
  CPF cpf;
  Email email;
  String password;
  boolean isActive;
}
