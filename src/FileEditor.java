import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileEditor {
	protected Vector<String> _fileData;
	protected File _file;
	protected String _fileName;

	public FileEditor(String fileName) {
		_file = new File(fileName);
		_fileData = new Vector<String>();
		_fileName = fileName; 
		checkIfFileExist();
		retrieveFileData();
	}

	public void checkIfFileExist() {
		if (_file.exists() == false) {
			try {
				_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void retrieveFileData() {
		try {
			Scanner sc = new Scanner(_file);
			while (sc.hasNextLine()) {
				_fileData.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Vector<String> getFileData() {
		return _fileData;
	}
	
	public String getFileName(){
		return _fileName;
	}
}
