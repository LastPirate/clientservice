package com.clientservice.application.service;

import com.clientservice.application.ValidationExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationExtensionTests {

  @ParameterizedTest
  @CsvSource({
      ", true",
      "   , true",
      "., false",
      "123, false",
      "qwe, false"
  })
  public void testIsEmptyOrBlank(String string, boolean expected) {
    //When
    boolean actual = ValidationExtension.isEmptyOrBlank(string);

    //Then
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({
      ", false",
      "    , false",
      "88005553535, false",
      "7800555353, false",
      "780055535353, false",
      "7qwertyuiop, false",
      "7!@#$%^&*()_, false",
      "78005553535, true"
  })
  public void testPhoneNumberValid(String string, boolean expected) {
    //When
    boolean actual = ValidationExtension.isPhoneNumberValid(string);

    //Then
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({
      ", false",
      "    , false",
      "1234567890, false",
      "1234.567890, false",
      "1234q567890, false",
      "1234 56789, false",
      "1234 5678901, false",
      "567890 1234, false",
      "qwer tyuiop, false",
      "1234 567890, true"
  })
  public void testPassportNumberValid(String string, boolean expected) {
    //When
    boolean actual = ValidationExtension.isPassportNumberValid(string);

    //Then
    assertEquals(expected, actual);
  }
}
