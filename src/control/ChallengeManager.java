/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import domain.Challenge;
import static domain.ChallengeState.PENDING_PLAYER_RESPONSE;
import domain.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class ChallengeManager {
	
	private static final String FILE_NAME = "./data/challenges.dat";
	private List<Challenge> challenges;

	public ChallengeManager() {
		load();
	}

	@SuppressWarnings("unchecked")
	private void load() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			challenges = new LinkedList<>();
			return;
		}

		try (ObjectInputStream ois
				= new ObjectInputStream(new FileInputStream(file))) {
			challenges = (List<Challenge>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			challenges = new LinkedList<>();
		}
	}

		public void save() {
		try (ObjectOutputStream oos
				= new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(challenges);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void addChallenge(Challenge challenge) {
		challenges.add(challenge);
		save(); 
	}

	
	public boolean hasPendingChallenge(Player player) {
    return challenges.stream()
        .anyMatch(c ->
            c.getDefiedPlayer().equals(player) &&
            c.getState() == PENDING_PLAYER_RESPONSE
        );
	}


}
