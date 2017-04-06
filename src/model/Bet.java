package model;

public class Bet {

	private final Player player;
	private final int value;

	public Bet(Player player, int value) {
		super();
		this.player = player;
		this.value = value;
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