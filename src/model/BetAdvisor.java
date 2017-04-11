package model;

import java.util.List;
import java.util.Random;

public class BetAdvisor {

	public static Bet calculateBetterBet(Player player, List<Bet> unavailableBets, int sumOfAvailableChopsticks,
			List<Round> roundHistory) {
		Bet bet;
		int betValue = 0;
		do {
			if (roundHistory.size() < 3) {
				betValue = generateRandomBetValue(sumOfAvailableChopsticks);
			} else {
				betValue = generateProbabilisticBetValue(roundHistory);
			}
			bet = new Bet(player, betValue);
		} while (unavailableBets.contains(bet));
		return bet;
	}

	private static int generateRandomBetValue(int sumOfAvailableChopsticks) {
		return new Random().nextInt(sumOfAvailableChopsticks);
	}

	private static int generateProbabilisticBetValue(List<Round> roundHistory) {
		return 0;
	}
}
