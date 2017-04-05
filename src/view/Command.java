package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Command {

	private Scanner scanner;

	public Command() {
		File file = new File("commands");
		try {
			scanner = new Scanner(file);
			String contents = scanner.useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
