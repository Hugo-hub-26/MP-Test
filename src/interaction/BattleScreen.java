/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class BattleScreen implements Screen{
	private final Scanner scanner;
    private final List<String> battleLog;

    public BattleScreen(Scanner scanner) {
        this.scanner = scanner;
        this.battleLog = new ArrayList<>();
    }

	public void addLogEntry(String logMessage) {
        this.battleLog.add(logMessage);
    }

    public void clearLog() {
        this.battleLog.clear();
    }

	
	@Override
    public char showScreen(Set<Character> validOptions) {
        System.out.println("\n=========================================================");
        System.out.println(" ARENA DE COMBATE ");
        System.out.println("=========================================================");

        if (battleLog.isEmpty()) {
            System.out.println(" [ Los contendientes se preparan... El combate va a comenzar ]");
        } else {
            for (String log : battleLog) {
                System.out.println(" " + log);
            }
        }

		System.out.println("=========================================================\n");

        // UX: pausa para leer el resultado
        System.out.println("Pulsa ENTER para volver...");
        scanner.nextLine();

        // devolvemos una opción válida (por ejemplo 'g' volver)
        // Si 'g' no está en validOptions, devolvemos cualquiera que sí lo esté.
        if (validOptions.contains('g')) return 'g';
        if (validOptions.contains('f')) return 'f';
        return validOptions.iterator().next();
    }


}
