package com.fgonzalesv.assignment6;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test all Graph methods
 * 
 * @author Fernando Gonzales-Vigil
 *
 */
public class Graph_STUDENT_Test {

  private GraphInterface<Town, Road> graph;
  private Town[] town;

  @Before
  public void setUp() throws Exception {
    graph = new Graph();
    town = new Town[9];

    for (int i = 1; i < 9; i++) {
      town[i] = new Town("Town_" + i);
      graph.addVertex(town[i]);
    }

    graph.addEdge(town[1], town[5], 3, "Road_1");
    graph.addEdge(town[1], town[7], 4, "Road_2");
    graph.addEdge(town[2], town[4], 6, "Road_3");
    graph.addEdge(town[2], town[5], 4, "Road_4");
    graph.addEdge(town[3], town[4], 2, "Road_5");
    graph.addEdge(town[3], town[6], 2, "Road_6");
    graph.addEdge(town[3], town[8], 3, "Road_7");
    graph.addEdge(town[6], town[8], 5, "Road_8");
  }

  @After
  public void tearDown() throws Exception {
    graph = null;
  }

  @Test
  public void testGetEdge() {
    assertEquals(new Road(town[1], town[7], 4, "Road_2"), graph.getEdge(town[1], town[7]));
    assertEquals(new Road(town[3], town[8], 3, "Road_7"), graph.getEdge(town[3], town[8]));
  }

  @Test
  public void testAddEdge() {
    assertEquals(false, graph.containsEdge(town[1], town[8]));
    graph.addEdge(town[1], town[8], 1, "Road_10");
    assertEquals(true, graph.containsEdge(town[1], town[8]));
  }

  @Test
  public void testAddVertex() {
    Town newTown = new Town("Town_10");
    assertEquals(false, graph.containsVertex(newTown));
    graph.addVertex(newTown);
    assertEquals(true, graph.containsVertex(newTown));
  }

  @Test
  public void testContainsEdge() {
    assertEquals(true, graph.containsEdge(town[1], town[7]));
    assertEquals(false, graph.containsEdge(town[1], town[2]));
  }

  @Test
  public void testContainsVertex() {
    assertEquals(true, graph.containsVertex(new Town("Town_5")));
    assertEquals(false, graph.containsVertex(new Town("Town_10")));
  }

  @Test
  public void testEdgeSet() {
    Set<Road> roads = graph.edgeSet();
    ArrayList<String> roadArrayList = new ArrayList<String>();
    for (Road road : roads)
      roadArrayList.add(road.getName());
    Collections.sort(roadArrayList);
    assertEquals("Road_1", roadArrayList.get(0));
    assertEquals("Road_2", roadArrayList.get(1));
    assertEquals("Road_3", roadArrayList.get(2));
    assertEquals("Road_4", roadArrayList.get(3));
    assertEquals("Road_5", roadArrayList.get(4));
    assertEquals("Road_8", roadArrayList.get(7));
  }

  @Test
  public void testEdgesOf() {
    Set<Road> roads = graph.edgesOf(town[2]);
    ArrayList<String> roadArrayList = new ArrayList<String>();
    for (Road road : roads)
      roadArrayList.add(road.getName());
    Collections.sort(roadArrayList);
    assertEquals("Road_3", roadArrayList.get(0));
    assertEquals("Road_4", roadArrayList.get(1));
  }

  @Test
  public void testRemoveEdge() {
    assertEquals(true, graph.containsEdge(town[3], town[8]));
    graph.removeEdge(town[3], town[8], 3, "Road_7");
    assertEquals(false, graph.containsEdge(town[3], town[8]));
  }

  @Test
  public void testRemoveVertex() {
    assertEquals(true, graph.containsVertex(town[1]));
    graph.removeVertex(town[1]);
    assertEquals(false, graph.containsVertex(town[1]));
  }

  @Test
  public void testVertexSet() {
    Set<Town> roads = graph.vertexSet();
    assertEquals(true, roads.contains(town[4]));
    assertEquals(true, roads.contains(town[5]));
    assertEquals(true, roads.contains(town[6]));
  }

  @Test
  public void testTown_1ToTown_8() {
    String beginTown = "Town_1", endTown = "Town_8";
    Town beginIndex = null, endIndex = null;
    Set<Town> towns = graph.vertexSet();
    Iterator<Town> iterator = towns.iterator();
    while (iterator.hasNext()) {
      Town town = iterator.next();
      if (town.getName().equals(beginTown))
        beginIndex = town;
      if (town.getName().equals(endTown))
        endIndex = town;
    }
    if (beginIndex != null && endIndex != null) {

      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
      assertNotNull(path);
      assertTrue(path.size() > 0);
      assertEquals("Town_1 via Road_1 to Town_5 3 mi", path.get(0).trim());
      assertEquals("Town_5 via Road_4 to Town_2 4 mi", path.get(1).trim());
      assertEquals("Town_2 via Road_3 to Town_4 6 mi", path.get(2).trim());
      assertEquals("Town_4 via Road_5 to Town_3 2 mi", path.get(3).trim());
      assertEquals("Town_3 via Road_7 to Town_8 3 mi", path.get(4).trim());
    } else
      fail("Town names are not valid");

  }

  @Test
  public void testTown1ToTown_7() {
    String beginTown = "Town_1", endTown = "Town_7";
    Town beginIndex = null, endIndex = null;
    Set<Town> towns = graph.vertexSet();
    Iterator<Town> iterator = towns.iterator();
    while (iterator.hasNext()) {
      Town town = iterator.next();
      if (town.getName().equals(beginTown))
        beginIndex = town;
      if (town.getName().equals(endTown))
        endIndex = town;
    }
    if (beginIndex != null && endIndex != null) {

      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
      assertNotNull(path);
      assertTrue(path.size() > 0);
      assertEquals("Town_1 via Road_2 to Town_7 4 mi", path.get(0).trim());
    } else
      fail("Town names are not valid");

  }

  @Test
  public void testTown_4ToTown_7() {
    String beginTown = "Town_4", endTown = "Town_7";
    Town beginIndex = null, endIndex = null;
    Set<Town> towns = graph.vertexSet();
    Iterator<Town> iterator = towns.iterator();
    while (iterator.hasNext()) {
      Town town = iterator.next();
      if (town.getName().equals(beginTown))
        beginIndex = town;
      if (town.getName().equals(endTown))
        endIndex = town;
    }
    if (beginIndex != null && endIndex != null) {

      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
      assertNotNull(path);
      assertTrue(path.size() > 0);
      assertEquals("Town_4 via Road_3 to Town_2 6 mi", path.get(0).trim());
      assertEquals("Town_2 via Road_4 to Town_5 4 mi", path.get(1).trim());
      assertEquals("Town_5 via Road_1 to Town_1 3 mi", path.get(2).trim());
      assertEquals("Town_1 via Road_2 to Town_7 4 mi", path.get(3).trim());
    } else
      fail("Town names are not valid");

  }

}
