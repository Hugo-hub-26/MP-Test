/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import command.AcceptChallengeCommand;
import command.CheckRankingCommand;
import command.Command;
import command.EditCharacterCommand;
import command.ExitCommand;
import command.ManageUsersCommand;
import command.RegisterCharacterCommand;
import command.SendChallengeCommand;
import command.UnregisterCharacterCommand;
import command.UnregisterCommand;
import command.ValidateChallengeCommand;
import domain.Administrator;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.Player;
import interaction.Screen;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class MenuMode implements Mode{
	private final Screen screen;
	private final GameContext context;
	private final AuthenticationManager authManager;
    private final UserManager userManager;
	private final ChallengeMediator challengeMediator;
	private Map<Character, Command> commands;

	public MenuMode(Screen screen, GameContext context, AuthenticationManager authManager, UserManager userManager, ChallengeMediator challengeMediator){
		this.screen = screen;
		this.context = context; 
		this.authManager = authManager;
		this.userManager = userManager;
		this.challengeMediator = challengeMediator;
		initCommands();
	}
	
	private void initCommands() {
	    if (context.getCurrentUser() instanceof Player) {
	        initPlayerCommands();
	    } else if (context.getCurrentUser() instanceof Administrator) {
	        initAdminCommands();
	    } else {
	        initPlayerCommands();
		}
	}

	private void initPlayerCommands() {
		commands = new HashMap<>();

		commands.put('a', new SendChallengeCommand(context, userManager, challengeMediator));
		commands.put('b', new AcceptChallengeCommand(context, userManager, authManager, challengeMediator));
		commands.put('c', new RegisterCharacterCommand(context));
		commands.put('d', new EditCharacterCommand(context,userManager));
		commands.put('e', new CheckRankingCommand(context, userManager, authManager, challengeMediator));
		commands.put('f', new UnregisterCharacterCommand(context, userManager, authManager, challengeMediator));
		commands.put('g', new UnregisterCommand(context, userManager, authManager, challengeMediator));
		commands.put('h', new ExitCommand(context));
	}	

	private void initAdminCommands() {
		commands = new HashMap<>();
	
		commands.put('a', new ValidateChallengeCommand(context, userManager, challengeMediator, authManager));
		commands.put('b', new EditCharacterCommand(context, userManager));
		commands.put('c', new ManageUsersCommand(context, userManager));
		commands.put('d', new CheckRankingCommand(context, userManager, authManager, challengeMediator));
		commands.put('e', new UnregisterCommand(context, userManager, authManager, challengeMediator));
		commands.put('f', new ExitCommand(context));
	}

	@Override
	public Mode showScreen() {
		initCommands();
		char option = screen.showScreen(commands.keySet());
		return doAction(option);
	}

	@Override
	public Mode doAction(char option) {
		Command command = commands.get(option);

		if (context.getCurrentUser() instanceof Player player &&
		    challengeMediator.hasPendingChallenge(player)) {

		    Challenge c = challengeMediator.challengesForPlayer(player).get(0);
		    context.setNextMode(new RestrictedChallengeMode(context, userManager, challengeMediator, c, authManager));
    return context.getNextMode();
}

		if (command == null) {
			throw new IllegalStateException(
					"Ningun comando asociado con esta opcion: " + option
			);
		}
		command.execute();
		return context.getNextMode();
	}
}
