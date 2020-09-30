package com.fgonzalesv.assignment2;

import java.util.ArrayList;
import java.util.Objects;

/**
 * An array base queue implementation. This queue orders elements FIFO (first-in-first-out).  
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class NotationQueue<T> implements QueueInterface<T> {

  private Object[] elements;
  private int first;
  private int last;
  private int numElements;
  private int capacity;


  /**
   * Creates a NotationQueue with fixed capacity
   *
   */
  public NotationQueue() {
    capacity = 20;
    elements = new Object[capacity];

  }

  /**
   * Creates a NotationQueue with the given (fixed)
   * capacity.
   *
   * @param capacity the capacity of this queue
   */
  public NotationQueue(int capacity) {
    this.capacity = capacity;
    this.first = this.last = -1;
    this.numElements = 0;
    elements = new Object[capacity];
  }

  /**
   * Determines if Queue is empty
   * @return true if Queue is empty, false if not
   */
  @Override
  public boolean isEmpty() {
    return numElements == 0;
  }

  /**
   * Determines if Queue is full
   * @return true if Queue is full, false if not
   */
  @Override
  public boolean isFull() {
    return capacity == numElements;
  }

  /**
   * Deletes and returns the element at the front of the Queue
   * @return the element at the front of the Queue
   */
  @Override
  public T dequeue() throws QueueUnderflowException {
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
    @SuppressWarnings("unchecked")
    T firstInLine = (T) elements[first];
    if (firstInLine == null)
      return null;
    elements[first] = null;
    first++;
    numElements--;
    return firstInLine;
  }

  /**
   * Number of elements in the Queue
   * @return the number of elements in the Queue
   */
  @Override
  public int size() {
    return numElements;
  }

  /**
   * Adds an element to the end of the Queue
   * @param e the element to add to the end of the Queue
   * @return true if the add was successful, false if not
   */
  @Override
  public boolean enqueue(T e) throws QueueOverflowException {
    if (isFull()) {
      throw new QueueOverflowException();
    }
    
    if(isEmpty()) {
      first = last = 0;
    } else {
      last++;
    }
    numElements++;
    elements[last] = e;
    return true;
  }

  /**
   * Returns the string representation of the elements in the Queue, 
   * the beginning of the string is the front of the queue
   * @return string representation of the Queue with elements
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
   
    for (int i = first; i <= last; i++) {
      sb.append(elements[i]);
    }
    return sb.toString();
  }

  /**
   * Returns the string representation of the elements in the Queue, 
   * the beginning of the string is the front of the queue
   * Place the delimiter between all elements of the Queue
   * @param delimiter - string used to separate queue elements
   * @return string representation of the Queue with elements separated with the delimiter
   */
  @Override
  public String toString(String delimiter) {
    StringBuilder sb = new StringBuilder();
    
    for (int i = first; i < last; i++) {
      sb.append(elements[i] + delimiter);
    }
    sb.append(elements[last]);
    return sb.toString();
  }

  /**
   * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
   * is the first element in the Queue
   * @param list elements to be added to the Queue
   */
  @Override
  public void fill(ArrayList<T> list) {
    ArrayList<T> cloneList = new ArrayList<>(list);
    cloneList.forEach(t -> {
      try {
        enqueue(t);
      } catch (QueueOverflowException ex) {
        ex.getMessage();
      }
    });
  }

}
