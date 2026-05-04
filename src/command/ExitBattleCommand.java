/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.GameContext;
import control.MenuMode;
import control.UserManager;
import domain.ChallengeMediator;
import interaction.MenuScreen;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class ExitBattleCommand implements Command{
	
	private final GameContext context;
    private final AuthenticationManager authManager;
    private final UserManager userManager;
    private final ChallengeMediator mediator;

    public ExitBattleCommand(GameContext context,
                             AuthenticationManager authManager,
                             UserManager userManager,
                             ChallengeMediator mediator) {
        this.context = context;
        this.authManager = authManager;
        this.userManager = userManager;
        this.mediator = mediator;
    }
	
	@Override
    public void execute() {
        context.setNextMode(new MenuMode(
                new MenuScreen(context),
                context,
                authManager,
                userManager,
                mediator
        ));
    }


	
}
