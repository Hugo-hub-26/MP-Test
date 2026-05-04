/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;
import control.AuthenticationManager;
import control.GameContext;
import control.MenuMode;
import control.UserManager;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.Player;
import interaction.MenuScreen;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */

public class RejectChallengeCommand implements Command {

    private final GameContext context;
    private final UserManager userManager;
    private final ChallengeMediator mediator;
    private final AuthenticationManager authManager;
    private final Challenge challenge;

    public RejectChallengeCommand(GameContext context,
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

        if (!(context.getCurrentUser() instanceof Player)) {
            System.out.println("Solo un jugador puede rechazar un desafío.\n");
            return;
        }

        // 1) Cambiar estado a REJECTED_BY_PLAYER 
        challenge.rejectByPlayer();

        // 2) Penalización (10% de la apuesta) — aquí sí
        int penalty = (int) (0.1 * challenge.getBetGold());

        Player defied = challenge.getDefiedPlayer();
        Player defying = challenge.getDefyingPlayer();

        defied.setGold(Math.max(0, defied.getGold() - penalty));
        defying.setGold(Math.max(0, defying.getGold() - penalty));

        userManager.save(); // persistimos el cambio de oro

        System.out.println("Desafío rechazado por el jugador.");
        System.out.println("Penalización aplicada: -" + penalty + " oro.\n");

        // 3) Volver al menú normal
        context.setNextMode(new MenuMode(
                new MenuScreen(context),
                context,
                authManager,
                userManager,
                mediator
        ));
    }
}