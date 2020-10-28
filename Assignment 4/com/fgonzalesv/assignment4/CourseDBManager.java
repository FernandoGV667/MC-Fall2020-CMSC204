package com.fgonzalesv.assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The data manager allows the user to read the courses from a text file or to enter the data in the
 * GUI, and print out the database elements. The input is read from a file or read from the
 * textfields.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class CourseDBManager implements CourseDBManagerInterface {

  private CourseDBStructure cds;

  public CourseDBManager() {
    cds = new CourseDBStructure(10);
  }

  /**
   * The add method uses the CDS add method to create a CDE element and add it to the data structure
   * 
   * @param id - the id of the course
   * @param crn - the CRN number of the course
   * @param credits - the number of credits of the course
   * @param roomNum - the room number
   * @param instructor - the name of the instructor
   */
  @Override
  public void add(String id, int crn, int credits, String roomNum, String instructor) {
    CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
    cds.add(element);
  }

  /**
   * The readFile method reads every line from a text file, converts it to a Course element and adds
   * it to the data structure. The format of the data is as follows: CMSC203 30503 4 SC450 Jill B.
   * Who-Dunit
   * 
   * @param input - the file that contains the data to read
   * @throws FileNotFoundException if file is not found
   */
  @Override
  public void readFile(File input) throws FileNotFoundException {
    InputStream in = new FileInputStream(input);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));

    List<String[]> list = br.lines().map(s -> s.split(" ")).collect(Collectors.toList());
    for (String[] ar : list) {
      if (ar.length > 5) {
        StringBuilder instructor = new StringBuilder();
        for (int i = 4; i < ar.length; i++) {
          instructor.append(ar[i] + " ");
        }
        cds.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
            instructor.toString().trim()));
      } else {
        cds.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
            ar[4]));
      }
    }
  }

  /**
   * The showAll method traverses the data structure for all elements and returns them all inside an
   * Arraylist.
   * 
   * @return an arraylist with all the elements in the data structure
   */
  @Override
  public ArrayList<String> showAll() {
    ArrayList<CourseDBElement> tmp = new ArrayList<>();
    ArrayList<String> list;
    for (int i = 0; i < cds.getTableSize(); i++) {
      if (cds.hashTable[i] != null) {
        tmp.addAll(cds.hashTable[i]);
      }
    }
    list = (ArrayList<String>) tmp.stream().map(s -> s.toString()).collect(Collectors.toList());
    return list;
  }

  /**
   * The get method uses the CDS get method to retrieve a CDE element from the data structure and
   * handle the exception if not found.
   * 
   * @param crn - the CRN number of the CDE to retrieve
   * @return the CourseDBElement if found or null if not
   */
  @Override
  public CourseDBElement get(int crn) {
    try {
      return cds.get(crn);
    } catch (IOException e) {
      System.out.println("Exception was thrown in Manager get CRN");
      e.getMessage();
    }
    return null;
  }

}
