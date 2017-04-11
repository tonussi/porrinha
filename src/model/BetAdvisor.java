package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	private static Map<Integer, Integer> findFrequencies(List<Round> roundHistory) {
		int listOfSums[] = new int[roundHistory.size()];
		int minimumValue = Integer.MAX_VALUE, maximumValue = 0, squareRoot = 0;

		squareRoot = (int) Math.sqrt(roundHistory.size());

		/* Transform list of sums of chop-sticks to array
		   Get minimum value and maximum value. */
		for (int i = 0; i < roundHistory.size(); i++) {
			listOfSums[i] = roundHistory.get(i).getChopsticks();
			minimumValue = Math.min(minimumValue, listOfSums[i]);
			maximumValue = Math.max(maximumValue, listOfSums[i]);
		}

		int difference = maximumValue - minimumValue;
		int classInterval = difference / squareRoot;

		return groupAndMapFrequencies(listOfSums);
	}

	private static Map<Integer, Integer> groupAndMapFrequencies(int[] listOfSums) {
		Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

		for (int i = 0; i < listOfSums.length; i++) {
			if (!frequencies.containsKey(listOfSums[i])) {
				frequencies.put(listOfSums[i], 1);
			} else {
				frequencies.put(listOfSums[i], frequencies.get(listOfSums[i]) + 1);
			}
		}

		return frequencies;
	}

}
