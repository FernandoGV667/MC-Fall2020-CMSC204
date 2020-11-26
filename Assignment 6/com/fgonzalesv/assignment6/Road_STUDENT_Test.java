package com.fgonzalesv.assignment6;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test all Road methods
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class Road_STUDENT_Test {
  private Town town1, town2, town3;
  private Road road1, road2, road3;

  @Before
  public void setUp() throws Exception {
    town1 = new Town("Town_1");
    town2 = new Town("Town_2");
    town3 = new Town("Town_3");
    road1 = new Road(town1, town2, "Road_1");
    road2 = new Road(town2, town1, "Road_1");
    road3 = new Road(town2, town3, "Road_2");
  }

  @After
  public void tearDown() throws Exception {
    town1 = town2 = town3 = null;
    road1 = road2 = road3 = null;
  }

  @Test
  public void testCompareTo() {
    assertEquals(0, road1.compareTo(road1));
    assertEquals(0, road1.compareTo(road2));
    assertEquals(-1, road2.compareTo(road3));
  }

  @Test
  public void testContains() {
    assertTrue(road1.contains(town1));
    assertTrue(road1.contains(town2));
    assertFalse(road1.contains(town3));
  }

  @Test
  public void testEqualsObject() {
    assertTrue(road1.equals(road1));
    assertTrue(road1.equals(road2));
    assertFalse(road1.equals(road3));
  }

  // Output: "Town_2 via Road_12 to Town_11 6 mi"
  @Test
  public void testToString() {
    assertEquals("Town_1 via Road_1 to Town_2 1 mi", road1.toString());
    assertEquals("Town_2 via Road_1 to Town_1 1 mi", road2.toString());
  }

  @Test
  public void testGetSource() {
    assertEquals(town1, road1.getSource());
    assertEquals(town2, road2.getSource());
    assertEquals(town2, road3.getSource());
  }

  @Test
  public void testGetDestination() {
    assertEquals(town2, road1.getDestination());
    assertEquals(town1, road2.getDestination());
    assertEquals(town3, road3.getDestination());
  }

  @Test
  public void testGetDistance() {
    assertEquals(1, road1.getDistance());
    assertEquals(500, new Road(town3, town2, 500, "Long_Road").getDistance());
  }

  @Test
  public void testGetName() {
    assertEquals("Road_1", road1.getName());
    assertEquals("Road_1", road2.getName());
    assertEquals("Road_2", road3.getName());
  }

}
