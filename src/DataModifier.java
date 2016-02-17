import java.util.Collections;
import java.util.Vector;

public class DataModifier {
	private static final String MESSAGE_NO_SUCH_LINE = "line number %d does not exist";
	private static final String MESSAGE_DISPLAY_LINE = "%d. %s";
	
	public static Vector<String> sortArrayAlphabet(Vector<String> fileData){
		Collections.sort(fileData);
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
		if (isExistingLine(fileData, lineNum)) {
			fileData.remove(lineNum);
		}
		else{
			showToUser(String.format(MESSAGE_NO_SUCH_LINE, lineNum));
		}
		return fileData;
	}

	public static boolean isExistingLine(Vector<String> fileData, int lineNum) {
		if (lineNum < fileData.size()) {
			return true;
		} else
			return false;
	}

	public static String getPhraseFromArray(Vector<String> fileData, int lineNum) {
		String deletedPhrase;
		deletedPhrase = fileData.get(lineNum);
		return deletedPhrase;
	}
	
	public static void showToUser(String message){
		System.out.println(message);
	}
	
	public static void showFileContent(Vector<String> fileData) {
		for (int i = 0; i < fileData.size(); i++) {
			String str = fileData.get(i);
			showToUser(String.format(MESSAGE_DISPLAY_LINE, (i + 1), str));
		}
	}
}
