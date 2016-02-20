import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

/**
 * This class is used to take in user commands and display the feedback as per
 * executed commands. Possible commands are: add <phrase> delete
 * <phraseNum> display clear search <phrase> sort exit
 * 
 * Each line of text will be preceded by the line number of the text. Therefore,
 * supposing "jumped over the moon" is the first phrase added in In the text
 * file it reads "1. jumped over the moon" Which is as shown to the user as well
 * when displayed.
 * 
 * Welcome to TextBuddy. mytextfile.txt is ready for use command: add little
 * brown fox added to mytextfile.txt: "little brown fox" command: display 1.
 * little brown fox command: add jumped over the moon added to mytextfile.txt:
 * "jumped over the moon" command: display 1. little brown fox 2. jumped over
 * the moon command: delete 2 deleted from mytextfile.txt:
 * "jumped over the moon" command: display 1. little brown fox command: clear
 * all content deleted from mytextfile.txt command: display mytextfile.txt is
 * empty command: exit
 * 
 * @author Cheng Tze Jin
 */
public class TextBuddy {
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	private static final String MESSAGE_EMPTY_FILE = "%s is empty";
	private static final String MESSAGE_CONTENT_CLEARED = "all content deleted from %s";
	private static final String MESSAGE_NO_SUCH_COMMAND = "Invalid command. Please key in a valid command.";
	private static final String MESSAGE_LINE_DELETED = "deleted from %s: \"%s\"";
	private static final String MESSAGE_PHRASE_ADDED = "added to %s: \"%s\"";
	private static final String MESSAGE_ARRAY_SORTED = "The list is sorted.";
	private static final String MESSAGE_ZERO_SEARCH_RESULTS = "\"%s\" cannot be found.";
	private static final String MESSAGE_DISPLAY_SEARCH_RESULTS = "Displaying results containing \"%s\"";
	private static final String MESSAGE_INVALID_INT_TYPE = "Not a line number. Please enter a valid integer to delete a line.";

	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		// String nameOfFile = args[0];
		String fileName = "mytextfile.txt";
		FileEditor file = initializeFile(fileName);
		Vector<String> fileData = file.getFileData();
		displayWelcomeMessage(file.getFileName());
		enterCommand(fileData, file);
	}

	public static FileEditor initializeFile(String fileName) {
		return new FileEditor(fileName);
	}

	public static void enterCommand(Vector<String> fileData, FileEditor file) {
		do {
			System.out.print("command:");
			String direction = userInput.next().toLowerCase();
			executeCommand(direction, fileData, file);
		} while (true);
	}

	/**
	 * This method checks which command the user wants to perform: display, add,
	 * delete, clear or exit. If the user enters none of the aforementioned, a
	 * message will display to inform the user of an invalid command. Else, when
	 * a correct command is entered, it will redirect to the associated method
	 * to perform said command. Any command that changes the data in the file,
	 * as well as the exit function, will save the edited data to the file.
	 * Hence, only search will not result in saving to the file, since no change
	 * has been made to the file.
	 */
	public static void executeCommand(String direction, Vector<String> fileData, FileEditor file) {
		switch (direction) {
		case "display": {
			displayArray(fileData, file);
			saveToFile(fileData, file);
			break;
		}
		case "add": {
			addLine(fileData, file);
			saveToFile(fileData, file);
			break;
		}
		case "delete": {
			deleteLine(fileData, file);
			saveToFile(fileData, file);
			break;
		}
		case "clear": {
			emptyArray(fileData, file);
			saveToFile(fileData, file);
			break;
		}
		case "sort": {
			sortArray(fileData, file);
			saveToFile(fileData, file);
			break;
		}
		case "search": {
			searchArray(fileData);
			break;
		}
		case "exit": {
			saveToFile(fileData, file);
			System.exit(0);
		}
		default:
			showToUser(MESSAGE_NO_SUCH_COMMAND);
		}
	}

	public static void searchArray(Vector<String> fileData) {
		String searchPhrase = getPhrase();
		Vector<String> matchingText = DataModifier.searchForPhrase(fileData, searchPhrase);
		displayArray(matchingText, searchPhrase);
	}

	public static void sortArray(Vector<String> fileData, FileEditor file) {
		fileData = DataModifier.sortArrayAlphabet(fileData);
		showToUser(MESSAGE_ARRAY_SORTED);
	}

	public static void saveToFile(Vector<String> fileData, FileEditor file) {
		file.writeToFile(fileData);
	}

	public static void deleteLine(Vector<String> fileData, FileEditor file) {
		int lineToBeDeleted = getLine();
		if (DataModifier.isExistingLine(fileData, lineToBeDeleted)) {
			String phraseToBeDeleted = DataModifier.getPhraseFromArray(fileData, lineToBeDeleted);
			fileData = DataModifier.deleteFromArray(fileData, lineToBeDeleted);
			showToUser(String.format(MESSAGE_LINE_DELETED, file.getFileName(), phraseToBeDeleted));
		}
	}

	/**Only allows the next input to be a integer. Otherwise, warning message will show
	 * -1 represents a non-integer input by the user.
	 */
	public static int getLine() {
		try {
			int deleteLine = userInput.nextInt();
			return (deleteLine - 1);
		} catch (InputMismatchException s) {
			showToUser(MESSAGE_INVALID_INT_TYPE);
			@SuppressWarnings("unused")
			String trash = userInput.nextLine();
			return -1;
		}
	}

	public static void addLine(Vector<String> fileData, FileEditor file) {
		String phraseToBeAdded = getPhrase();
		fileData = DataModifier.addToArray(fileData, phraseToBeAdded);
		showToUser(String.format(MESSAGE_PHRASE_ADDED, file.getFileName(), phraseToBeAdded));
	}

	public static String getPhrase() {
		String addPhrase = userInput.nextLine();
		return addPhrase.trim();
	}
	
	//displays search results, depending on number of results
	public static void displayArray(Vector<String> fileData, String Phrase) {
		if (fileData.isEmpty()) {
			showToUser(String.format(MESSAGE_ZERO_SEARCH_RESULTS, Phrase));
		} else {
			showToUser(String.format(MESSAGE_DISPLAY_SEARCH_RESULTS, Phrase));
			DataModifier.showFileContent(fileData);
		}
	}

	public static void displayArray(Vector<String> fileData, FileEditor file) {
		if (fileData.isEmpty()) {
			showToUser(String.format(MESSAGE_EMPTY_FILE, file.getFileName()));
		} else {
			DataModifier.showFileContent(fileData);
		}
	}

	public static void emptyArray(Vector<String> fileData, FileEditor file) {
		fileData = DataModifier.clearArray(fileData);
		showToUser(String.format(MESSAGE_CONTENT_CLEARED, file.getFileName()));
	}

	public static void displayWelcomeMessage(String fileName) {
		showToUser(String.format(MESSAGE_WELCOME, fileName));
	}

	public static void showToUser(String message) {
		System.out.println(message);
	}

}
