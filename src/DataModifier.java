import java.util.Collections;
import java.util.Vector;

/**
 * This class accesses and modifies the data stored in Vector<String> types.
 * Allows data to be added, deleted, displayed, searched, sorted.
 * 
 * @author Cheng Tze Jin
 */
public class DataModifier {
	private static final String MESSAGE_NO_SUCH_LINE = "line number %d does not exist";
	private static final String MESSAGE_DISPLAY_LINE = "%d. %s";
	
	/**
	 * This search method searches such that searching for the word "wall" 
	 * will result in "wallet" being a result as well.  
	 */
	public static Vector<String> searchForPhrase(Vector<String> fileData, String phrase) {
		Vector<String> matchingText = new Vector<String>();

		for (String fileText : fileData) {
			if (fileText.contains(phrase)) {
				matchingText.add(fileText);
			}
		}
		return matchingText;
	}

	//sorts the vector by alphabetical order, ignoring case. 
	public static Vector<String> sortArrayAlphabet(Vector<String> fileData) {
		Collections.sort(fileData, String.CASE_INSENSITIVE_ORDER);
		return fileData;
	}

	public static Vector<String> clearArray(Vector<String> fileData) {
		fileData.removeAllElements();
		return fileData;
	}

	public static Vector<String> addToArray(Vector<String> fileData, String phrase) {
		fileData.addElement(phrase);
		return fileData;
	}

	public static Vector<String> deleteFromArray(Vector<String> fileData, int lineNum) {
		fileData.remove(lineNum);
		return fileData;
	}
	
	//-1 represents a non-integer input by the user.
	public static boolean isExistingLine(Vector<String> fileData, int lineNum) {
		if (lineNum == -1) {
			return false;
		} else if (lineNum < fileData.size()) {
			return true;
		} else {
			showToUser(String.format(MESSAGE_NO_SUCH_LINE, (lineNum + 1)));
			return false;
		}
	}

	public static String getPhraseFromArray(Vector<String> fileData, int lineNum) {
		String deletedPhrase;
		deletedPhrase = fileData.get(lineNum);
		return deletedPhrase;
	}

	public static void showToUser(String message) {
		System.out.println(message);
	}

	public static void showFileContent(Vector<String> fileData) {
		for (int i = 0; i < fileData.size(); i++) {
			String str = fileData.get(i);
			showToUser(String.format(MESSAGE_DISPLAY_LINE, (i + 1), str));
		}
	}
}
