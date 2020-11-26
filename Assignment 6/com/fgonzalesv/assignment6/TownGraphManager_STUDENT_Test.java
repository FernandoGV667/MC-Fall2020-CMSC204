package com.fgonzalesv.assignment6;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test all TownGraphManager methods
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class TownGraphManager_STUDENT_Test {

  private TownGraphManagerInterface graph;
  private String[] town;

  @Before
  public void setUp() throws Exception {
    graph = new TownGraphManager();
    town = new String[9];

    for (int i = 1; i < 9; i++) {
      town[i] = "Town_" + i;
      graph.addTown(town[i]);
    }

    graph.addRoad(town[1], town[5], 3, "Road_1");
    graph.addRoad(town[1], town[7], 4, "Road_2");
    graph.addRoad(town[2], town[4], 6, "Road_3");
    graph.addRoad(town[2], town[5], 4, "Road_4");
    graph.addRoad(town[3], town[4], 2, "Road_5");
    graph.addRoad(town[3], town[6], 2, "Road_6");
    graph.addRoad(town[3], town[8], 3, "Road_7");
    graph.addRoad(town[6], town[8], 5, "Road_8");

  }

  @After
  public void tearDown() throws Exception {
    graph = null;
  }

  @Test
  public void testAddRoad() {
    ArrayList<String> roads = graph.allRoads();
    assertEquals("Road_1", roads.get(0));
    assertEquals("Road_2", roads.get(1));
    graph.addRoad(town[1], town[8], 5, "Road_10");
    roads = graph.allRoads();
    assertEquals("Road_1", roads.get(0));
    assertEquals("Road_10", roads.get(1));
    assertEquals("Road_2", roads.get(2));

  }

  @Test
  public void testGetRoad() {
    assertEquals("Road_4", graph.getRoad(town[2], town[5]));
    assertEquals("Road_7", graph.getRoad(town[3], town[8]));
  }

  @Test
  public void testAddTown() {
    assertEquals(false, graph.containsTown("Town_9"));
    graph.addTown("Town_9");
    assertEquals(true, graph.containsTown("Town_9"));
  }

  @Test
  public void testDisjointGraph() {
    assertEquals(false, graph.containsTown("Town_10"));
    graph.addTown("Town_10");
    ArrayList<String> path = graph.getPath(town[1], "Town_10");
    assertFalse(path.size() > 0);
  }

  @Test
  public void testContainsTown() {
    assertEquals(true, graph.containsTown("Town_5"));
    assertEquals(false, graph.containsTown("Town_10"));
  }

  @Test
  public void testContainsRoadConnection() {
    assertEquals(true, graph.containsRoadConnection(town[1], town[7]));
    assertEquals(false, graph.containsRoadConnection(town[1], town[8]));
  }

  @Test
  public void testAllRoads() {
    ArrayList<String> roads = graph.allRoads();
    assertEquals("Road_1", roads.get(0));
    assertEquals("Road_2", roads.get(1));
    assertEquals("Road_3", roads.get(2));
    assertEquals("Road_5", roads.get(4));
    assertEquals("Road_8", roads.get(7));
  }

  @Test
  public void testDeleteRoadConnection() {
    assertEquals(true, graph.containsRoadConnection(town[1], town[7]));
    graph.deleteRoadConnection(town[1], town[7], "Road_2");
    assertEquals(false, graph.containsRoadConnection(town[1], town[7]));
  }

  @Test
  public void testDeleteTown() {
    assertEquals(true, graph.containsTown("Town_4"));
    graph.deleteTown(town[4]);
    assertEquals(false, graph.containsTown("Town_4"));
  }

  @Test
  public void testAllTowns() {
    ArrayList<String> roads = graph.allTowns();
    assertEquals("Town_1", roads.get(0));
    assertEquals("Town_2", roads.get(1));
    assertEquals("Town_3", roads.get(2));
    assertEquals("Town_4", roads.get(3));
    assertEquals("Town_8", roads.get(7));
  }

  @Test
  public void testGetPath() {
    ArrayList<String> path = graph.getPath(town[1], town[8]);
    assertNotNull(path);
    assertTrue(path.size() > 0);
    assertEquals("Town_1 via Road_1 to Town_5 3 mi", path.get(0).trim());
    assertEquals("Town_5 via Road_4 to Town_2 4 mi", path.get(1).trim());
    assertEquals("Town_2 via Road_3 to Town_4 6 mi", path.get(2).trim());
    assertEquals("Town_4 via Road_5 to Town_3 2 mi", path.get(3).trim());
    assertEquals("Town_3 via Road_7 to Town_8 3 mi", path.get(4).trim());

  }

  @Test
  public void testGetPathA() {
    ArrayList<String> path = graph.getPath(town[1], town[7]);
    assertNotNull(path);
    assertTrue(path.size() > 0);
    assertEquals("Town_1 via Road_2 to Town_7 4 mi", path.get(0).trim());
  }

  @Test
  public void testGetPathB() {
    ArrayList<String> path = graph.getPath(town[2], town[6]);
    assertNotNull(path);
    assertTrue(path.size() > 0);
    assertEquals("Town_2 via Road_3 to Town_4 6 mi", path.get(0).trim());
    assertEquals("Town_4 via Road_5 to Town_3 2 mi", path.get(1).trim());
    assertEquals("Town_3 via Road_6 to Town_6 2 mi", path.get(2).trim());

  }


}
