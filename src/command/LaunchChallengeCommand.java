/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import control.UserManager;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.Player;
import java.util.Scanner;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class LaunchChallengeCommand implements Command{
	
	private final GameContext context;
	private final UserManager userManager;
	private final ChallengeMediator mediator;

	public LaunchChallengeCommand(GameContext context,
			UserManager userManager,
			ChallengeMediator mediator) {
		this.context = context;
		this.userManager = userManager;
		this.mediator = mediator;
	}

	@Override
    public void execute() {
        Scanner sc = context.getScanner();
        Player defying = (Player) context.getCurrentUser();

		if (defying.getGameCharacter() != null){

	        System.out.println("Nick del rival:");
   	     	String rivalNick = sc.nextLine().trim();

	        System.out.println("Oro a apostar:");
    	    int bet;
	        try {
	            bet = Integer.parseInt(sc.nextLine().trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Cantidad invalida.");
	            return;
	        }

	        if (bet <= 0 || bet > defying.getGold()) {
	            System.out.println("No puedes apostar esa cantidad.");
	            return;
	        }

	        if (!(userManager.findByNick(rivalNick) instanceof Player)) {
	            System.out.println("El jugador no existe.");
	            return;
	        }

			if ((userManager.findByNick(rivalNick).equals(defying))) {
	            System.out.println("No puedes desafiarte a ti mismo.");
	            return;
	        }

	        Player defied = (Player) userManager.findByNick(rivalNick);

			if (bet > defied.getGold()){
				System.out.println("La cantidad es mayor que lo que el rival tiene disponible.");
			}

	        Challenge c = new Challenge(defying, defied, bet);
	        mediator.registerChallenge(c);

	        System.out.println("Desafio enviado. Pendiente de validacion por un administrador.");
		}else{
			System.out.println("Jugador sin personaje.");
		}
		System.out.println("Pulse ENTER para continuar.");
		sc.nextLine();
    }
}
