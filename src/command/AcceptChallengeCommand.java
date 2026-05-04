/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.BattleMode;
import control.GameContext;
import control.MenuMode;
import control.UserManager;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.Player;
import interaction.MenuScreen;
import java.util.List;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class AcceptChallengeCommand implements Command{

	private final GameContext context;
	private final UserManager userManager;
	private final ChallengeMediator mediator;
	private final AuthenticationManager authManager;

	
	public AcceptChallengeCommand(GameContext context, UserManager userManager, AuthenticationManager authManager, ChallengeMediator mediator) {
        this.context = context;
        this.userManager = userManager;
        this.mediator = mediator;
		this.authManager = authManager;
    }

	@Override
    public void execute() {

        if (!(context.getCurrentUser() instanceof Player player)) {
            System.out.println("Solo un jugador puede aceptar desafíos.\n");
            return;
        }

        List<Challenge> pending = mediator.challengesForPlayer(player); // PENDING_PLAYER_RESPONSE
        if (pending.isEmpty()) {
            System.out.println("No tienes desafíos pendientes.\n");
            return;
        }

        Challenge ch = pending.get(0);

		
System.out.println("""
[Desafío pendiente]
Te desafía: %s
Apuesta: %d
a) Aceptar
b) Rechazar
""".formatted(ch.getDefyingPlayer().getNick(), ch.getBetGold()));

        while (true) {
            System.out.print("Opcion:");
            String input = context.getScanner().nextLine().trim().toLowerCase();
            if (input.isEmpty()) continue;
            char c = input.charAt(0);
			if (player.getGameCharacter() == null) {
			    System.out.println("No puedes aceptar un desafío sin personaje.");
    			return;
			}

            if (c == 'a') {
                ch.acceptByPlayer();
                // Aquí ENTRAS al combate:
                context.setNextMode(new BattleMode(context, authManager, userManager, mediator, ch));
                return;
            }

			if (c == 'b') {
                ch.rejectByPlayer();
                System.out.println("Desafío rechazado.\n");
				int penalty = (int) (0.1 * ch.getBetGold());

        		Player defied = ch.getDefiedPlayer();
        		Player defying = ch.getDefyingPlayer();

		        defied.setGold(Math.max(0, defied.getGold() - penalty));
		        defying.setGold(Math.max(0, defying.getGold() - penalty));

		        userManager.save(); // persistimos el cambio de oro
                context.setNextMode(new MenuMode(new MenuScreen(context), context, authManager, userManager, mediator));
                return;
            }

            System.out.println("Opción inválida.\n");
        }
    }
}
	

