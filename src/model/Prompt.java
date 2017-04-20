package model;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fusesource.jansi.AnsiConsole;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

public class Prompt {
	private static Prompt instance = null;
	private ConsolePrompt consolePrompt;
	private PromptBuilder consolePromptBuilder;

	protected Prompt() {
		AnsiConsole.systemInstall();
		refreshConsoleBuilder();
	}

	public static Prompt getInstance() {
		if (instance == null) {
			instance = new Prompt();
		}
		return instance;
	}

	public void printWelcomeMessage() {
		System.out.println(ansi().eraseScreen().render(
				"@|red,italic Hello commander!|@\n@|reset "
						+ "Are you sure you want to play|@ @|yellow Porrinha|@ with the best robots in the world?\n"
						+ "I'm sure you want to. So let's start. @|green Good luck.|@ "));
	}

	public void printTheGameWillStart() {
		System.out.println(ansi().bold().render("\n@|yellow,italic The game will start! :)|@\n"));
	}
	
	public void printNewRound(int roundNumber) {
		System.out.println(ansi().render(String.format("\n@|red,bold ROUND %s !!|@\n", roundNumber)));
	}

	public void printRoundWinner(Player roundWinner) {
		String message = roundWinner != null ? String
				.format("@|%s,bold %s won the round!|@\n", roundWinner.getColor(), roundWinner.getId())
				: "@|white,bold None of the players won the round.|@\n";
		System.out.println(ansi().render(message));
	}

	public void printGameWinner(Player gameWinner) {
		System.out.println(ansi().render(String.format("\n@|%s,bold %s won the game!!!!|@\n", gameWinner.getColor(), gameWinner.getId())));
	}
	
	public void printHeaderRoundBets() {
		System.out.println(ansi().render(String.format("\n@|white,bold Round bets:|@")));
	}

	public void printBet(Bet bet) {
		Player player = bet.getPlayer();
		System.out.println(ansi().render(String.format("@|%s,italic %s bet %s|@", player.getColor(), player.getId(), bet.getValue())));
	}

	public String askForPlayerName() throws Exception {
		refreshConsoleBuilder();
		consolePromptBuilder.createInputPrompt().name("name").message("Please enter your name: ").defaultValue("Coxinha").addPrompt();
		HashMap<String, ? extends PromtResultItemIF> results = instance.results();
		return ((InputResult) results.get("name")).getInput();
	}

	public Integer askForNumberOfComputers() throws Exception {
		refreshConsoleBuilder();
		consolePromptBuilder.createListPrompt().name("numberOfComputers").message("Do you want to play with how many computers?").newItem("1")
				.text("One computer").add().newItem("2").text("Two computers").add().newItem("3").text("Three computers").add().newItem("4")
				.text("Four computers").add().addPrompt();
		HashMap<String, ? extends PromtResultItemIF> results = instance.results();
		return Integer.valueOf(((ListResult) results.get("numberOfComputers")).getSelectedId());
	}

	public Integer askHold(int playerChopsticks) throws IOException {
		refreshConsoleBuilder();
		ListPromptBuilder listPromptBuilder = consolePromptBuilder.createListPrompt().name("hold")
				.message("How many sticks do you want to hold?").newItem("0").text("0 chopsticks").add();
		for (int i = 1; i <= playerChopsticks; i++) {
			listPromptBuilder = listPromptBuilder.newItem(String.valueOf(i)).text(String.format("%s chopsticks", i)).add();
		}
		listPromptBuilder.addPrompt();
		HashMap<String, ? extends PromtResultItemIF> results = instance.results();
		return Integer.valueOf(((ListResult) results.get("hold")).getSelectedId());
	}

	public int askBet(List<Bet> bets, int availableChopsticks) throws IOException {
		refreshConsoleBuilder();
		List<Integer> unavailableBets = new ArrayList<>();
		for (Bet bet : bets) {
			unavailableBets.add(bet.getValue());
		}
		ListPromptBuilder listPromptBuilder = consolePromptBuilder.createListPrompt().name("bet").message("Your turn to bet. How much do you want to bet?");
		for (int i = 0; i <= availableChopsticks; i++) {
			if (!unavailableBets.contains(i)) {
				listPromptBuilder = listPromptBuilder.newItem(String.valueOf(i)).text(String.format("Sum of %s chopsticks", i)).add();
			}
		}

		listPromptBuilder.addPrompt();
		HashMap<String, ? extends PromtResultItemIF> results = instance.results();
		return Integer.valueOf(((ListResult) results.get("bet")).getSelectedId());
	}

	private HashMap<String, ? extends PromtResultItemIF> results() throws IOException {
		return consolePrompt.prompt(consolePromptBuilder.build());
	}

	private void refreshConsoleBuilder() {
		consolePrompt = new ConsolePrompt();
		consolePromptBuilder = consolePrompt.getPromptBuilder();
	}
}