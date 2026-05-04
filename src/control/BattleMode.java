/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import command.Command;
import command.ExitBattleCommand;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.GameCharacter;
import domain.Player;
import domain.StatsCalculator;
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

    Player p1 = challenge.getDefyingPlayer();
    Player p2 = challenge.getDefiedPlayer();
    int bet = challenge.getBetGold();

	if (p1.getGameCharacter() == null || p2.getGameCharacter() == null) {
		screen.clearLog();
		screen.addLogEntry("Error: uno de los jugadores no tiene personaje.");
		screen.addLogEntry("El combate no puede realizarse.");
		challenge.finish(); // o challenge.cancel(), según tu modelo
		userManager.save();
		return;
	}


    // Log inicial
    screen.clearLog();
    screen.addLogEntry("Combate entre " + p1.getNick() + " y " + p2.getNick());
    screen.addLogEntry("Apuesta: " + bet + " oro");
    screen.addLogEntry("");

    // 1) Preparar contexto de combate con los personajes
    context.setCharacter1(p1.getGameCharacter());
	context.setCharacter2(p2.getGameCharacter());

    // 2) Ejecutar combate automático
    StatsCalculator calc = new StatsCalculator(challenge.getCombatType()); // o el type que uses
    GameContext result = calc.battle(context);

    // 3) Aplicar timer 24h (mejor aquí: “ha participado en combate”)
    p1.markChallengeTime();
    p2.markChallengeTime();

    // 4) Interpretar resultado
    if (result.getDraw()) {
        screen.addLogEntry("Resultado: EMPATE");
        // Política de oro en empate (elige una):
        // a) no cambia oro
        // b) se devuelve la apuesta (si la retenías)
        // Aquí lo dejo como “sin cambios”
    } else {

        GameCharacter winnerChar = result.getCharacter1();
        GameCharacter loserChar  = result.getCharacter2();

        // Mapeo ganador -> Player (robusto si el objeto es el mismo)
        Player winnerPlayer;
        Player loserPlayer;

        if (winnerChar == p1.getGameCharacter()) {
            winnerPlayer = p1;
            loserPlayer = p2;
        } else if (winnerChar == p2.getGameCharacter()) {
            winnerPlayer = p2;
            loserPlayer = p1;
        } else {
            // Fallback por nombre (por si el battle() devuelve nuevas instancias)
            if (winnerChar.getName().equals(p1.getGameCharacter().getName())) {
                winnerPlayer = p1;
                loserPlayer = p2;
            } else {
                winnerPlayer = p2;
                loserPlayer = p1;
            }
        }

        screen.addLogEntry("Ganador: " + winnerPlayer.getNick());

        // Reparto de oro (tu regla actual)
        winnerPlayer.setGold(winnerPlayer.getGold() + bet);
        loserPlayer.setGold(Math.max(0, loserPlayer.getGold() - bet));
    }

    screen.addLogEntry("");
    screen.addLogEntry("Resultado aplicado a oro. Fin del combate.");

    // 5) Estado del desafío
    challenge.finish();

    // 6) Persistencia
    
    userManager.save();
	challenge.finish();
        
	}

    private void saveBattleToHistory(Player p1, Player p2, Player winner, int gold, GameContext result) {
    try (java.io.FileWriter fw = new java.io.FileWriter("data/combat_history.txt", true);
         java.io.PrintWriter out = new java.io.PrintWriter(fw)) {
        
        out.println("--- REGISTRO DE COMBATE ---");
        out.println("Fecha: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        out.println("Desafiante: " + p1.getNick());
        out.println("Desafiado: " + p2.getNick());
        out.println("Vencedor: " + (winner != null ? winner.getNick() : "EMPATE"));
        out.println("Oro Ganado: " + gold);
        
        // Asumiendo que result tiene el número de rondas
        out.println("Rondas empleadas: " + result.getRounds()); 

        // Lógica de esbirros vivos
        StringBuilder esbirrosVivos = new StringBuilder("Contendientes con esbirros vivos: ");
        boolean algunoVivo = false;
        
        if (hasAliveMinions(p1.getGameCharacter())) {
            esbirrosVivos.append(p1.getNick()).append(" ");
            algunoVivo = true;
        }
        if (hasAliveMinions(p2.getGameCharacter())) {
            esbirrosVivos.append(p2.getNick());
            algunoVivo = true;
        }
        
        out.println(algunoVivo ? esbirrosVivos.toString() : "Ningún esbirro quedó en pie.");
        out.println("---------------------------\n");
        
    } catch (java.io.IOException e) {
        System.err.println("Error al guardar el historial de combate: " + e.getMessage());
    }
}
}
