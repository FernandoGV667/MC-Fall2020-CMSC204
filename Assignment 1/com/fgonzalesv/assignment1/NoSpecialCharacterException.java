package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password doesn’t contain at least one special character:
 * (!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~) 
 *
 * @author Fernando Gonzales-Vigil
 *
 */
public class NoSpecialCharacterException extends RuntimeException {
  private static final long serialVersionUID = 2168095776341246515L;

  public NoSpecialCharacterException() {
    super("The password must contain at least one special character ");
  }

}
