package com.fgonzalesv.assignment4;

import java.io.IOException;
import java.util.LinkedList;

/**
 * It is the data structure class that is used with the Course Database Manager class. This is a
 * hash table with buckets implemented with an array of linked lists of CourseDBElements.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class CourseDBStructure implements CourseDBStructureInterface {

  protected LinkedList<CourseDBElement>[] hashTable;

  @SuppressWarnings("unchecked")
  public CourseDBStructure(int i) {
    hashTable = new LinkedList[i];
  }

  @SuppressWarnings("unchecked")
  public CourseDBStructure(String string, int i) {
    hashTable = new LinkedList[i];
  }

  /**
   * The add method will take a CourseDBElement and add it to the data structure. If a linked list
   * at the relevant hash code doesn’t exist, create a LinkedList with the first element being the
   * CDE and add it to the HashTable. If the LinkedList already exists, add the CDE to the existing
   * list
   * 
   * @param element - the CDE element
   */
  @Override
  public void add(CourseDBElement element) {
    // Using Separate Chaining Algorithm: Carrano - Module 13 - Page 31
    int index = getHashIndex(element);

    if (hashTable[index] == null) {
      hashTable[index] = new LinkedList<CourseDBElement>();
      hashTable[index].add(element);
    } else {
      if (hashTable[index].contains(element))
        return;
      else
        hashTable[index].add(element);
    }
  }

  /**
   * The get method will take a CRN number and retrieve the CDE element of the data structure. If
   * the element is not in the data structure, it returns an Exception.
   * 
   * @param crn - the CRN number of the element
   * @return the CDE element
   * @throws IOException If the element is not in the hashtable
   */
  @Override
  public CourseDBElement get(int crn) throws IOException {

    CourseDBElement temp = new CourseDBElement();

    temp.setCRN(crn);
    int index = getHashIndex(temp);
    LinkedList<CourseDBElement> list = hashTable[index];

    return list.stream().filter(c -> c.getCRN() == crn).findAny().orElseThrow(IOException::new);
  }

  /**
   * 
   * @return the size of the data structure´s array
   */
  @Override
  public int getTableSize() {
    return hashTable.length;
  }

  /**
   * getHashIndex computes the index position in the array where the element it´s located. The index
   * is computed using the element´s hashcode
   * 
   * @param element - the element to compute
   * @return the element index
   */
  private int getHashIndex(CourseDBElement element) {
    int hashIndex = element.hashCode() % hashTable.length;
    if (hashIndex < 0) {
      hashIndex += hashTable.length;
    }
    return hashIndex;
  }

}
