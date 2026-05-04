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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class CheckRankingCommand implements Command{
	
	private final GameContext context;
    private final UserManager userManager;
	private final AuthenticationManager authManager;
	private final ChallengeMediator	challengeMed;

    public CheckRankingCommand(GameContext context, UserManager userManager, AuthenticationManager authManager, ChallengeMediator challengeMed) {
        this.context = context;
        this.userManager = userManager;
		this.authManager = authManager;
		this.challengeMed = challengeMed;
    }

	@Override
	public void execute() {
	
	System.out.println("\n[RANKING DE JUGADORES POR ORO]\n");

        List<Player> ranking = userManager.getUsers().stream()
                .filter(u -> u instanceof Player)
                .map(u -> (Player) u)
                .sorted(Comparator.comparingInt(Player::getGold).reversed())
                .collect(Collectors.toList());

		
		if (ranking.isEmpty()) {
            System.out.println("No hay jugadores registrados.\n");
        } else {
            int pos = 1;
            for (Player p : ranking) {
                System.out.printf(
                        "%d. %s - Oro: %d%n",
                        pos++,
                        p.getNick(),
                        p.getGold()
                );
            }
			
			
		System.out.println("Pulsa ENTER para volver al menú...");
    	context.getScanner().nextLine();
    }

		
		// Volver al menú correspondiente
        context.setNextMode(new MenuMode(
                new MenuScreen(context),
                context,
                authManager,
                userManager,
                challengeMed
        ));

	}
	
}
