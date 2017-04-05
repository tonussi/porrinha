package porrinha;

import static org.junit.Assert.*;

import org.junit.Test;

import view.CommandFactory;
import view.ReadFile;
import view.TerminalUserInterface;

public class CommandFactoryTest {

	@Test
	public void testMethodNameConnectionWithPrototype() {
		TerminalUserInterface term = TerminalUserInterface.getInstance();
		term.open(0, 0, 700, 700);

		ReadFile fileCommands = new ReadFile("C:/Users/estagiario.satelite/workspace/porrinha/commands");
		CommandFactory cd = new CommandFactory(fileCommands.commands);

		assertEquals("bet", cd.methodMap.get("bet").method());
		assertEquals("hold", cd.methodMap.get("hold").method());
	}

}
