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
public class DenyChallengeCommand implements Command{

	private final GameContext context;
    private final UserManager userManager;
    private final ChallengeMediator mediator;
	private final AuthenticationManager authManager;
    private final Challenge challenge;

    public DenyChallengeCommand(GameContext context,
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
			System.out.println("Solo un operador puede rechazar.\n");
			return;
		}

		mediator.denyChallenge(challenge, admin);
		System.out.println("Desafío rechazado.\n");

		context.setNextMode(new MenuMode(new MenuScreen(context),
				context, authManager, userManager, mediator));
	}
	
}
