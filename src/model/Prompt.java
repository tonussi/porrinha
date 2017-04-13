package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import model.Bet;
import model.Player;

import org.fusesource.jansi.AnsiConsole;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

import static org.fusesource.jansi.Ansi.ansi;

public class Prompt {
	private static Prompt instance = null;
	private final ConsolePrompt consolePrompt;
	private final PromptBuilder consolePromptBuilder;

	protected Prompt() {
		AnsiConsole.systemInstall();
		consolePrompt = new ConsolePrompt();
		consolePromptBuilder = consolePrompt.getPromptBuilder();

	}

	public void printWelcomeMessage() {
		System.out
				.println(ansi()
						.eraseScreen()
						.render("@|red,italic Hello commander!|@\n@|reset "
								+ "Are you sure you want to play|@ @|yellow Porrinha|@ with the best robots in the world?\n"
								+ "I'm sure you want to. So let's start. @|green Good luck.|@ "));
	}

	public void askForNumberOfPlayers() {
		consolePromptBuilder.createListPrompt().name("numberOfPlayers")
				.message("Do you want to play with how many players?")
				.newItem("1").text("One player").add().newItem("2")
				.text("Two players").add().newItem("3").text("Three players")
				.add().newItem("4").text("Four players").add().addPrompt();
	}

	public HashMap<String, ? extends PromtResultItemIF> results()
			throws IOException {
		return consolePrompt.prompt(consolePromptBuilder.build());
	}

	public void printTheGameWillStart() {
		System.out.println(ansi().bold().render(
				"\n@|yellow,italic The game will start! :)|@\n"));
	}

	public void printGameWinner(Player gameWinner) {
		System.out.println(ansi().render(
				String.format("\n@|%s,bold %s won the game!!!!|@\n",
						gameWinner.getColor(), gameWinner.getId())));
	}

	public void printRoundWinner(Player roundWinner) {
		String message = roundWinner != null ? String.format(
				"@|%s,bold %s won the round!|@\n", roundWinner.getColor(),
				roundWinner.getId())
				: "@|white,bold None of the players won the round.|@\n";
		System.out.println(ansi().render(message));
	}

	public void printBets(List<Bet> roundBets) {
		System.out.println(ansi().render(
				String.format("\n@|white,bold Round bets:|@")));

		Player player;
		for (Bet bet : roundBets) {
			player = bet.getPlayer();
			System.out.println(ansi().render(
					String.format("@|%s,italic %s - %s|@", player.getColor(),
							player.getId(), bet.getValue())));
		}
	}

	public static Prompt getInstance() {
		if (instance == null) {
			instance = new Prompt();
		}
		return instance;
	}
}