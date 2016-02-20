import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class TextBuddyTest {
	
	@Test
	public void testRetrieveFileData() {
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
		 
		file = new FileEditor("EMPTYTESTFILE.txt");
		fileTester = file.getFileData(); 
		tester = new Vector<String>();
		fileTesterArray = fileTester.toArray();
		testerArray = tester.toArray();
		assertArrayEquals(testerArray, fileTesterArray);
		
		file = new FileEditor("TESTMANYLINESmytextfil.txt");
		fileTester = file.getFileData();
		fileTesterArray = fileTester.toArray();
		for (int i = 0; i < 400; i++){
			tester.add(Integer.toString(i+1));
		}
		testerArray = tester.toArray();
		assertArrayEquals(testerArray, fileTesterArray);
	}
	

}
