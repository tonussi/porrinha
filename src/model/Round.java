package model;

public class Round {

	private final Player winner;
	private final int chopsticks;
	private final Bet bet;
	
	public Round(Player winner, int chopsticks, Bet bet) {
		super();
		this.winner = winner;
		this.chopsticks = chopsticks;
		this.bet = bet;
	}

	public Player getWinner() {
		return winner;
	}

	public int getChopsticks() {
		return chopsticks;
	}

	public Bet getBet() {
		return bet;
	}
	
	
}
