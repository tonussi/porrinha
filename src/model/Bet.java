package model;

public class Bet implements Profilable{

	private final Player player;
	private final Profile profile;
	private final int value;
	private int sumOfChopsticks;

	public Bet(Player player, int value, int sumOfChopsticks) {
		super();
		this.player = player;
		this.value = value;
		this.sumOfChopsticks = sumOfChopsticks;
		this.profile = analyzeProfile();
	}

	private Profile analyzeProfile() {
		int intervalStep = sumOfChopsticks / 3;
		if (value < intervalStep) {
			return Profile.SHY;
		} else if (value >= intervalStep && value <= intervalStep * 2) {
			return Profile.MODERATE;
		}
		return Profile.AGRESSIVE;
	}

	public Profile getProfile() {
		return profile;
	}

	public Player getPlayer() {
		return player;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		if (value != other.value)
			return false;
		return true;
	}

}