/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.AuthenticationMode;
import control.GameContext;
import control.MenuMode;
import control.Mode;
import control.UserManager;
import domain.ChallengeMediator;
import domain.Player;
import domain.User;
import interaction.AuthenticationScreen;
import interaction.LoginErrorScreen;
import interaction.MenuScreen;
import interaction.RegisterErrorScreen;
import interaction.WelcomeScreen;
import java.util.Set;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class AuthenticationCommand implements Command{

	private final GameContext context;
	private final AuthenticationScreen authScreen;
	private final UserManager userManager;
	private final AuthenticationManager authManager;
	private final Mode successMode;
	private final ChallengeMediator challengeMed;

	public AuthenticationCommand(
			GameContext context,
			AuthenticationScreen authScreen,
			UserManager userManager,
			AuthenticationManager authManager,
			ChallengeMediator challengeMediator) {

		this.context = context;
		this.authScreen = authScreen;
		this.userManager = userManager;
		this.authManager = authManager;
		this.challengeMed = challengeMediator;
		//REVISAR
		this.successMode = new MenuMode(new MenuScreen(context), context, authManager, userManager, challengeMediator);
	}


	@Override
	public void execute() {
		String[] credentials = authScreen.askCredentials();

		if (authManager.login(credentials[0], credentials[1])){
			User user = userManager.findByNick(credentials[0]);
			if ((user instanceof Player player) && player.isBlocked()){
				System.out.println("Este usuario está bloqueado. Localice a un administrador.");
	            context.setNextMode(new AuthenticationMode(new LoginErrorScreen(context.getScanner()), context, authManager, userManager, challengeMed));
			}
			if (user != null) {
	            context.setCurrentUser(user);
	            context.setNextMode(successMode);
	        } else {
	            context.setNextMode(new AuthenticationMode(new LoginErrorScreen(context.getScanner()), context, authManager, userManager, challengeMed));
	        }
		} else {
			// Muestras la pantalla de error
    		new RegisterErrorScreen(context.getScanner()).showScreen(Set.of());

    		// Vuelves al menú de selección (AuthenticationMode)
    		context.setNextMode(new AuthenticationMode(
            	new WelcomeScreen(context.getScanner()),
            	context,
            	authManager,
            	userManager,
				challengeMed
    		));
		}
	}
	
}
