package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class BetAdvisor {

	public static Bet calculateBetterBet(Player player, List<Bet> unavailableBets, int sumOfAvailableChopsticks,
			List<Round> roundHistory, int numberOfPlayers) {
		Bet bet;
		int betValue = 0;
		do {
			if (roundHistory.size() < 3) {
				betValue = generateRandomBetValue(sumOfAvailableChopsticks);
			} else {
				betValue = generateProbabilisticBetValue(roundHistory, numberOfPlayers);
			}
			bet = new Bet(player, betValue);
		} while (unavailableBets.contains(bet));
		return bet;
	}

	private static int generateRandomBetValue(int sumOfAvailableChopsticks) {
		return new Random().nextInt(sumOfAvailableChopsticks);
	}

	private static int generateProbabilisticBetValue(List<Round> roundHistory, int numberOfPlayers) {
		return 0;
	}

	/**
	 * This methods do the job of analyzing the frequencies of bets in the round history
	 * once analyzed, this method will return the Chance Nodes of the Expectimax. The
	 * Expectimax is a modification of the Minimax, which involves Chance Nodes between
	 * MAX nodes and MIN nodes.
	 * 
	 * @param List<Round> roundHistory The History of Rounds.
	 * @param int numberOfPlayers Number of Current Players.
	 * @return List<Integer> Chance Nodes.
	 */
	private static Map<Integer, Integer> findMajorProbabilityRamifications(List<Round> roundHistory,
			int numberOfPlayers) {

		int listOfAllBets[] = new int[roundHistory.size() * numberOfPlayers];
		int minimumValue = Integer.MAX_VALUE;
		int maximumValue = 0;
		int maxFrequencyValue1 = 0;
		int maxFrequencyValue2 = 0;
		int frequencyClassValue1 = 0;
		int frequencyClassValue2 = 0;
		int squareRoot = 0;

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

		Map<Integer, Integer> groupedFrequencies = groupAndMapFrequencies(listOfAllBets, listOfClasses);

		for (Entry<Integer, Integer> e : groupedFrequencies.entrySet()) {
			if (e.getValue() > maxFrequencyValue1) {
				maxFrequencyValue1 = e.getValue();
				frequencyClassValue1 = e.getKey();
			}
			if (e.getValue() > maxFrequencyValue2 && maxFrequencyValue2 != maxFrequencyValue1) {
				maxFrequencyValue2 = e.getValue();
				frequencyClassValue2 = e.getKey();
			}
		}

		return groupedFrequencies;

	}

	private static Map<Integer, Integer> groupAndMapFrequencies(int[] listOfAllBets, int[] listOfClasses) {

		Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

		int accumulativeClassValue = 0;
		for (int i = 0; i < listOfClasses.length; i++) {
			accumulativeClassValue = listOfClasses[i];
			for (int j = 0; j < listOfAllBets.length; j++) {
				if (listOfAllBets[j] <= accumulativeClassValue) {
					int tmp = frequencies.get(listOfAllBets[j]);
					frequencies.put(listOfAllBets[j], tmp + 1);
				}
			}
		}
		return frequencies;
	}

}
