package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileAnalyzer {

	public static Profile calculateCommonProfile(List<Profilable> profilables) {
		int agressiveCount = 0;
		int moderateCount = 0;
		int shyCount = 0;
		for (Profilable profilable : profilables) {
			if (profilable.getProfile() == Profile.AGRESSIVE) {
				++agressiveCount;
			} else if (profilable.getProfile() == Profile.MODERATE) {
				++moderateCount;
			} else {
				++shyCount;
			}
		}
		List<Profile> profiles = new ArrayList<Profile>();
		int maxCount = Math.max(Math.max(agressiveCount, moderateCount), shyCount);
		if (agressiveCount == maxCount) {
			profiles.add(Profile.AGRESSIVE);
		}
		if (moderateCount == maxCount) {
			profiles.add(Profile.MODERATE);
		}
		if (shyCount == maxCount) {
			profiles.add(Profile.SHY);
		}

		return profiles.get(new Random().nextInt(profiles.size()));
	}

}
