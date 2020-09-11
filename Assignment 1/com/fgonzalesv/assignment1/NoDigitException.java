package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password doesn’t contain a numeric character
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class NoDigitException extends RuntimeException {
  private static final long serialVersionUID = 4249766279098595828L;

  public NoDigitException() {
    super("The password must contain at least one digit");
  }
}
