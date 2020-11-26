package com.fgonzalesv.assignment6;

/**
 * Represents the edges of a Graph of Towns. The class stores references to the two vertices(Town
 * endpoints), the distance between vertices, and a name. Since this is a undirected graph, an edge
 * from A to B is equal to an edge from B to A.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class Road implements Comparable<Road> {

  private Town source, destination;
  private int distance;
  private String name;

  /**
   * Constructor
   * 
   * @param source - One town on the road
   * @param destination - Another town on the road
   * @param distance - distance from one town to the other
   * @param name - name of the road
   */

  Road(Town source, Town destination, int distance, String name) {
    this.source = source;
    this.destination = destination;
    this.distance = distance;
    this.name = name;
  }

  /**
   * Constructor with weight preset at 1
   * 
   * @param source - One town on the road
   * @param destination - Another town on the road
   * @param name - name of the road
   */
  Road(Town source, Town destination, String name) {
    this.source = source;
    this.destination = destination;
    this.distance = 1;
    this.name = name;
  }

  /**
   * compare two road objects
   * 
   * @param r - road to compare with
   * @return 0 if the road names are the same, a positive or negative number if the road names are
   *         not the same
   */
  @Override
  public int compareTo(Road r) {
    return this.name.compareTo(r.name);
  }

  /**
   * Returns true only if the edge contains the given town
   * 
   * @param town - a vertex of the graph
   * @return true only if the edge is connected to the given vertex
   */
  public boolean contains(Town town) {
    return source.getName().equals(town.getName()) || destination.getName().equals(town.getName());
  }

  /**
   * Returns true if each of the ends of the road r is the same as the ends of this road. Remember
   * that a road that goes from point A to point B is the same as a road that goes from point B to
   * point A.
   * 
   * @param obj - road object to compare it to
   * @return true if the roads are equal, false if not
   */
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Road)) {
      return false;
    }
    Road r = (Road) obj;
    return (this.source.equals(r.source) && this.destination.equals(r.destination))
        || (this.source.equals(r.destination) && this.destination.equals(r.source));
  }

  /**
   * To string method. Output: "Town_2 via Road_12 to Town_11 6 mi"
   * 
   * @return all varibles
   */
  @Override
  public String toString() {
    return source.getName() + " via " + name + " to " + destination.getName() + " " + distance
        + " mi";
  }

  /**
   * Returns the first town on the road
   * 
   * @return a town on the road
   */
  public Town getSource() {
    return source;
  }

  /**
   * Returns the second town on the road
   * 
   * @return a town on the road
   */
  public Town getDestination() {
    return destination;
  }

  /**
   * Returns the distance of the road
   * 
   * @return the distance of the road
   */
  public int getDistance() {
    return distance;
  }

  /**
   * Returns the road name
   * 
   * @return the name of the road
   */
  public String getName() {
    return name;
  }

}

