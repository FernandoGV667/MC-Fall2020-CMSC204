package com.fgonzalesv.assignment4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManagerTest_STUDENT {
  private CourseDBManagerInterface manager = new CourseDBManager();

  @Before
  public void setUp() throws Exception {
    manager = new CourseDBManager();
  }

  @After
  public void tearDown() throws Exception {
    manager = null;
  }

  /**
   * AddToDB method should not throw Exception and should not add duplicated elements
   */
  @Test
  public void testAddToDB() {
    try {
      manager.add("CMSC207", 30509, 4, "SC453", "Edward E. Nigma");
      assertEquals(1, manager.showAll().size());
      manager.add("CMSC207", 30509, 4, "SC453", "Edward E. Nigma");
      assertEquals(1, manager.showAll().size());
    } catch (Exception e) {
      fail("This should not have caused an Exception");
    }
  }

  /**
   * 
   */
  @Test
  public void testRead() {
    try {
      File inputFile = new File("TestStudent.txt");
      PrintWriter inFile = new PrintWriter(inputFile);
      inFile.println("CMSC204 31504 4 SC445 Joey Balloney");
      inFile.print("CMSC204 30503 4 SC450 Matt Damon");
      inFile.close();

      assertEquals(0, manager.showAll().size());
      manager.readFile(inputFile);
      assertEquals(2, manager.showAll().size());
    } catch (Exception e) {
      fail("Should not have thrown an exception");
    }
  }

  @Test
  public void testShowAll() {
    manager.add("CMSC204", 31504, 4, "SC445", "Joey Balloney");
    manager.add("CMSC204", 30503, 4, "SC450", "Matt Damon");
    manager.add("CMSC207", 30559, 4, "SC453", "Edward E. Nigma");
    ArrayList<String> list = manager.showAll();

    assertEquals(list.get(0),
        "\nCourse:CMSC204 CRN:30503 Credits:4 Instructor:Matt Damon Room:SC450");
    assertEquals(list.get(1),
        "\nCourse:CMSC207 CRN:30559 Credits:4 Instructor:Edward E. Nigma Room:SC453");
    assertEquals(list.get(2),
        "\nCourse:CMSC204 CRN:31504 Credits:4 Instructor:Joey Balloney Room:SC445");
  }

  /**
   * Testing that get method retrieves same CDE´s that the contained in the data structure
   */
  @Test
  public void testGet() {
    manager.add("CMSC204", 31504, 4, "SC445", "Joey Balloney");
    manager.add("CMSC207", 30559, 4, "SC453", "Edward E. Nigma");
    ArrayList<String> list = manager.showAll();

    assertEquals(list.get(0), manager.get(30559).toString());
    assertEquals(list.get(1), manager.get(31504).toString());
  }



}
