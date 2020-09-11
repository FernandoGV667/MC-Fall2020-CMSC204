package com.fgonzalesv.assignment1;

/**
 * This Exception is thrown if password and re-typed password inputs are not identical
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class UnmatchedException extends RuntimeException {
  private static final long serialVersionUID = -8298259736823227585L;

  public UnmatchedException() {
    super("The passwords do not match");
  }

}
