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
import domain.Player;
import interaction.MenuScreen;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class UnregisterCharacterCommand implements Command{
	
	private final GameContext context;
    private final UserManager userManager;
	private final AuthenticationManager authManager;
	private final ChallengeMediator challengeMed;

    public UnregisterCharacterCommand(GameContext context, UserManager userManager, AuthenticationManager authManager, ChallengeMediator challengeMed) {
        this.context = context;
        this.userManager = userManager;
		this.challengeMed = challengeMed;
		this.authManager = authManager;
    }

	@Override
    public void execute() {

        if (!(context.getCurrentUser() instanceof Player player)) {
            System.out.println("Solo un jugador puede eliminar su personaje.\n");
            return;
        }

        if (player.getGameCharacter() == null) {
            System.out.println("No tienes ningún personaje registrado.\n");
            return;
        }

        player.setGameCharacter(null);
        userManager.save();
        System.out.println("Personaje eliminado correctamente.\n");
        context.setNextMode(new MenuMode(
                new MenuScreen(context),
                context,
                authManager,
                userManager,
                challengeMed
        ));
    }

	
}
