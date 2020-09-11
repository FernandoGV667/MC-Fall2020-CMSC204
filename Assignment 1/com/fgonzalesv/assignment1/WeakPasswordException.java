package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password contains 6 to 9 characters which are otherwise valid
 *
 * @author Fernando Gonzales-Vigil
 *
 */
public class WeakPasswordException extends RuntimeException {
  private static final long serialVersionUID = -2738304578553934504L;

  public WeakPasswordException() {
    super("The password is OK but weak - it contains fewer than 10 characters");
  }



}
