package com.fgonzalesv.assignment5;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MorseCodeConverterTest {
	File inputFile;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrintTree()
	{
		//Note the extra space between j and b - that is because there is an empty string that
		//is the root, and in the LNR traversal, the root would come between the right most
		//child of the left tree (j) and the left most child of the right tree (b).
		String correctResult = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
		String s = MorseCodeConverter.printTree();
		s = s.trim(); // take off preceding or succeeding spaces
		assertEquals(correctResult, s);
	}
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish("--. --- --- -.. -... -.-- . / .-- --- .-. .-.. -.. ");
		assertEquals("goodbye world",converter1);
		
		String test2="daisy daisy";		
		String converter2 = MorseCodeConverter.convertToEnglish("-.. .- .. ... -.-- / -.. .- .. ... -.-- ");
		assertEquals("daisy daisy", converter2);
	}

     @Test 
     public void testConvertToEnglishFile() throws FileNotFoundException { String
       test1="give me your answer do"; getFile("Daisy.txt"); String converter1 =
       MorseCodeConverter.convertToEnglish(inputFile); assertEquals(test1,converter1);
       
       String test2="im half crazy all for the love of you"; getFile("DaisyDaisy.txt"); String
       converter2 = MorseCodeConverter.convertToEnglish(inputFile); assertEquals(test2,converter2);
     }
	
	@Test
    public void testConvertToEnglishString_STUDENT() {  
        String converter1 = MorseCodeConverter.convertToEnglish(".. / -- ..- ... - / -. --- - / ..-. . .- .-. / ..-. . .- .-. / .. ... / - .... . / -- .. -. -.. / -.- .. .-.. .-.. . .-.");
        assertEquals("i must not fear fear is the mind killer",converter1);
           
        String converter2 = MorseCodeConverter.convertToEnglish(".... --- .--. . / -.-. .-.. --- ..- -.. ... / --- -... ... . .-. ...- .- - .. --- -.");
        assertEquals("hope clouds observation", converter2);
    }
	
	@Test
    public void testConvertToEnglishFile_STUDENT() throws FileNotFoundException {
	    
        String test1="i must not fear fear is the mind killer";  
        File file1 = new File("/Users/Fernando/Downloads/CMSC 204 Fall 2020/Assignment 5/letany_STUDENT");
        // getFile("letany_STUDENT.txt");
        String converter1 = MorseCodeConverter.convertToEnglish(file1);
        assertEquals(test1,converter1);
        
        String test2="hope clouds observation"; 
        File file2 = new File("/Users/Fernando/Downloads/CMSC 204 Fall 2020/Assignment 5/herbert_STUDENT");
        // getFile("herbert_STUDENT.txt");
        String converter2 = MorseCodeConverter.convertToEnglish(file2);
        assertEquals(test2,converter2);

    }
	
	public void getFile(String in) throws FileNotFoundException {		
		JFileChooser chooser = new JFileChooser();
		int status;

		chooser.setDialogTitle("Select Input File - " + in);
		status = chooser.showOpenDialog(null);

		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				inputFile = chooser.getSelectedFile();
				// readFile();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
