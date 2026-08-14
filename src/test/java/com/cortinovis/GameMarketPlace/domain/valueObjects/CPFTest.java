package com.cortinovis.GameMarketPlace.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

  @Test
  void shouldCreateCPFWithValidValue() {
    CPF cpf = new CPF("529.982.247-25");

    assertEquals("52998224725", cpf.getValue());
  }

  @Test
  void shouldRemoveSpecialCharactersFromCPF() {
    CPF cpf = new CPF("529.982.247-25");

    assertEquals("52998224725", cpf.getValue());
  }

  @Test
  void shouldAcceptCPFWithOnlyNumbers() {
    CPF cpf = new CPF("52998224725");

    assertEquals("52998224725", cpf.getValue());
  }

  @Test
  void shouldRejectCPFWithLessThan11Numbers() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("5299822472")
    );
  }

  @Test
  void shouldRejectCPFWithMoreThan11Numbers() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("529982247250")
    );
  }

  @Test
  void shouldRejectCPFWithAllNumbersEqual() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("11111111111")
    );
  }

  @Test
  void shouldRejectInvalidCPF() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("12345678901")
    );
  }

  @Test
  void shouldRejectCPFWithInvalidCharactersOnly() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("abcdefghijk")
    );
  }

  @Test
  void shouldRejectEmptyCPF() {
    assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("")
    );
  }
}