package com.fgonzalesv.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double-linked list relies on a head (reference to first element of the list) and
 * tail (reference to the last element of the list). Both are set to null when the list is empty.
 * Both point to the same element when there is only one element in the list. A node structure has
 * only three fields: data and the prev and next references. The class must only define the
 * following entities: an inner class Node, an inner class that implements ListIterator (for the
 * iterator method), head and tail references and an integer representing the list size. However
 * only the hasNext(), next(), hasPrevious() and previous() methods of ListIterator need to be
 * implemented, all other methods can throw the UnsupportedOperationException. All the entities are
 * defined as protected so they can be accessed by the subclasses.
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
  protected Node head;
  protected Node tail;
  protected int size;

  public BasicDoubleLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Adds an element to the end of the list.
   * 
   * @param data - the data for the Node within the linked list
   * @return reference to the current object
   */
  public BasicDoubleLinkedList<T> addToEnd(T data) {
    Node newTail = new Node(data);

    if (size == 0) {
      head = newTail;
      tail = head;
    } else {
      tail.next = newTail;
      newTail.prev = tail;
      tail = newTail;
    }
    size++;
    return this;
  }

  /**
   * Adds element to the front of the list.
   * 
   * @param data - the data for the Node within the linked list
   * @return reference to the current object
   */
  public BasicDoubleLinkedList<T> addToFront(T data) {
    Node newHead = new Node(data);
    if (size == 0) {
      head = newHead;
      tail = head;
    } else {
      head.prev = newHead;
      newHead.next = head;
      head = newHead;
    }
    size++;
    return this;
  }

  /**
   * Returns but does not remove the first element from the list. If there are no elements the
   * method returns null.
   * 
   * @return the data element or null
   */
  public T getFirst() {
    if (size == 0) {
      return null;
    }
    return head.data;
  }

  /**
   * Returns but does not remove the last element from the list. If there are no elements the method
   * returns null.
   * 
   * @return the data element or null
   */
  public T getLast() {
    if (size == 0) {
      return null;
    }
    return tail.data;
  }

  /**
   * This method just returns the value of the instance variable you use to keep track of size.
   * 
   * @return the size of the linked list
   */
  public int getSize() {
    return size;
  }

  /**
   * This method implements ListIterator and defines the methods of hasNext(), next(), hasPrevious()
   * and previous().
   * 
   * @return an iterator positioned at the head of the list
   * @throws UnsupportedOperationException When there´s no more elements (at the end of the list and
   *         calling next() or at the beginning of the list and calling previous()).
   * @throws NoSuchElementException If remove(), add(), nextIndex() and previousIndex() and set()
   *         methods are called
   */
  public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
    return new NodeIterator();
  }

  /**
   * Removes the first instance of the targetData from the list.
   * 
   * @param targetData - the data element to be removed
   * @param comparator - the comparator to determine equality of data elements
   * @return reference to the current object
   */
  public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
    Node curNode = head;
    while (curNode != null) {
      if (comparator.compare(targetData, curNode.data) == 0) {
        if (curNode == head) {
          head = head.next;
        } else if (curNode == tail) {
          tail = tail.prev;
        } else {
          curNode.prev.next = curNode.next;
        }
        size--;
        return this;
      }
      curNode = curNode.next;
    }
    return this;
  }

  /**
   * Removes and returns the first element from the list. If there are no elements the method
   * returns null.
   * 
   * @return the data element or null
   */
  public T retrieveFirstElement() {
    if (size == 0) {
      return null;
    }
    T first = head.data;
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }
    size--;
    return first;
  }

  /**
   * Removes and returns the last element from the list. If there are no elements the method returns
   * null.
   * 
   * @return the data element or null
   */
  public T retrieveLastElement() {
    if (size == 0) {
      return null;
    }
    T last = tail.data;
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      tail = tail.prev;
      tail.next = null;
    }
    size--;
    return last;
  }

  /**
   * Returns an arraylist of the items in the list from head of list to tail of list
   * 
   * @return an arraylist of the items in the list
   */
  public ArrayList<T> toArrayList() {
    ArrayList<T> list = new ArrayList<>();
    Node curNode = head;

    while (curNode != null) {
      list.add(curNode.data);
      curNode = curNode.next;
    }
    return list;
  }

  /**
   * A Node is a basic unit of a data structure. Node contains data and links to other nodes. This
   * node class is specific for double linked lists because it has a link for the previous and the
   * next node.
   */
  public class Node {
    protected Node prev;
    protected Node next;
    protected T data;

    public Node(T data) {
      this.prev = null;
      this.next = null;
      this.data = data;
    }
  }

  /**
   * An iterator for lists that allows the programmer to traverse the list in either direction, and
   * obtain the iterator's current position in the list. Modification options have been disabled.
   */
  public class NodeIterator implements ListIterator<T> {

    protected Node current = head;
    protected Node last;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public boolean hasPrevious() {
      return last != null;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (hasNext()) {
        last = current;
        current = current.next;
        return last.data;
      }
      throw new NoSuchElementException("No next elements available in List");
    }

    @Override
    public T previous() throws NoSuchElementException {
      if (hasPrevious()) {
        current = last;
        last = last.prev;
        return current.data;
      }
      throw new NoSuchElementException("No previous elements available in List");
    }

    @Override
    public int nextIndex() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

    @Override
    public int previousIndex() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void remove() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void set(T data) throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void add(T data) throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

  }

}
