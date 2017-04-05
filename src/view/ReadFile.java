package view;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {

	public List<String> commands;

	public ReadFile(String string) {
        try {

        	System.out.print("Enter the file name: ");

            Scanner input = new Scanner("C:/Users/estagiario.satelite/workspace/porrinha/commands");
            this.commands = new ArrayList<String>();

            File file = new File(input.nextLine());

            input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                commands.add(line);
                System.out.println("Command: " + line);
            }

            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
