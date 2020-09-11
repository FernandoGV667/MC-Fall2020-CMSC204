package com.fgonzalesv.assignment1;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * 
 * @author
 *
 */
public class PasswordCheckerTest_STUDENT {
  ArrayList<String> passwords;
  String password1, password2;

  @Before
  public void setUp() throws Exception {
    String[] p = {"458899Yu%", "MyToolisGR8", "m0rn?ng4MEE", "be42night", "Morpheu$665",
        "ElCincodeMayo", "februarY29?", "ZYXWVUL", "Caesa&12", "u999v", "camelCharacter1%",
        "AAAbcd56@", "myFirstName2!"};
    passwords = new ArrayList<String>();
    passwords.addAll(Arrays.asList(p)); // puts strings into the ArrayList
  }

  @After
  public void tearDown() throws Exception {
    passwords = null;
  }

  /**
   * Test if the password is less than 8 characters long. This test should throw a LengthException
   * for second case.
   */
  @Test
  public void testIsValidPasswordTooShort() {
    try {
      assertTrue(PasswordCheckerUtility.isValidPassword("MerN1$4"));
      PasswordCheckerUtility.isValidPassword("m4r$");
      assertTrue("Did not throw lengthException", false);
    } catch (LengthException e) {
      assertTrue("Successfully threw a lengthExcepetion", true);
    } catch (Exception e) {
      assertTrue("Threw some other exception besides lengthException", false);
    }
  }

  /**
   * Test if the password has at least one uppercase alpha character This test should throw a
   * NoUpperAlphaException for second case
   */
  @Test
  public void testIsValidPasswordNoUpperAlpha() {
    try {
      assertTrue(PasswordCheckerUtility.isValidPassword("Morning4U$"));
      PasswordCheckerUtility.isValidPassword("43#886$");
      assertTrue("Did not throw NoUpperAlphaException", false);
    } catch (NoUpperAlphaException e) {
      assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
    } catch (Exception e) {
      assertTrue("Threw some other exception besides NoUpperAlphaException", false);
    }
  }

  /**
   * Test if the password has at least one lowercase alpha character This test should throw a
   * NoLowerAlphaException for second case
   */
  @Test
  public void testIsValidPasswordNoLowerAlpha() {
    try {
      assertTrue(PasswordCheckerUtility.isValidPassword("Morning4U$"));
      PasswordCheckerUtility.isValidPassword("MORNINGUS");
      assertTrue("Did not throw NoLowerAlphaException", false);
    } catch (NoLowerAlphaException e) {
      assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
    } catch (Exception e) {
      assertTrue("Threw some other exception besides NoLowerAlphaException", false);
    }
  }

  /**
   * Test if the password has more than 2 of the same character in sequence This test should throw a
   * InvalidSequenceException for second case
   */
  @Test
  public void testIsWeakPassword() {
    try {
      assertEquals(true, PasswordCheckerUtility.isValidPassword("Morning4U$"));
      boolean weakPwd = PasswordCheckerUtility.isWeakPassword("Morn4U$");
      assertTrue(weakPwd);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue("Threw some incorrect exception", false);
    }
  }

  /**
   * Test if the password has more than 2 of the same character in sequence This test should throw a
   * InvalidSequenceException for second case
   */
  @Test
  public void testIsValidPasswordInvalidSequence() {
    try {
      assertEquals(true, PasswordCheckerUtility.isValidPassword("Morning4U$"));
      PasswordCheckerUtility.isValidPassword("Mooorning4U$");
      assertTrue("Did not throw an InvalidSequenceException", false);
    } catch (InvalidSequenceException e) {
      assertTrue("Successfully threw an InvalidSequenceExcepetion", true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue("Threw some other exception besides an InvalidSequenceException", false);
    }
  }

  /**
   * Test if the password has at least one digit One test should throw a NoDigitException
   */
  @Test
  public void testIsValidPasswordNoDigit() {
    try {
      assertEquals(true, PasswordCheckerUtility.isValidPassword("Morning4U$"));
      PasswordCheckerUtility.isValidPassword("MooorningU");
      assertTrue("Did not throw a NoDigitException", false);
    } catch (NoDigitException e) {
      assertTrue("Successfully threw a NoDigitException", true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue("Threw some other exception besides a NoDigitException", false);
    }
  }

  /**
   * Test if the password has at least one special character One test should throw a
   * NoSpecialCharacterException
   */
  @Test
  public void testIsValidPasswordNoSpecialCharacter() {
    try {
      assertEquals(true, PasswordCheckerUtility.isValidPassword("Morning4U$"));
      PasswordCheckerUtility.isValidPassword("Mooorning4U");
      assertTrue("Did not throw a NoSpecialCharacterException", false);
    } catch (NoSpecialCharacterException e) {
      assertTrue("Successfully threw a NoSpecialCharacterException", true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue("Threw some other exception besides a NoSpecialCharacterException", false);
    }
  }

  /**
   * Test correct passwords This test should not throw an exception
   */
  @Test
  public void testIsValidPasswordSuccessful() {
    try {
      assertEquals(true, PasswordCheckerUtility.isValidPassword("ZZ7mnopqq?"));
      assertEquals(true, PasswordCheckerUtility.isValidPassword("$ubLiM3Z"));
      assertEquals(true, PasswordCheckerUtility.isValidPassword("M0oNc#ilD"));
      assertEquals(true, PasswordCheckerUtility.isValidPassword("(Brew3rs)"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue("Threw some incorrect exception", false);
    }
  }

  /**
   * Test the invalidPasswords method Check the results of the ArrayList of Strings returned by the
   * validPasswords method
   */
  @Test
  public void testInvalidPasswords() {
    ArrayList<String> results;
    results = PasswordCheckerUtility.getInvalidPasswords(passwords);

    Scanner scan = new Scanner(results.get(0)); //
    assertEquals(scan.next(), "MyToolisGR8");
    String nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("special"));

    scan = new Scanner(results.get(1)); //
    assertEquals(scan.next(), "be42night");
    nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("uppercase"));

    scan = new Scanner(results.get(2)); //
    assertEquals(scan.next(), "ElCincodeMayo");
    nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("digit"));

    scan = new Scanner(results.get(3)); //
    assertEquals(scan.next(), "ZYXWVUL");
    nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("lowercase"));

    scan = new Scanner(results.get(4)); // a
    assertEquals(scan.next(), "u999v");
    nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("long"));

    scan = new Scanner(results.get(5)); // a
    assertEquals(scan.next(), "AAAbcd56@");
    nextResults = scan.nextLine().toLowerCase();
    assertTrue(nextResults.contains("same"));
  }

}
