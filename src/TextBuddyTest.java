import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

public class TextBuddyTest {
	/**
	 * Test retrieving of data from text file function
	 */
	@Test
	public void testRetrieveFileData() {
		//Tests normal circumstances, some text in the file.
		FileEditor file = new FileEditor("TESTmytextfile.txt");
		Vector<String> fileTester = file.getFileData();
		Vector<String> tester = new Vector<String>();
		tester.add("the fox");
		tester.add("coloured himself green");
		tester.add("while jumping over the lazy moon");
		tester.add("and swimming joyously");
		tester.add("across the Pacific Ocean.");
		Object[] fileTesterArray = fileTester.toArray();
		Object[] testerArray = tester.toArray();
		assertArrayEquals(testerArray, fileTesterArray);
		
		//Tests on empty textfile
		file = new FileEditor("EMPTYTESTFILE.txt");
		fileTester = file.getFileData(); 
		tester = new Vector<String>();
		fileTesterArray = fileTester.toArray();
		testerArray = tester.toArray();
		assertArrayEquals(testerArray, fileTesterArray);
		
		//Tests a textfile with 400 lines. 
		file = new FileEditor("TESTMANYLINESmytextfil.txt");
		fileTester = file.getFileData();
		fileTesterArray = fileTester.toArray();
		for (int i = 0; i < 400; i++){
			tester.add(Integer.toString(i+1));
		}
		testerArray = tester.toArray();
		assertArrayEquals(testerArray, fileTesterArray);
	}
	
	/**
	 * Tests if an the line number given for deletion exists or not.
	 * 5 different circumstances, 1st for normal, 2nd for number = 0, 
	 * 3rd when number is slightly lesser than 0, 4th when number exceeds 
	 * number of lines in file, 5th when number is far below 0.
	 */
	@Test
	public void testIsExistingLine(){
		FileEditor file = new FileEditor("TESTmytextfile.txt");
		Vector<String> fileTester = file.getFileData();
		assertTrue(DataModifier.isExistingLine(fileTester, 3));
		assertTrue(DataModifier.isExistingLine(fileTester, 0));
		assertFalse(DataModifier.isExistingLine(fileTester, -1));
		assertFalse(DataModifier.isExistingLine(fileTester, 100));
		assertFalse(DataModifier.isExistingLine(fileTester, -100));
	}
	
	/**
	 * Tests searching of phrase in different circumstances. 
	 */
	@Test
	public void testSearchForPhrase(){
		Vector<String> tester = new Vector<String>();
		tester.add("the fox");
		tester.add("coloured himself green");
		tester.add("while jumping over the lazy moon");
		tester.add("and swimming joyously");
		tester.add("across the Pacific Ocean.");
		
		//Normal circumstances, some found, some not.
		String phrase = "the";
		Vector<String> expected = new Vector<String>();
		expected.add("the fox");
		expected.add("while jumping over the lazy moon");
		expected.add("across the Pacific Ocean.");
		Vector<String> actual = new Vector<String>();
		actual = DataModifier.searchForPhrase(tester, phrase);
		Object[] expectedArray = expected.toArray();
		Object[] actualArray = actual.toArray();
		assertArrayEquals(expectedArray, actualArray);
		
		//Searching with whitespaces in between.
		phrase = "over the";
		expected.clear();
		expected.add("while jumping over the lazy moon");
		actual = DataModifier.searchForPhrase(tester, phrase);
		expectedArray = expected.toArray();
		actualArray = actual.toArray();
		assertArrayEquals(expectedArray, actualArray);
		
		//No results found
		phrase = "notintext";
		expected.clear();
		actual = DataModifier.searchForPhrase(tester, phrase);
		expectedArray = expected.toArray();
		actualArray = actual.toArray();
		assertArrayEquals(expectedArray, actualArray);
		
		//all lines found.
		phrase = " ";
		expected.add("the fox");
		expected.add("coloured himself green");
		expected.add("while jumping over the lazy moon");
		expected.add("and swimming joyously");
		expected.add("across the Pacific Ocean.");
		actual = DataModifier.searchForPhrase(tester, phrase);
		expectedArray = expected.toArray();
		actualArray = actual.toArray();
		assertArrayEquals(expectedArray, actualArray);
	}

}
