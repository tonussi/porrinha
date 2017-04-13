package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

	public final String id;
	private int chopsticks;
	private final String color;
	private List<Bet> betHistory;

	public Player(String id, String color) {
		super();
		this.id = id;
		this.color = color;
		this.chopsticks = 3;
		this.betHistory = new ArrayList<Bet>();
	}

	public Bet bet(List<Bet> unavailableBets, int sumOfAvailableChopsticks, List<Round> roundHistory) {
		BetAdvisor.calculateBetterBet(this, unavailableBets, sumOfAvailableChopsticks, roundHistory);
		Bet bet;
		do {
			bet = new Bet(this, sumOfAvailableChopsticks);
		} while (unavailableBets.contains(bet));
		betHistory.add(bet);
		return bet;
	}

	public Hold hold() {
		return new Hold(this, new Random().nextInt(chopsticks));
	}

	public void discardChopstick() {
		--chopsticks;
	}

	public String getColor() {
		return color;
	}

	public String getId() {
		return id;
	}

	public int getChopsticks() {
		return chopsticks;
	}

	public Bet getLastBet() {
		return betHistory.get(betHistory.size() - 1);
	}

}
