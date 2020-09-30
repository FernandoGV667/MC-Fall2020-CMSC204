package com.fgonzalesv.assignment2;

/**
 * This Exception is thrown if an enqueue method is called on a full queue
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class QueueOverflowException extends RuntimeException {

  private static final long serialVersionUID = 269190177857139116L;
  
  public QueueOverflowException() {
    super("Enqueue method has been called on a full queue");
  }

}
