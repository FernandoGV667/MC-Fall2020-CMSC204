package com.fgonzalesv.assignment2;

/**
 * This Exception is thrown if a push method is called on a full stack
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class StackOverflowException extends RuntimeException {

  private static final long serialVersionUID = -324591898653728278L;
  
  public StackOverflowException() {
    super("Push method has been called on a full stack");
  }

}
