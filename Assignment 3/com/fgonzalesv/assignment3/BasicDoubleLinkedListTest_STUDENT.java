package com.fgonzalesv.assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedListTest_STUDENT {

  BasicDoubleLinkedList<Album> linkedAlbum;
  AlbumComparator comparator;

  public Album album1 = new Album("A Perfect Circle", "Thirteenth Step", 2003);
  public Album album2 = new Album("Metallica", "And Justice for All", 1988);
  public Album album3 = new Album("Nirvana", "Bleach", 1989);
  public Album album4 = new Album("Deftones", "Adrenaline", 1995);
  public Album album5 = new Album("The Strokes", "Is this It", 2001);


  @Before
  public void setUp() throws Exception {
    linkedAlbum = new BasicDoubleLinkedList<Album>();
    linkedAlbum.addToEnd(album2);
    linkedAlbum.addToEnd(album3);
    linkedAlbum.addToEnd(album4);
    comparator = new AlbumComparator();
  }

  @After
  public void tearDown() throws Exception {
    linkedAlbum = null;
    comparator = null;
  }

  @Test
  public void testGetSize() {
    assertEquals(3, linkedAlbum.getSize());
  }

  @Test
  public void testAddToEnd() {
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.addToEnd(album5);
    assertEquals(album5, linkedAlbum.getLast());
  }

  @Test
  public void testAddToFront() {
    assertEquals(album2, linkedAlbum.getFirst());
    linkedAlbum.addToFront(album1);
    assertEquals(album1, linkedAlbum.getFirst());
  }

  @Test
  public void testGetFirst() {
    assertEquals(album2, linkedAlbum.getFirst());
    linkedAlbum.addToFront(album1);
    assertEquals(album1, linkedAlbum.getFirst());
  }

  @Test
  public void testGetLast() {
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.addToEnd(album5);
    assertEquals(album5, linkedAlbum.getLast());
  }

  @Test
  public void testToArrayList() {
    ArrayList<Album> list;
    linkedAlbum.addToFront(album1);
    linkedAlbum.addToEnd(album5);
    list = linkedAlbum.toArrayList();
    assertEquals(album1, list.get(0));
    assertEquals(album2, list.get(1));
    assertEquals(album3, list.get(2));
    assertEquals(album4, list.get(3));
    assertEquals(album5, list.get(4));
  }

  @Test
  public void testIteratorSuccessfulNext() {

    linkedAlbum.addToFront(album1);
    linkedAlbum.addToEnd(album5);
    ListIterator<Album> iteratorAlbum = linkedAlbum.iterator();
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album1, iteratorAlbum.next());
    assertEquals(album2, iteratorAlbum.next());
    assertEquals(album3, iteratorAlbum.next());
    assertEquals(album4, iteratorAlbum.next());
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album5, iteratorAlbum.next());
    assertEquals(false, iteratorAlbum.hasNext());
  }

  @Test
  public void testIteratorSuccessfulPrevious() {
    linkedAlbum.addToFront(album1);
    linkedAlbum.addToEnd(album5);
    ListIterator<Album> iteratorAlbum = linkedAlbum.iterator();
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album1, iteratorAlbum.next());
    assertEquals(album2, iteratorAlbum.next());
    assertEquals(album3, iteratorAlbum.next());
    assertEquals(album4, iteratorAlbum.next());
    assertEquals(album5, iteratorAlbum.next());
    assertEquals(true, iteratorAlbum.hasPrevious());
    assertEquals(album5, iteratorAlbum.previous());
    assertEquals(album4, iteratorAlbum.previous());
    assertEquals(album3, iteratorAlbum.previous());
    assertEquals(album2, iteratorAlbum.previous());
    assertEquals(album1, iteratorAlbum.previous());
  }

  @Test
  public void testIteratorNoSuchElementExceptionNext() {

    linkedAlbum.addToFront(album1);
    linkedAlbum.addToEnd(album5);
    ListIterator<Album> iteratorAlbum = linkedAlbum.iterator();
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album1, iteratorAlbum.next());
    assertEquals(album2, iteratorAlbum.next());
    assertEquals(album3, iteratorAlbum.next());
    assertEquals(album4, iteratorAlbum.next());
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album5, iteratorAlbum.next());
    assertEquals(false, iteratorAlbum.hasNext());

    try {
      iteratorAlbum.next();
      assertTrue("Did not throw a NoSuchElementException", false);
    } catch (NoSuchElementException e) {
      assertTrue("Successfully threw a NoSuchElementException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the NoSuchElementException", false);
    }

  }

  @Test
  public void testIteratorNoSuchElementExceptionPrevious() {
    linkedAlbum.addToFront(album1);
    linkedAlbum.addToEnd(album5);
    ListIterator<Album> iteratorAlbum = linkedAlbum.iterator();
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album1, iteratorAlbum.next());
    assertEquals(album2, iteratorAlbum.next());
    assertEquals(album3, iteratorAlbum.next());
    assertEquals(album4, iteratorAlbum.next());
    assertEquals(album5, iteratorAlbum.next());
    assertEquals(true, iteratorAlbum.hasPrevious());
    assertEquals(album5, iteratorAlbum.previous());
    assertEquals(album4, iteratorAlbum.previous());
    assertEquals(album3, iteratorAlbum.previous());
    assertEquals(album2, iteratorAlbum.previous());
    assertEquals(album1, iteratorAlbum.previous());

    try {
      iteratorAlbum.previous();
      assertTrue("Did not throw a NoSuchElementException", false);
    } catch (NoSuchElementException e) {
      assertTrue("Successfully threw a NoSuchElementException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the NoSuchElementException", false);
    }

  }

  @Test
  public void testIteratorUnsupportedOperationException() {
    linkedAlbum.addToFront(album1);
    ListIterator<Album> iteratorAlbum = linkedAlbum.iterator();
    assertEquals(true, iteratorAlbum.hasNext());
    assertEquals(album1, iteratorAlbum.next());
    assertEquals(album2, iteratorAlbum.next());

    try {
      iteratorAlbum.remove();
      assertTrue("Did not throw a UnsupportedOperationException", false);
    } catch (UnsupportedOperationException e) {
      assertTrue("Successfully threw a UnsupportedOperationException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the UnsupportedOperationException", false);
    }

  }

  @Test
  public void testRemove() {
    assertEquals(3, linkedAlbum.getSize());
    assertEquals(album2, linkedAlbum.getFirst());
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.remove(album2, comparator);
    assertEquals(2, linkedAlbum.getSize());
    assertEquals(album3, linkedAlbum.getFirst());
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.addToFront(album1);
    assertEquals(album1, linkedAlbum.getFirst());
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.remove(album4, comparator);
    assertEquals(2, linkedAlbum.getSize());
    assertEquals(album1, linkedAlbum.getFirst());
    assertEquals(album3, linkedAlbum.getLast());

  }

  @Test
  public void testRetrieveFirstElement() {
    assertEquals(3, linkedAlbum.getSize());
    assertEquals(album2, linkedAlbum.getFirst());
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.addToFront(album1);
    assertEquals(album1, linkedAlbum.getFirst());
    assertEquals(album1, linkedAlbum.retrieveFirstElement());
    assertEquals(album2, linkedAlbum.getFirst());
    assertEquals(3, linkedAlbum.getSize());
  }

  @Test
  public void testRetrieveLastElement() {
    assertEquals(3, linkedAlbum.getSize());
    assertEquals(album2, linkedAlbum.getFirst());
    assertEquals(album4, linkedAlbum.getLast());
    linkedAlbum.addToEnd(album5);
    assertEquals(album5, linkedAlbum.getLast());
    assertEquals(album5, linkedAlbum.retrieveLastElement());
    assertEquals(album4, linkedAlbum.getLast());
    assertEquals(3, linkedAlbum.getSize());
  }

  private class AlbumComparator implements Comparator<Album> {

    @Override
    public int compare(Album first, Album second) {
      return first.toString().compareTo(second.toString());
    }
  }

  private class Album {
    String bandName;
    String title;
    int year;

    public Album(String bandName, String title, int year) {
      this.bandName = bandName;
      this.title = title;
      this.year = year;
    }

    public String getBandName() {
      return bandName;
    }

    public String getTitle() {
      return title;
    }

    public int getYear() {
      return year;
    }

    public String toString() {
      return (getBandName() + " " + getTitle() + " " + getYear());
    }

  }

}
