package model;

import java.util.ArrayList;
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

	private static Map<Integer, Integer> findFrequencies(List<Round> roundHistory, int numberOfPlayers) {
		int listOfAllBets[] = new int[roundHistory.size() * numberOfPlayers];
		int minimumValue = Integer.MAX_VALUE, maximumValue = 0, squareRoot = 0;

		squareRoot = (int) Math.sqrt(roundHistory.size());

		for (int i = 0; i < roundHistory.size(); i++) {
			for (Bet bet : roundHistory.get(i).getBets()) {
				listOfAllBets[i] = bet.getValue();
				minimumValue = Math.min(minimumValue, listOfAllBets[i]);
				maximumValue = Math.max(maximumValue, listOfAllBets[i]);
			}
		}

		int difference = Math.abs(maximumValue - minimumValue);
		int classInterval = (int) difference / squareRoot;

		int quantityOfClasses = (int) (maximumValue - minimumValue) / classInterval;
		int listOfClasses[] = new int[quantityOfClasses];

		int accumulativeClasseValue = minimumValue;
		for (int i = 0; i < quantityOfClasses; i++) {
			accumulativeClasseValue = (int) (accumulativeClasseValue + classInterval);
			listOfClasses[i] = accumulativeClasseValue;
		}

		return groupAndMapFrequencies(listOfAllBets, listOfClasses);
	}

	private static Map<Integer, Integer> groupAndMapFrequencies(int[] listOfAllBets, int[] listOfClasses) {

		Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

		int accumulativeClassValue = 0;
		for (int i = 0; i < listOfClasses.length; i++) {
			accumulativeClassValue = listOfClasses[i];
			for (int j = 0; j < listOfAllBets.length; j++) {
				if (listOfAllBets[j] <= accumulativeClassValue) {
					frequencies.put(listOfAllBets[j], 1);
				}
			}
		}
		return frequencies;
	}

}
