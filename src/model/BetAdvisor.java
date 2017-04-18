package model;

import java.util.List;
import java.util.Random;

public class BetAdvisor {

	public static Bet calculateBetterBet(Player player, List<Bet> unavailableBets, int sumOfAvailableChopsticks,
			List<Player> players) {
		Bet bet = null;
		int betValue = 0;
		do {
			betValue = player.getId().equals("Player 1") ? calculateBetterBetValue(players) : new Random().nextInt(sumOfAvailableChopsticks);
			bet = new Bet(player, betValue, sumOfAvailableChopsticks);
		} while (unavailableBets.contains(bet));
		return bet;
	}

	private static int calculateBetterBetValue(List<Player> players) {
		Profile holdProfile;
		int sumOfHold = 0;

		for (Player player : players) {
			holdProfile = ProfileAnalyzer.calculateCommonProfile((List<Profilable>) (List<?>) player.getHoldHistory());
			sumOfHold += calculatePlayerHoldByProfile(player, holdProfile);
		}
		return sumOfHold;
	}

	private static int calculatePlayerHoldByProfile(Player player, Profile holdProfile) {
		if (holdProfile == Profile.AGRESSIVE) {
			return player.getChopsticks();
		} else if (holdProfile == Profile.SHY) {
			return 0;
		}
		return 1 + new Random().nextInt(2);
	}
}