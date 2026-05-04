/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class Player extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private static int nextRegisterNumber = 1;   // contador global
    private final int registerNumber;
	private int gold;
	private GameCharacter charac;
	private boolean blocked;
	private Instant lastChallengeTime;
	
	public Player(String n, String nck, String p){
		super(n, nck, p);
		this.registerNumber = nextRegisterNumber++;
		gold = 200;
   		charac = null;
		blocked = false;
	}
	
	public void markChallengeTime() {
    	this.lastChallengeTime = Instant.now();
	}
	
	public boolean canBeChallenged() {
    	if (lastChallengeTime == null) {
        	return true; // nunca ha combatido
    	}
	    Duration elapsed = Duration.between(lastChallengeTime, Instant.now());
	    return elapsed.toHours() >= 24;
	}

	
	public int getRegisterNumber() {
   		return registerNumber;
	}
	public static void updateNextRegisterNumber(int usedNumber) {
    	if (usedNumber >= nextRegisterNumber) {
        	nextRegisterNumber = usedNumber + 1;
    	}
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getGold(){
		return this.gold;
	}
	
    public void setGameCharacter(GameCharacter charac){
  		this.charac = charac;
    }

	public void block(){
		this.blocked = true;
	}

	public void unblock(){
		this.blocked = false;
	}

	public boolean isBlocked(){
		return blocked;
	}

	public GameCharacter getGameCharacter() {
		return charac;
	}

}
