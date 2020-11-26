package com.fgonzalesv.assignment6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Graph class that implements the GraphInterface given you. For Graph<V,E>, V is the vertex type
 * (a Town), E is the edge type (a Road). The graph is stored using an adjacent matrix or an
 * adjacency list.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class Graph implements GraphInterface<Town, Road> {

  //final int NoEdge = Integer.MAX_VALUE;
  //final int NoPath0 = Integer.MAX_VALUE;

  private ArrayList<String> shortestPath = new ArrayList<>();
  private Set<Town> towns = new HashSet<>();
  private Set<Road> roads = new HashSet<>();
  private Town destination;

  /**
   * Returns a road connecting source town to target town if such towns and such road exist in this
   * graph.
   *
   * @param sourceVertex - source vertex of the edge.
   * @param destinationVertex - target vertex of the edge.
   *
   * @return an edge connecting source vertex to target vertex.
   */
  @Override
  public Road getEdge(Town sourceVertex, Town destinationVertex) {
    if (sourceVertex == null || destinationVertex == null) {
      return null;
    }
    return roads.stream().filter(r -> r.contains(sourceVertex) && r.contains(destinationVertex))
        .findAny().orElse(null);
  }

  /**
   * Creates a new road in this graph, going from the source town to the target town, and returns
   * the created road.
   * 
   * The source and target towns must already be contained in this graph. If they are not found in
   * graph IllegalArgumentException is thrown.
   *
   * @param sourceVertex - source town of the road.
   * @param destinationVertex - target town of the road.
   * @param distance - distance of the road
   * @param description - description for road
   *
   * @return The newly created road if added to the graph, otherwise null.
   *
   * @throws IllegalArgumentException if source or target towns are not found in the graph.
   * @throws NullPointerException if any of the specified towns is null.
   */
  @Override
  public Road addEdge(Town sourceVertex, Town destinationVertex, int distance, String description)
      throws IllegalArgumentException, NullPointerException {
    if (sourceVertex == null || destinationVertex == null) {
      throw new NullPointerException();
    }
    if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
      throw new IllegalArgumentException();
    }
    Road road = new Road(sourceVertex, destinationVertex, distance, description);
    roads.add(road);
    return road;
  }

  /**
   * Adds the specified town to this graph if not already present. More formally, adds the specified
   * town, t, to this graph if this graph contains no town u such that u.equals(v). If this graph
   * already contains such vertex, the call leaves this graph unchanged and returns false. In
   * combination with the restriction on constructors, this ensures that graphs never contain
   * duplicate town.
   *
   * @param t - town to be added to this graph.
   * @return true if this graph did not already contain the specified town.
   * @throws NullPointerException if the specified town is null.
   */
  @Override
  public boolean addVertex(Town t) throws NullPointerException {
    if (t == null) {
      throw new NullPointerException();
    }
    if (!towns.contains(t)) {
      towns.add(t);
      return true;
    }
    return false;
  }

  /**
   * Returns true if and only if this graph contains a road going from the source town to the target
   * town. In undirected graphs the same result is obtained when source and target are inverted. If
   * any of the specified towns does not exist in the graph, or if is null, returns false.
   *
   * @param sourceVertex - source town of the road.
   * @param destinationVertex - target town of the road.
   *
   * @return true if this graph contains the specified road.
   */
  @Override
  public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
    for (Road r : roads) {
      if (r.contains(sourceVertex) && r.contains(destinationVertex)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if this graph contains the specified town. More formally, returns true if and only
   * if this graph contains a town u such that u.equals(v). If the specified town is null returns
   * false.
   *
   * @param t - town whose presence in this graph is to be tested.
   *
   * @return true if this graph contains the specified town.
   */
  @Override
  public boolean containsVertex(Town t) {
    return towns.contains(t);
  }

  /**
   * Returns a set of the roads contained in this graph. The set is backed by the graph, so changes
   * to the graph are reflected in the set. If the graph is modified while an iteration over the set
   * is in progress, the results of the iteration are undefined.
   *
   * @return a set of the roads contained in this graph.
   */
  @Override
  public Set<Road> edgeSet() {
    return roads;
  }

  /**
   * Returns a set of all edges touching the specified vertex (also referred to as adjacent
   * vertices). If no edges are touching the specified vertex returns an empty set.
   *
   * @param town - the town for which a set of touching roads is to be returned.
   * @return a set of all edges touching the specified vertex.
   *
   * @throws IllegalArgumentException if town is not found in the graph.
   * @throws NullPointerException if town is null.
   */
  @Override
  public Set<Road> edgesOf(Town town) {
    if (town == null) {
      throw new NullPointerException();
    }
    Set<Road> roadSet = new HashSet<>();
    for (Road r : roads) {
      if (r.contains(town)) {
        roadSet.add(r);
      }
    }
    if (roadSet.isEmpty()) {
      throw new IllegalArgumentException();
    }
    return roadSet;
  }

  /**
   * Removes a road going from source town to target town, if such towns and such road exist in this
   * graph.
   *
   * @param sourceVertex - source town of the road.
   * @param destinationVertex - target town of the road.
   * @param distance - distance of the road
   * @param description - description of the road
   *
   * @return The removed road, or null if no road removed.
   */
  @Override
  public Road removeEdge(Town sourceVertex, Town destinationVertex, int distance,
      String description) {
    Road road = null;
    for (Road r : roads) {
      if (r.contains(destinationVertex) && r.contains(sourceVertex) && (distance > -1)
          && description != null) {
        road = r;
      }
    }
    if (roads.remove(road)) {
      return road;
    }
    return null;
  }

  /**
   * Removes the specified town from this graph including all its touching roads if present. More
   * formally, if the graph contains a town u such that u.equals(t), the call removes all roads that
   * touch u and then removes u itself. If no such u is found, the call leaves the graph unchanged.
   * Returns true if the graph contained the specified town.
   *
   * @param t - town to be removed from this graph, if present.
   *
   * @return true if the graph contained the specified vertex; false otherwise.
   */
  @Override
  public boolean removeVertex(Town t) {
    if (t == null) {
      return false;
    }
    return towns.remove(t);
  }

  /**
   * Returns a set of the towns contained in this graph. The set is backed by the graph, so changes
   * to the graph are reflected in the set. If the graph is modified while an iteration over the set
   * is in progress, the results of the iteration are undefined.
   *
   * @return a set view of the towns contained in this graph.
   */
  @Override
  public Set<Town> vertexSet() {
    return towns;
  }

  /**
   * Find the shortest path from the sourceVertex to the destinationVertex call the
   * dijkstraShortestPath with the sourceVertex
   * 
   * @param sourceVertex - starting town
   * @param destinationVertex - ending town
   * @return An arraylist of Strings that describe the path from sourceVertex to destinationVertex
   *         Format: Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
   */
  @Override
  public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

    destination = destinationVertex;
    dijkstraShortestPath(sourceVertex);
    ArrayList<Road> roadPath = new ArrayList<>();

    boolean anySource = roads.stream().anyMatch(r -> r.contains(sourceVertex));
    boolean anyDest = roads.stream().anyMatch(r -> r.contains(destinationVertex));

    if (!anySource || !anyDest) {
      return new ArrayList<>();
    }

    for (int i = 0; i < shortestPath.size() - 1; i++) {
      Town source = new Town(shortestPath.get(i));
      Town destination = new Town(shortestPath.get(i + 1));
      Road road = getEdge(source, destination);
      roadPath.add(new Road(source, destination, road.getDistance(), road.getName()));
    }

    shortestPath.clear();
    // towns.clear();
    // roads.clear();
    return roadPath.stream().map(Road::toString).collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Dijkstra's Shortest Path Method using Adjancency Matrix. Internal structures are built which
   * hold the ability to retrieve the path, shortest distance from the sourceVertex to all the other
   * vertices in the graph, etc.
   * 
   * @param sourceVertex - the vertex to find shortest path from
   */
  @Override
  public void dijkstraShortestPath(Town sourceVertex) {
    // shortestPath.clear();
    List<Town> vertices = new ArrayList<>(towns);

    int[][] adjacencyMatrix = new int[towns.size()][towns.size()];

    for (int i = 0; i < adjacencyMatrix.length; i++) {
      for (int j = 0; j < adjacencyMatrix[i].length; j++) {
        if (i == j || !containsEdge(vertices.get(i), vertices.get(j))) {
          adjacencyMatrix[i][j] = 0;
        } else {
          int distance = getEdge(vertices.get(i), vertices.get(j)).getDistance();
          adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = distance;
        }
      }
    }

    int startTown = 0;
    for (Town t : vertices) {
      if (!t.equals(sourceVertex)) {
        startTown++;
      } else {
        break;
      }
    }

    int endTown = 0;

    for (Town t : vertices) {
      if (!t.equals(destination)) {
        endTown++;
      } else {
        break;
      }
    }

    int numTowns = adjacencyMatrix[0].length;
    int[] shortDistances = new int[numTowns];
    boolean[] visited = new boolean[numTowns];

    for (int t = 0; t < numTowns; t++) {
      shortDistances[t] = Integer.MAX_VALUE;
      visited[t] = false;
    }

    shortDistances[startTown] = 0;
    int[] minPathLengths = new int[numTowns];
    minPathLengths[startTown] = -1;

    for (int i = 1; i < numTowns; i++) {
      int nearestTown = -1;
      int minDistance = Integer.MAX_VALUE;
      
      for (int townIndex = 0; townIndex < numTowns; townIndex++) {
        if (!visited[townIndex] && shortDistances[townIndex] < minDistance) {
          nearestTown = townIndex;
          minDistance = shortDistances[townIndex];
        }
      }

      visited[nearestTown] = true;

      for (int townIndex = 0; townIndex < numTowns; townIndex++) {
        int roadDistance = adjacencyMatrix[nearestTown][townIndex];
        if (roadDistance > 0 && ((minDistance + roadDistance) < shortDistances[townIndex])) {
          minPathLengths[townIndex] = nearestTown;
          shortDistances[townIndex] = minDistance + roadDistance;
        }
      }
      
    }
    computeShortPath(endTown, minPathLengths);
  }

  /**
   * Process the shortest paths of towns and add into arraylist
   * 
   * @param sourceVertex - index of town
   * @param minPathLengths -  array with towns short distances
   */
  private void computeShortPath(int sourceVertex, int[] minPathLengths) {

    if (sourceVertex == -1) {
      return;
    }

    computeShortPath(minPathLengths[sourceVertex], minPathLengths);

    int townIndex = 0;

    for (Town t : towns) {
      if (townIndex == sourceVertex) {
        shortestPath.add(t.getName());
      }
      townIndex++;
    }
  }


}
