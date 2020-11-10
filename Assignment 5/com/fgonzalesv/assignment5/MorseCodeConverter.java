package com.fgonzalesv.assignment5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The MorseCodeConverter contains a static MorseCodeTree object and constructs (calls the
 * constructor for) the MorseCodeTree. This class has two static methods convertToEnglish to convert
 * from morse code to English. One method is passed a string object (“.-.. --- ...- . / .-.. --- ---
 * -.- ...”). The other method is passed a file to be converted.
 * 
 * @author Fernando
 */
public class MorseCodeConverter {

  private static MorseCodeTree morse = new MorseCodeTree();

  /**
   * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is
   * delimited by a ‘/’. Example: code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.." string
   * returned = "Hello World"
   * 
   * @param code - the morse code
   * @return the English translation
   */
  public static String convertToEnglish(String code) {
    String[] letter;
    String[] word = code.split(" / ");
    StringBuilder english = new StringBuilder();

    for (String ch : word) {
      letter = ch.split(" ");
      for (String tmp : letter) {
        english.append(morse.fetch(tmp));
      }
      english.append(" ");
    }
    return english.toString().trim();
  }

  /**
   * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word
   * is delimited by a ‘/’. 
   * Example: a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-..
   * -.." 
   * string returned = "Hello World"
   * 
   * @param codeFile - Name of the File that contains Morse Code
   * @return the English translation of the file
   * @throws FileNotFoundException the file was not found
   */
  public static String convertToEnglish(File codeFile) throws FileNotFoundException {
    StringBuilder sb = new StringBuilder();
    InputStream in = new FileInputStream(codeFile);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));

    br.lines().forEach(s -> sb.append(convertToEnglish(s)).append("\n"));
    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString().trim();
  }

  /**
   * returns a string with all the data in the tree in LNR order with an space in between them. Uses
   * the toArrayList method in MorseCodeTree It should return the data in this order: "h s v i f u e
   * l r a p w j b d x n c k y t z g q m o" Note the extra space between j and b - that is because
   * there is an empty string that is the root, and in the LNR traversal, the root would come
   * between the right most child of the left tree (j) and the left most child of the right tree
   * (b). This is used for testing purposes to make sure the MorseCodeTree has been built properly
   * 
   * @return the data in the tree in LNR order separated by a space.
   */
  public static String printTree() {
    ArrayList<String> morseTree = morse.toArrayList();
    StringBuilder sb = new StringBuilder();

    for (String letters : morseTree) {
      sb.append(letters).append(" ");
    }
    return sb.toString().trim();
  }

}
