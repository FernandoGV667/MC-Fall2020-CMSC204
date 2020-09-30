package com.fgonzalesv.assignment2;

import java.util.ArrayList;

/**
 * An array base stack implementation. The NotationStack class represents a last-in-first-out 
 * (LIFO) stack of objects.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class NotationStack<T> implements StackInterface<T> {

  private Object[] elements;
  private int first;
  private int last;
  private int numElements;
  private int capacity;

  /**
   * Creates a NotationStack with fixed capacity
   *
   */
  public NotationStack() {
    this.capacity = 20;
    this.elements = new Object[capacity];
  }
  
  
  /**
   * Creates a NotationStack with the given (fixed)
   * capacity.
   *
   * @param capacity the capacity of the stack
   */
  public NotationStack(int capacity) {
    this.capacity = capacity;
    this.first = this.last = -1;
    this.numElements = 0;
    elements = new Object[capacity];
  }

  /**
   * Determines if Stack is empty
   * @return true if Stack is empty, false if not
   */
  @Override
  public boolean isEmpty() {
    return numElements == 0;
  }

  /**
   * Determines if Stack is full
   * @return true if Stack is full, false if not
   */
  @Override
  public boolean isFull() {
    return capacity == numElements;
  }

  /**
   * Deletes and returns the element at the top of the Stack
   * @return the element at the top of the Stack
   */
  @Override
  public T pop() throws StackUnderflowException {
    if (isEmpty()) {
      throw new StackUnderflowException();
    }
    @SuppressWarnings("unchecked")
    T firstInTop = (T) elements[last];
    if (firstInTop == null)
      return null;
    elements[last] = null;
    last--;
    numElements--;
    return firstInTop;
  }

  /**
   * Returns the element at the top of the Stack, does not pop it off the Stack
   * @return the element at the top of the Stack
   */
  @Override
  public T top() throws StackUnderflowException {
    if (isEmpty()) {
      throw new StackUnderflowException();
    }
    @SuppressWarnings("unchecked")
    T firstInTop = (T) elements[last];
    return firstInTop;
  }

  /**
   * Number of elements in the Stack
   * @return the number of elements in the Stack
   */
  @Override
  public int size() {
    return numElements;
  }

  /**
   * Adds an element to the top of the Stack
   * @param e the element to add to the top of the Stack
   * @return true if the add was successful, false if not
   */
  @Override
  public boolean push(T e) throws StackOverflowException {
    if (isFull()) {
      throw new StackOverflowException();
    }

    if (isEmpty()) {
      first = last = 0;
    } else {
      last++;
    }
    numElements++;
    elements[last] = e;
    return true;
  }

  /**
   * Returns the elements of the Stack in a string from bottom to top, the beginning 
   * of the String is the bottom of the stack
   * @return an string which represent the Objects in the Stack from bottom to top
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
   * Returns the string representation of the elements in the Stack, the beginning of the 
   * string is the bottom of the stack
   * Place the delimiter between all elements of the Stack
   * @param delimiter - String to separate the elements of the stack
   * @return string representation of the Stack from bottom to top with elements 
   * separated with the delimiter
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
   * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
   * is the first bottom element of the Stack
   * @param list elements to be added to the Stack from bottom to top
   */
  @Override
  public void fill(ArrayList<T> list) {
    ArrayList<T> cloneList = new ArrayList<>(list);
    cloneList.forEach(t -> {
      try {
        push(t);
      } catch (StackOverflowException ex) {
        ex.getMessage();
      }
    });
  }

}
