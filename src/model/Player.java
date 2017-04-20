package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

	public final String id;
	private int chopsticks;
	private final String color;
	private List<Bet> betHistory;
	private List<Hold> holdHistory;
	private boolean human;

	public Player(String id, String color, boolean human) {
		super();
		this.id = id;
		this.color = color;
		this.human = human;
		this.chopsticks = 3;
		this.betHistory = new ArrayList<Bet>();
		this.holdHistory = new ArrayList<Hold>();
	}
	
	public Bet bet(int value, int availableChosticks) {
		Bet bet = new Bet(this, value, availableChosticks);
		betHistory.add(bet);
		return bet;
	}

	public Bet bet(List<Bet> unavailableBets, int sumOfAvailableChopsticks,
			List<Player> players) {
		Bet bet = BetAdvisor.calculateBetterBet(this, unavailableBets,
				sumOfAvailableChopsticks, players);
		betHistory.add(bet);
		return bet;
	}
	
	public Hold hold(int value) {
		Hold hold = new Hold(this, value);
		holdHistory.add(hold);
		return hold;
	}

	public Hold hold() {
		Hold hold = new Hold(this, new Random().nextInt(chopsticks));
		holdHistory.add(hold);
		return hold;
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

	public List<Hold> getHoldHistory() {
		return holdHistory;
	}

	public boolean isHuman() {
		return human;
	}
}
