/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class Challenge implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Administrator validatedBy;    
    private ChallengeState state;
	private final Player defyingPlayer;
	private final Player defiedPlayer;
	private final int betGold;
	private List<Strength> activeStrengths = new ArrayList<>();
	private List<Weakness> activeWeaknesses = new ArrayList<>();

	//REVISAR
	public void setActiveModifiers(List<Strength> s, List<Weakness> w) {
		this.activeStrengths = s;
		this.activeWeaknesses = w;
	}


    public Challenge(Player defyingPlayer,
                     Player defiedPlayer,
                     int betGold) {
        this.defyingPlayer = defyingPlayer;
        this.defiedPlayer = defiedPlayer;
        this.betGold = betGold;
        this.state = ChallengeState.PENDING_ADMIN_VALIDATION;
    }
	
	// Getters
	public Player getDefyingPlayer() {
		return defyingPlayer;
	}

	public Player getDefiedPlayer() {
		return defiedPlayer;
	}

	public int getBetGold() {
		return betGold;
	}

	public ChallengeState getState() {
		return state;
	}

	public void validateByAdmin(Administrator admin) {
		this.validatedBy = admin;
		this.state = ChallengeState.PENDING_PLAYER_RESPONSE;
	}

	public void denyByAdmin(Administrator admin) {
		this.validatedBy = admin;
		this.state = ChallengeState.DENIED_BY_ADMIN;
	}

	public void acceptByPlayer() {
        this.state = ChallengeState.ACCEPTED;
    }

    public void rejectByPlayer() {
        this.state = ChallengeState.REJECTED;
    }

    public void finish() {
        this.state = ChallengeState.FINISHED;
    }
}
