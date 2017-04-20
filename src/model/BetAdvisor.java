package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BetAdvisor {

	public static Bet calculateBetterBet(Player player,
			List<Bet> unavailableBets, int sumOfAvailableChopsticks,
			List<Player> players) {
		int betValue = -1;
		int offset = 1;
		List<Integer> betValues = selectBetValues(unavailableBets);
		do {
			if (betValue == -1) {
				betValue = calculateBetterBetValue(players);
			} else {
				betValue = betValue + offset;
				offset = offset > 0 ? offset * -1 : (offset * -1) + 1;
			}
		} while (betValues.contains(betValue));
		return new Bet(player, betValue, sumOfAvailableChopsticks);
	}

	@SuppressWarnings("unchecked")
	private static int calculateBetterBetValue(List<Player> players) {
		Profile holdProfile;
		int sumOfHold = 0;

		for (Player player : players) {
			holdProfile = ProfileAnalyzer
					.calculateCommonProfile((List<Profilable>) (List<?>) player
							.getHoldHistory());
			sumOfHold += calculatePlayerHoldByProfile(player, holdProfile);
		}

		return sumOfHold;
	}

	private static int calculatePlayerHoldByProfile(Player player,
			Profile holdProfile) {
		if (holdProfile == Profile.AGRESSIVE) {
			return player.getChopsticks();
		} else if (holdProfile == Profile.SHY) {
			return 0;
		}
		return 1 + new Random().nextInt(2);
	}

	private static List<Integer> selectBetValues(List<Bet> unavailableBets) {
		List<Integer> betValues = new ArrayList<>();
		for (Bet bet : unavailableBets) {
			betValues.add(bet.getValue());
		}
		return betValues;
	}
}