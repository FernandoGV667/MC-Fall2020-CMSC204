package com.fgonzalesv.assignment1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates that a string or a collection of strings is valid or not as a password. This utility
 * class will be used to verify the different requirements that a string requires to be valid and to
 * be safe.
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class PasswordCheckerUtility {

  /**
   * Returns true if valid password. Throws exceptions for invalid passwords. Valid if password is
   * greater than or equal to 6 characters, has at least one digit, has at least on upper-case
   * alphabetic character, has at least on lower-case alphabetic character, and has no more than two
   * of the same character in a row.
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if valid password, set up to return false if an invalid password, but throws an
   *         exception instead.
   * @throws LengthException - thrown if length is less than 6 characters
   * @throws NoUpperAlphaException - thrown if no uppercase alphabetic
   * @throws NoLowerAlphaException - thrown if no lowercase alphabetic
   * @throws NoDigitException - thrown if no digit
   * @throws NoSpecialCharacterException - thrown if no special character
   * @throws InvalidSequenceException - thrown if more than 2 of same character.
   */
  public static boolean isValidPassword(String passwordString)
      throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException,
      NoSpecialCharacterException, InvalidSequenceException {
    return isLongEnough(passwordString) && hasUpperCase(passwordString)
        && hasLowerCase(passwordString) && hasDigit(passwordString)
        && hasSpecialCharacter(passwordString) && hasValidSequence(passwordString);
  }

  /**
   * Return true if length of password is greater than or equal to 6 but less than or equal to 9
   * 
   * @param passwordString - - string to be checked if weak password
   * @return true if length of password is greater than or equal to 6 but less than or equal to 9
   */
  public static boolean isWeakPassword(String passwordString) {
    return isValidPassword(passwordString) && (passwordString.length() < 10);
  }

  /**
   * Returns an arraylist of invalid passwords (weak passwords are not considered invalid)
   * 
   * @param passwords - arraylist of passwords to check for validity
   * @return arraylist of invalid passwords. It will not return weak passwords.
   */
  public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {

    ArrayList<String> invalidPasswords = new ArrayList<>();
    for (String s : passwords) {
      try {
        isValidPassword(s);
      } catch (Exception ex) {
        invalidPasswords.add(s + " " + ex.getMessage());
      }
    }
    return invalidPasswords;
  }

  /**
   * Returns true if the password length is greater or equal than 6
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if length of password is greater than or equal to 6
   */
  private static boolean isLongEnough(String passwordString) {
    if (passwordString.length() >= 6) {
      return true;
    } else {
      throw new LengthException();
    }
  }

  /**
   * Return true if password contains at least one digit and throws exception if not
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if password has at least one digit
   */
  private static boolean hasDigit(String passwordString) {
    for (Character c : passwordString.toCharArray()) {
      if (Character.isDigit(c)) {
        return true;
      }
    }
    throw new NoDigitException();
  }

  /**
   * Returns true if password has a lowercase character and throws exception if not.
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if password has at least one lowercase alphanumeric character
   */
  private static boolean hasLowerCase(String passwordString) {
    for (Character c : passwordString.toCharArray()) {
      if (Character.isLowerCase(c)) {
        return true;
      }
    }
    throw new NoLowerAlphaException();
  }

  /**
   * Return true if password has at least one uppercase character and throws exception if not.
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if password has at least one uppercase alphanumeric character
   */
  private static boolean hasUpperCase(String passwordString) {
    for (Character c : passwordString.toCharArray()) {
      if (Character.isUpperCase(c)) {
        return true;
      }
    }
    throw new NoUpperAlphaException();
  }

  /**
   * Return true if password has at least one special character (!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~) 
   * and throws exception if not.
   * 
   * @param passwordString - - string to be checked for validity
   * @return true if password has at least one special character
   */
  private static boolean hasSpecialCharacter(String passwordString) {
    Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
    Matcher matcher = pattern.matcher(passwordString);
    if (!matcher.find()) {
      throw new NoSpecialCharacterException();
    }
    return true;
  }
  
  /**
   * Return true if password characters do not repeat more than 2 times in a row and throw exception
   * if a sequence of 3 repeated characters are detected.
   * 
   * @param passwordString- - string to be checked for validity
   * @return true if password characters do not repeat more than twice in a row
   */
  private static boolean hasValidSequence(String passwordString) {
    for (int i = 0; i < passwordString.length() - 2; i++) {
      if (passwordString.charAt(i) == passwordString.charAt(i + 1)) {
        if (passwordString.charAt(i + 1) == passwordString.charAt(i + 2)) {
          throw new InvalidSequenceException();
        }
      }
    }
    return true;
  }

}
