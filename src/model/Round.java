package model;

import java.util.List;

public class Round {

	private final Player winner;
	private final int chopsticks;
	private final List<Bet> bets;

	public Round(Player winner, int chopsticks, List<Bet> roundBets) {
		super();
		this.winner = winner;
		this.chopsticks = chopsticks;
		this.bets = roundBets;
	}

	public Player getWinner() {
		return winner;
	}

	public int getChopsticks() {
		return chopsticks;
	}

	public List<Bet> getBets() {
		return bets;
	}

}
