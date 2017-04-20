package model;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {
	public static void main(String[] args) throws Exception {
		Prompt prompt = Prompt.getInstance();
		prompt.printWelcomeMessage();
		String playerName = prompt.askForPlayerName();
		Integer numberOfPlayers = prompt.askForNumberOfComputers();

		List<Player> players = createPlayers(playerName, numberOfPlayers);
		int availableChopsticks = players.size() * 3;
		int roundNumber = 0;
		Player gameWinner = null;
		Player roundWinner = null;
		List<Hold> playersHolds;
		List<Bet> roundBets;
		List<Round> roundHistory = new ArrayList<>();
		availableChopsticks = players.size() * 3;

		prompt.printTheGameWillStart();
		do {
			prompt.printNewRound(roundNumber + 1);
			playersHolds = doHoldStep(prompt, players);
			roundBets = doBetStep(prompt, players, availableChopsticks, roundNumber, numberOfPlayers);
			roundWinner = findRoundWinner(playersHolds, roundBets);
			prompt.printRoundWinner(roundWinner);
			if (roundWinner != null) {
				roundHistory.add(new Round(roundWinner, availableChopsticks, roundBets));
				roundWinner.discardChopstick();
				availableChopsticks -= 1;
				roundWinner = null;
				gameWinner = findGameWinner(players);
			}
			roundNumber++;
		} while (gameWinner == null);

		prompt.printGameWinner(gameWinner);
	}

	private static List<Hold> doHoldStep(Prompt prompt, List<Player> players) throws IOException {
		List<Hold> playersHold = new ArrayList<Hold>();
		Hold hold = null;
		for (Player player : players) {
			if (player.isHuman()) {
				hold = player.hold(prompt.askHold(player.getChopsticks()));
			} else {
				hold = player.hold();
			}
			playersHold.add(hold);
		}
		return playersHold;
	}

	private static List<Bet> doBetStep(Prompt prompt, List<Player> players, int availableChopsticks, int roundNumber, int numberOfPlayers)
			throws IOException {
		List<Bet> bets = new ArrayList<Bet>();
		Bet bet = null;
		Player bettingPlayer;
		prompt.printHeaderRoundBets();
		while (bets.size() != players.size()) {
			bettingPlayer = players.get((roundNumber + bets.size()) % players.size());
			if (bettingPlayer.isHuman()) {
				bet = bettingPlayer.bet(prompt.askBet(bets, availableChopsticks), availableChopsticks);

			} else {
				bet = bettingPlayer.bet(bets, availableChopsticks, players);
			}
			bets.add(bet);
			prompt.printBet(bet);
		}
		return bets;
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

	private static List<Player> createPlayers(String playerName, int numberOfPlayers) {
		Deque<String> colors = new ArrayDeque<String>();
		colors.add("green");
		colors.add("yellow");
		colors.add("magenta");
		colors.add("cyan");
		colors.add("red");

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(playerName, colors.pop(), true));
		for (int id = 1; id <= numberOfPlayers; id++) {
			players.add(new Player(String.format("Computer %s", id), colors.pop(), false));
		}

		return players;
	}

}
