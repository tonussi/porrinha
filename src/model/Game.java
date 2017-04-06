package model;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import de.codeshelf.consoleui.prompt.ListResult;

public class Game {
	public static void main(String[] args) throws IOException {
		Prompt prompt = Prompt.getInstance();
		prompt.printWelcomeMessage();
		prompt.askForNumberOfPlayers();

		int numberOfPlayers = Integer.valueOf(((ListResult) prompt.results()
				.get("numberOfPlayers")).getSelectedId());
		prompt.printTheGameWillStart();

		List<Player> players = createPlayers(numberOfPlayers);
		Player gameWinner = null;
		Player roundWinner = null;
		List<Hold> playersHolds;
		List<Bet> roundBets;
		do {
			playersHolds = doHoldStep(players);
			roundBets = doBetStep(players);
			prompt.printBets(roundBets);
			roundWinner = findRoundWinner(playersHolds, roundBets);
			prompt.printRoundWinner(roundWinner);

			if (roundWinner != null) {
				roundWinner.discardChopstick();
				roundWinner = null;
			}

			gameWinner = findGameWinner(players);
		} while (gameWinner == null);
		prompt.printGameWinner(gameWinner);
	}

	private static List<Hold> doHoldStep(List<Player> players) {
		List<Hold> playersHold = new ArrayList<Hold>();
		for (Player player : players) {
			playersHold.add(player.hold());
		}
		return playersHold;
	}

	private static List<Bet> doBetStep(List<Player> players) {
		List<Bet> playersBets = new ArrayList<Bet>();
		for (Player player : players) {
			playersBets.add(player.bet(players.size(), playersBets));
		}
		return playersBets;
	}

	private static Player findRoundWinner(List<Hold> holds, List<Bet> bets) {
		int sumOfAllChopsticksOnHand = sumHolds(holds);
		for (Bet bet : bets) {
			if (bet.getValue() == sumOfAllChopsticksOnHand) {
				return bet.getPlayer();
			}
		}
		return null;
	}

	private static int sumHolds(List<Hold> holds) {
		int sum = 0;
		for (Hold hold : holds) {
			sum += hold.getValue();
		}
		return sum;
	}

	private static Player findGameWinner(List<Player> players) {
		for (Player player : players) {
			if (player.getChopsticks() == 0) {
				return player;
			}
		}
		return null;
	}

	// @formatter:off
	
	
	private static List<Player> createPlayers(int numberOfPlayers) {
		Deque<String> colors = new ArrayDeque<String>();
		colors.add("green");
		colors.add("yellow");
		colors.add("magenta");
		colors.add("cyan");
		colors.add("red");
		
		List<Player> players = new ArrayList<Player>();
		for(int id = 1; id <= numberOfPlayers; id++) {
			players.add(new Player(String.format("Player %s", id), colors.pop()));
		}
		
		return players;
	}
	// @formatter:on

}