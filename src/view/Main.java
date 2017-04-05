package view;

public class Main {

	public static void main(String[] args) {

		TerminalUserInterface term = TerminalUserInterface.getInstance();
		term.open(0, 0, 700, 700);

		ReadFile fileCommands = new ReadFile("C:/Users/estagiario.satelite/workspace/porrinha/commands");
		CommandFactory cd = new CommandFactory(fileCommands.commands);

		System.out.println(cd.methodMap.get("bet").method());
		System.out.println(cd.methodMap.get("hold").method());
	}

}
