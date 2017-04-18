package model;

public class Hold  implements Profilable {

	private final Player player;
	private final Profile profile;
	private final int value;

	public Hold(Player player, int value) {
		super();
		this.player = player;
		this.value = value;
		this.profile = analyzeProfile();
	}

	private Profile analyzeProfile() {
		int chopsticks = player.getChopsticks();
		if (value == chopsticks) {
			return Profile.AGRESSIVE;
		} else if (value == 0) {
			return Profile.SHY;
		}
		return Profile.MODERATE;
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
		Hold other = (Hold) obj;
		if (value != other.value)
			return false;
		return true;
	}

}