/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import domain.Catalogue;
import domain.GameCharacter;
import domain.Player;
import domain.User;
import java.util.Scanner;

/**
 * Implementa el patrón Decorator
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class GameContext {
	private User currentUser;
	private GameCharacter char1;
	private GameCharacter char2;
	private boolean draw;
	private final Scanner scanner;
    private final Catalogue catalog;
	private Mode nextMode;

	public GameContext(Scanner s, Catalogue cat){
		scanner = s;
        catalog = cat;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Mode getNextMode() {
		return nextMode;
	}

	public void setNextMode(Mode mode) {
		nextMode = mode;	
	}

	public Scanner getScanner() {
		return scanner;
	}

    public Catalogue getCatalog() {
   		return catalog;
   	}

	public GameCharacter getCharacter1() {
		return this.char1;
	}

	public GameCharacter getCharacter2() {
		return this.char2;
	}

	public void setCharacter1(GameCharacter char1) {
		this.char1 = char1;
	}

	public void setCharacter2(GameCharacter char2) {
		this.char2 = char2;
	}

	public void setDraw(boolean b) {
		draw = b;
	}

	public boolean getDraw() {
		return draw;
	}
}
