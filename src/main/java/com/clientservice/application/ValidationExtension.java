package com.clientservice.application;

import org.apache.commons.lang3.StringUtils;

public class ValidationExtension {
  private static final String phoneNumberRegexp = "7[0-9]{10}";
  private static final String passportNumberRegexp = "[0-9]{4} [0-9]{6}";

  public static boolean isEmptyOrBlank(String string) {
    return StringUtils.trimToNull(string) == null;
  }

  public static boolean isPhoneNumberValid(String phoneNumber) {
    return phoneNumber.matches(phoneNumberRegexp);
  }

  public static boolean isPassportNumberValid(String passportNumber) {
    return passportNumber.matches(passportNumberRegexp);
  }
}
