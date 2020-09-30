package com.fgonzalesv.assignment2;

/**
 * This Exception is thrown if the Notation format is incorrect
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class InvalidNotationFormatException extends RuntimeException {

  private static final long serialVersionUID = -4719462251997366246L;
  
  public InvalidNotationFormatException() {
    super("Input's notation format is incorrect");
  }

}
