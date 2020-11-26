package com.fgonzalesv.assignment6;

import java.util.ArrayList;

/**
 * Represents a town as a node/vertex of a graph.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class Town implements Comparable<Town>{

    private String name;
    private ArrayList<Town> adjacentTowns;
    
    /**
     * constructor
     * @param name - name of the town
     */
    Town(String name){
        this.name = name;
    }
    
    /**
     * Copy constructor
     * @param templateTown - town to copy
     */
    Town(Town templateTown){
        this.name=templateTown.name;
    }
    
    /**
     * Compare two towns by name
     * @param t - town to compare with
     * @return 0 if names are equal, a positive or negative number if the names are not equal
     */
    @Override
    public int compareTo(Town t) {      
        return this.name.compareTo(t.name);
    }

    /**
     * The comparison for equality will be based in the name
     * @param obj - object to be compared with
     * @return true if the town names are equal, false if not
     */
     @Override
      public boolean equals(Object obj) {
        if (obj == null) {
          return false;
        }
        if (obj == this) {
          return true;
        }
        if (!(obj instanceof Town)) {
          return false;
        }
        Town t = (Town) obj;
        return this.name.equals(t.name);
      }
    
    /**
     * @return name of the town
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the hashcode for the name of the town
     */
    public int hashCode() {
        return name.hashCode();
    }
    
    /**
     * To string method
     * @return the town name
     */
    public String toString() {
        return name;
    }
}

