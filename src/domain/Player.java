/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class Player extends User{
	private String registerNumber;	
	private int gold;
	private GameCharacter charac;
	boolean blocked;
	
	public Player(String n, String nck, String p){
		super(n, nck, p);
		//registerNumber = newRegisterNumber();
		gold = 0;
   		charac = null;
		blocked = false;
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
}
