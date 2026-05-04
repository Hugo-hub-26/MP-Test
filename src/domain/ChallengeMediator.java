/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import control.ChallengeManager;
import java.util.List;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class ChallengeMediator {

	private final ChallengeManager challengeManager;

	public ChallengeMediator(ChallengeManager challengeManager) {
		this.challengeManager = challengeManager;
	}

	

	public void registerChallenge(Challenge challenge) {
		challengeManager.addChallenge(challenge);
	}

	public Challenge nextChallengeForAdmin() {
		return challengeManager.getChallenges().stream()
				.filter(c -> c.getState()
				== ChallengeState.PENDING_ADMIN_VALIDATION)
				.findFirst()
				.orElse(null);
	}

	public void passChallenge(Challenge challenge, Administrator admin) {
		challenge.validateByAdmin(admin);
		challengeManager.save();
	}

	public void denyChallenge(Challenge challenge, Administrator admin) {
		challenge.denyByAdmin(admin);
		challengeManager.save();
	}

	public List<Challenge> challengesForPlayer(Player player) {
		return challengeManager.getChallenges().stream()
				.filter(c -> c.getDefiedPlayer().equals(player)
				&& c.getState()
				== ChallengeState.PENDING_PLAYER_RESPONSE)
				.toList();
	}

	public boolean hasPendingChallenge(Player player) {
    	return challengeManager.hasPendingChallenge(player);
	}

}
