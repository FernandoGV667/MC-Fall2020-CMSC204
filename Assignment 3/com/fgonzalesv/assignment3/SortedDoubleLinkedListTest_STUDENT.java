package com.fgonzalesv.assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedListTest_STUDENT {

  SortedDoubleLinkedList<Album> sortedLinkedAlbum;
  AlbumComparator comparator;

  public Album album1 = new Album("A Perfect Circle", "Thirteenth Step", 2003);
  public Album album2 = new Album("Metallica", "And Justice for All", 1988);
  public Album album3 = new Album("Nirvana", "Bleach", 1989);
  public Album album4 = new Album("Deftones", "Adrenaline", 1995);
  public Album album5 = new Album("The Strokes", "Is this It", 2001);
  // Band order = album1, album4, album2, album3, album5

  @Before
  public void setUp() throws Exception {
    comparator = new AlbumComparator();
    sortedLinkedAlbum = new SortedDoubleLinkedList<>(comparator);
  }

  @After
  public void tearDown() throws Exception {
    comparator = null;
    sortedLinkedAlbum = null;
  }

  @Test
  public void testAddToEnd() {
    try {
      sortedLinkedAlbum.addToEnd(album5);
      assertTrue("Did not throw an UnsupportedOperationException", false);
    } catch (UnsupportedOperationException e) {
      assertTrue("Successfully threw an UnsupportedOperationException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the UnsupportedOperationException", false);
    }
  }

  @Test
  public void testAddToFront() {
    try {
      sortedLinkedAlbum.addToFront(album1);
      assertTrue("Did not throw an UnsupportedOperationException", false);
    } catch (UnsupportedOperationException e) {
      assertTrue("Successfully threw an UnsupportedOperationException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the UnsupportedOperationException", false);
    }
  }

  @Test
  public void testIteratorSuccessfulNext() {
    sortedLinkedAlbum.add(album1);
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album3);
    sortedLinkedAlbum.add(album4);
    ListIterator<Album> iterator = sortedLinkedAlbum.iterator();
    assertEquals(true, iterator.hasNext());
    assertEquals(album1, iterator.next());
    assertEquals(album4, iterator.next());
    assertEquals(album2, iterator.next());
    assertEquals(true, iterator.hasNext());
  }


  @Test
  public void testIteratorSuccessfulPrevious() {
    sortedLinkedAlbum.add(album1);
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album3);
    sortedLinkedAlbum.add(album4);
    ListIterator<Album> iterator = sortedLinkedAlbum.iterator();
    assertEquals(true, iterator.hasNext());
    assertEquals(album1, iterator.next());
    assertEquals(album4, iterator.next());
    assertEquals(album2, iterator.next());
    assertEquals(true, iterator.hasPrevious());
    assertEquals(album2, iterator.previous());
    assertEquals(album4, iterator.previous());
    assertEquals(album1, iterator.previous());

  }


  @Test
  public void testIteratorNoSuchElementException() {
    sortedLinkedAlbum.add(album5);
    sortedLinkedAlbum.add(album3);
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album4);
 // Band album order = album4, album2, album3, album5
    ListIterator<Album> iterator = sortedLinkedAlbum.iterator();

    assertEquals(true, iterator.hasNext());
    assertEquals(album4, iterator.next());
    assertEquals(album2, iterator.next());
    assertEquals(album3, iterator.next());
    assertEquals(album5, iterator.next());
    assertEquals(false, iterator.hasNext());
    
    try {
      iterator.next();
      assertTrue("Did not throw a NoSuchElementException", false);
    } catch (NoSuchElementException e) {
      assertTrue("Successfully threw a NoSuchElementException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the NoSuchElementException", false);
    }
  }

  @Test
  public void testIteratorUnsupportedOperationException() {
    sortedLinkedAlbum.add(album5);
    sortedLinkedAlbum.add(album3);
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album4);
    ListIterator<Album> iterator = sortedLinkedAlbum.iterator();

    try {
      iterator.remove();
      assertTrue("Did not throw a UnsupportedOperationException", false);
    } catch (UnsupportedOperationException e) {
      assertTrue("Successfully threw a UnsupportedOperationException", true);
    } catch (Exception e) {
      assertTrue("Threw an exception other than the UnsupportedOperationException", false);
    }
  }

  @Test
  public void testAddAlbum() {
    // Band order = album1, album4, album2, album3, album5
    sortedLinkedAlbum.add(album1);
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album3);
    assertEquals(album1, sortedLinkedAlbum.getFirst());
    assertEquals(album3, sortedLinkedAlbum.getLast());
    sortedLinkedAlbum.add(album4);
    sortedLinkedAlbum.add(album5);
    assertEquals(album1, sortedLinkedAlbum.getFirst());
    assertEquals(album5, sortedLinkedAlbum.getLast());

    assertEquals(album5, sortedLinkedAlbum.retrieveLastElement());
    assertEquals(album3, sortedLinkedAlbum.getLast());
  }

  @Test
  public void testRemoveFirstAlbum() {
    // Band order = album1, album4, album2, album3, album5
    sortedLinkedAlbum.add(album3);
    sortedLinkedAlbum.add(album4);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
    assertEquals(album3, sortedLinkedAlbum.getLast());
    sortedLinkedAlbum.add(album1);
    assertEquals(album1, sortedLinkedAlbum.getFirst());
    // remove the first element
    sortedLinkedAlbum.remove(album1, comparator);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
  }

  @Test
  public void testRemoveEndAlbum() {
     // Band order = album1, album4, album2, album3, album5
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album4);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
    assertEquals(album2, sortedLinkedAlbum.getLast());
    sortedLinkedAlbum.add(album3);
    assertEquals(album3, sortedLinkedAlbum.getLast());
    // remove last element with remove method
    sortedLinkedAlbum.remove(album3, comparator);
    assertEquals(album2, sortedLinkedAlbum.getLast());
  }

  @Test
  public void testRemoveMiddleAlbum() {
    // Band order = album1, album4, album2, album3, album5
    sortedLinkedAlbum.add(album2);
    sortedLinkedAlbum.add(album4);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
    assertEquals(album2, sortedLinkedAlbum.getLast());
    sortedLinkedAlbum.add(album3);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
    assertEquals(album3, sortedLinkedAlbum.getLast());
    assertEquals(3, sortedLinkedAlbum.getSize());
    // remove from middle of list
    sortedLinkedAlbum.remove(album2, comparator);
    assertEquals(album4, sortedLinkedAlbum.getFirst());
    assertEquals(album3, sortedLinkedAlbum.getLast());
    assertEquals(2, sortedLinkedAlbum.getSize());
  }

  private class AlbumComparator implements Comparator<Album> {

    @Override
    public int compare(Album first, Album second) {
      return first.getBandName().compareTo(second.getBandName());
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
