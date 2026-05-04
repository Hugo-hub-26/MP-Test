/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import command.Command;
import command.ExitBattleCommand;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.Player;
import interaction.BattleScreen;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class BattleMode implements Mode {
	
	private final GameContext context;
    private final AuthenticationManager authManager;
    private final UserManager userManager;
    private final ChallengeMediator mediator;

    private final Challenge challenge;
    private final BattleScreen screen;

    private Map<Character, Command> commands;
    private boolean resolved = false;

	
	public BattleMode(GameContext context,
                      AuthenticationManager authManager,
                      UserManager userManager,
                      ChallengeMediator mediator,
                      Challenge challenge) {

        this.context = context;
        this.authManager = authManager;
        this.userManager = userManager;
        this.mediator = mediator;

        this.challenge = challenge;
        this.screen = new BattleScreen(context.getScanner());

        initCommands();
    }


	private void initCommands() {
        commands = new HashMap<>();
        commands.put('g', new ExitBattleCommand(context, authManager, userManager, mediator));
    }

	@Override
	public Mode showScreen() {
		
		// Resolvemos el combate una sola vez
        if (!resolved) {
            resolveCombat();
            resolved = true;
        }

        char option = screen.showScreen(commands.keySet());
        return doAction(option);

	}

	@Override
	public Mode doAction(char option) {
		
		Command cmd = commands.get(option);
        if (cmd == null) throw new IllegalStateException("Opción inválida en combate: " + option);

        cmd.execute();
        return context.getNextMode();

	}

	
private void resolveCombat() {

        // Los getters que has usado en tu Challenge son estos:
        Player p1 = challenge.getDefyingPlayer();
        Player p2 = challenge.getDefiedPlayer();
        int bet = challenge.getBetGold();

        screen.clearLog();
        screen.addLogEntry("Combate entre " + p1.getNick() + " y " + p2.getNick());
        screen.addLogEntry("Apuesta: " + bet + " oro");
        screen.addLogEntry("");

        boolean p1Wins = Math.random() < 0.5;

		if (p1Wins) {
            screen.addLogEntry("Ganador: " + p1.getNick());
            p1.setGold(p1.getGold() + bet);
            p2.setGold(Math.max(0, p2.getGold() - bet));
        } else {
            screen.addLogEntry("Ganador: " + p2.getNick());
            p2.setGold(p2.getGold() + bet);
            p1.setGold(Math.max(0, p1.getGold() - bet));
        }

        screen.addLogEntry("");
        screen.addLogEntry("Resultado aplicado a oro. Fin del combate.");

        // Estado del desafío
        challenge.finish();

        // Persistencia
        userManager.save();
    }
}
