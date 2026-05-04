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
import domain.User;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class SendChallengeCommand implements Command{

	private final GameContext context;
    private final UserManager userManager;
    private final ChallengeMediator mediator;

    public SendChallengeCommand(GameContext context, UserManager userManager, ChallengeMediator mediator) {
        this.context = context;
        this.userManager = userManager;
        this.mediator = mediator;
    }

	@Override
	public void execute() {
		
		if (!(context.getCurrentUser() instanceof Player defying)) {
            System.out.println("Solo un jugador puede lanzar desafios.\n");
            return;
        }

        System.out.println("Nick del rival:");
        String rivalNick = context.getScanner().nextLine().trim();

        System.out.println("Oro a apostar:");
        int bet;
		
		try {
            bet = Integer.parseInt(context.getScanner().nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad invalida.\n");
            return;
        }

        if (bet <= 0 || bet > defying.getGold()) {
            System.out.println("Apuesta invalida (debe ser >0 y <= tu oro).\n");
            return;
        }

        User u = userManager.findByNick(rivalNick);
        if (!(u instanceof Player)) {
            System.out.println("El jugador rival no existe.\n");
            return;
        }

		Player defied = (Player) u;
		if (!defied.canBeChallenged()) {
			System.out.println("No se puede desafiar a este jugador: ha participado en un desafío en las últimas 24 horas.\n");
		    return;
		}
		
		Challenge ch = new Challenge(defying, defied, bet); // estado PENDING_ADMIN_VALIDATION
        mediator.registerChallenge(ch);

        System.out.println("Desafío enviado. Queda pendiente de validación por un operador.\n");


	}
	
}
