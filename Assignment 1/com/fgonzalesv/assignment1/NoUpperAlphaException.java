package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password doesn’t contain an uppercase alpha character 
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class NoUpperAlphaException extends RuntimeException {
  private static final long serialVersionUID = -6629982197836576074L;

  public NoUpperAlphaException() {
    super("The password must contain at least one uppercase alphabetic character");
  }

}
