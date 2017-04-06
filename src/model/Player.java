package model;

import java.util.List;
import java.util.Random;

public class Player {

	public final String id;
	private int chopsticks;
	private final String color;

	public Player(String id, String color) {
		super();
		this.id = id;
		this.color = color;
		this.chopsticks = 3;
	}

	public Bet bet(int numberOfPlayers, List<Bet> unavailableBets) {
		Bet bet;
		do {
			bet = new Bet(this, new Random().nextInt(3 * numberOfPlayers));
		} while (unavailableBets.contains(bet));
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

}
