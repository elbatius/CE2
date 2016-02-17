import java.util.Scanner;
import java.util.Vector;

public class TextBuddy {
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	private static final String MESSAGE_EMPTY_FILE = "%s is empty";
	private static final String MESSAGE_CONTENT_CLEARED = "all content deleted from %s";
	private static final String MESSAGE_ENTER_COMMAND = "command:";
	
	private static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		// String nameOfFile = args[0];
		String fileName = "mytextfile.txt";
		FileEditor file = initializeFile(fileName);
		Vector<String> fileData = file.getFileData();
		displayWelcomeMessage(file.getFileName());
		enterCommand(fileData);
	}

	public static FileEditor initializeFile(String fileName) {
		return new FileEditor(fileName);
	}
	
	public static void enterCommand(Vector<String> fileData) {
		do {
			showToUser(MESSAGE_ENTER_COMMAND);
			String direction = userInput.next();
			executeCommand(direction, fileData);
		} while (true);
	}
	
	public static void executeCommand(String direction, Vector<String> fileData){
		
	}
	
	public static void displayArray(Vector<String> fileData, FileEditor file) {
		if (fileData.isEmpty()) {
			showToUser(String.format(MESSAGE_EMPTY_FILE, file.getFileName()));
		} else {
			for (String str : fileData) {
				showToUser(str);
			}
		}
	}
	
	public static void emptyArray(Vector<String> fileData, FileEditor file){
		fileData.removeAllElements();
		showToUser(String.format(MESSAGE_CONTENT_CLEARED, file.getFileName()));
	}
	
	public static void displayWelcomeMessage(String fileName){
		showToUser(String.format(MESSAGE_WELCOME, fileName));
	}
	
	public static void showToUser(String message) {
		System.out.println(message);
	}

}
