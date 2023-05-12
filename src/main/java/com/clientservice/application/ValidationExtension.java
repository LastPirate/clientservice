package com.clientservice.application;

import org.apache.commons.lang3.StringUtils;

public class ValidationExtension {
  private static final String phoneNumberRegexp = "7\\d{10}";
  private static final String passportNumberRegexp = "\\d{4} \\d{6}";

  public static boolean isEmptyOrBlank(String string) {
    return StringUtils.trimToNull(string) == null;
  }

  public static boolean isPhoneNumberValid(String phoneNumber) {
    return !ValidationExtension.isEmptyOrBlank(phoneNumber) &&
        phoneNumber.matches(phoneNumberRegexp);
  }

  public static boolean isPassportNumberValid(String passportNumber) {
    return !ValidationExtension.isEmptyOrBlank(passportNumber) &&
        passportNumber.matches(passportNumberRegexp);
  }
}
