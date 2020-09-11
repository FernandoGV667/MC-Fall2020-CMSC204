package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if the password doesn’t contain a lowercase alpha character
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class NoLowerAlphaException extends RuntimeException {
  private static final long serialVersionUID = -2485490956722223156L;
  
  public NoLowerAlphaException(){
    super("The password must contain at least one lowercase alphabetic character");
}
}
