/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import command.AcceptChallengeCommand;
import command.Command;
import command.DenyChallengeCommand;
import domain.Challenge;
import domain.ChallengeMediator;
import interaction.RestrictedChallengeScreen;
import interaction.Screen;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class RestrictedChallengeMode implements Mode {

	private final Screen screen;
	private final GameContext context;
	private final UserManager userManager;
	private final ChallengeMediator mediator;
	private final AuthenticationManager authManager;
	private Map<Character, Command> commands;

	public RestrictedChallengeMode(GameContext context,
			UserManager userManager,
			ChallengeMediator mediator,
			Challenge challenge,
			AuthenticationManager authManager) {

		this.context = context;
		this.userManager = userManager;
		this.mediator = mediator;
		this.authManager = authManager;
		this.screen = new RestrictedChallengeScreen(
				context.getScanner(), challenge
		);

		initCommands(challenge);
	}

	private void initCommands(Challenge challenge) {
		commands = new HashMap<>();
		commands.put('a', new AcceptChallengeCommand(context, userManager, authManager, mediator));
		commands.put('b', new DenyChallengeCommand(context, userManager, mediator, challenge, authManager));
	}

	@Override
	public Mode showScreen() {
		char option = screen.showScreen(commands.keySet());
		return doAction(option);
	}

	@Override
	public Mode doAction(char option) {
		commands.get(option).execute();
		return context.getNextMode();
	}
}
