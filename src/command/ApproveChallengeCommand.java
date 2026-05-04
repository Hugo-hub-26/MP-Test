/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.GameContext;
import control.MenuMode;
import control.UserManager;
import domain.Administrator;
import domain.Challenge;
import domain.ChallengeMediator;
import interaction.MenuScreen;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class ApproveChallengeCommand implements Command{

	private final GameContext context;
	private final UserManager userManager;
	private final ChallengeMediator mediator;
	private final Challenge challenge;
	private final AuthenticationManager authManager; 

	public ApproveChallengeCommand(GameContext context,
			UserManager userManager,
			ChallengeMediator mediator,
			Challenge challenge,
			AuthenticationManager authManager) {
		this.context = context;
		this.userManager = userManager;
		this.mediator = mediator;
		this.challenge = challenge;
		this.authManager = authManager;
	}

	@Override
	public void execute() {

		if (!(context.getCurrentUser() instanceof Administrator admin)) {
			System.out.println("Solo un operador puede validar.\n");
			return;
		}

		if (challenge.getDefyingPlayer().getGameCharacter() == null ||
    		challenge.getDefiedPlayer().getGameCharacter() == null) {
    		System.out.println("Desafío inválido: jugador sin personaje.");
    		challenge.denyByAdmin(admin);
    		return;
		}

		int combatType = 1;

		System.out.println("""
Configurar tipo de combate para este desafío.
1) Dia			
2) Noche
3) Tarde
                     				""");
		System.out.println("Introduce el tipo de combate:");
		while (true) {
			String line = context.getScanner().nextLine().trim();
			if (line.isEmpty()) {
				break;
			}
			combatType = Integer.parseInt(line);
		}
		challenge.setCombatType(combatType);

		mediator.passChallenge(challenge, admin);

		System.out.println("Desafío validado. Notificación enviada al jugador desafiado.\n");

		// Volver al menú de admin
		context.setNextMode(new MenuMode(new MenuScreen(context),
				context, authManager, userManager, mediator));
	}
	
}
