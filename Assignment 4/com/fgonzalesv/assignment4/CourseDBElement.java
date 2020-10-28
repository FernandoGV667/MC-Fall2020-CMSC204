package com.fgonzalesv.assignment4;

/**
 * CourseDBElement implements Comparable, and consists of five attributes: the Course ID (a String),
 * the CRN (an int), the number of credits (an int), the room number (a String), and the instructor
 * name (a String). Normally the CourseDBElement will be an object consisting of these five
 * attributes, and is referred to as a CDE.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class CourseDBElement implements Comparable {

  private String courseId;
  private int CRN;
  private int credits;
  private String room;
  private String instructor;

  public CourseDBElement(String courseId, int crn, int credits, String room, String instructor) {
    this.courseId = courseId;
    this.CRN = crn;
    this.credits = credits;
    this.room = room;
    this.instructor = instructor;
  }

  public CourseDBElement() {
    this.courseId = "";
    this.CRN = 0;
    this.credits = 0;
    this.room = "";
    this.instructor = "";
  }

  /**
   * @return id of the course
   */
  public String getCourseId() {
    return courseId;
  }

  /**
   * @param courseId - id of the course
   */
  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  /**
   * @return unique number that represents the course
   */
  public int getCRN() {
    return CRN;
  }

  /**
   * @param crn - unique number that represents the course
   */
  public void setCRN(int crn) {
    CRN = crn;
  }

  /**
   * @return number of credits of the course
   */
  public int getCredits() {
    return credits;
  }

  /**
   * @param credits - number of credits of the course
   */
  public void setCredits(int credits) {
    this.credits = credits;
  }

  /**
   * @return room number
   */
  public String getRoom() {
    return room;
  }

  /**
   * @param room - room number as string
   */
  public void setRoom(String room) {
    this.room = room;
  }

  /**
   * @return name of the Instructor
   */
  public String getInstructor() {
    return instructor;
  }

  /**
   * @param instructor - name of the instructor
   */
  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  /**
   * @return the hashcode computation of the CDE based on the string value of the CRN
   */
  @Override
  public int hashCode() {
    String s = String.valueOf(getCRN());
    long hash = 0;
    int prime = 31;
    for (int i = 0; i < s.length(); i++) {
      hash = prime * hash + s.charAt(i);
    }
    return (int) hash;
  }

  /**
   * @return the String representation in this format: \nCourse:CMSC203 CRN:30503 Credits:4
   *         Instructor:Jill B. Who-Dunit Room:SC450
   */
  @Override
  public String toString() {
    return "\nCourse:" + courseId + " CRN:" + CRN + " Credits:" + credits + " Instructor:"
        + instructor + " Room:" + room;
  }

  /**
   * The comparison will be based in the CRN
   * 
   * @param element - element to be compared with
   */
  @Override
  public int compareTo(CourseDBElement element) {
    return Integer.compare(this.getCRN(), element.getCRN());
  }
  
  /**
   * The comparison for equality will be based in the CRN
   * 
   * @param obj - object to be compared with
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null){
        return false;
    }

    if (obj == this) {
        return true;
    } 

    if (!(obj instanceof CourseDBElement)) {
        return false;
    }
    CourseDBElement cde = (CourseDBElement) obj;
    return this.getCRN() == cde.getCRN();
}

}
