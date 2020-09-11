package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the length of password is less than 6 characters.
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class LengthException extends RuntimeException {
  private static final long serialVersionUID = -4902259964747550272L;

  public LengthException() {
    super("The password must be at least 6 characters long");
  }
}
