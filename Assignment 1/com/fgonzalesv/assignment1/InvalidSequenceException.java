package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password contains more than 2 of the same character in sequence
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class InvalidSequenceException extends RuntimeException {
  private static final long serialVersionUID = -7298603562572665902L;

  public InvalidSequenceException() {
    super("The password cannot contain more than two of the same character in sequence");
  }
}
