/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import control.UserManager;
import domain.Player;
import domain.User;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class ManageUsersCommand implements Command {
	
	private final GameContext context;
    private final UserManager userManager;

    public ManageUsersCommand(GameContext context, UserManager userManager) {
        this.context = context;
        this.userManager = userManager;
    }

	@Override
	public void execute() {
		
System.out.println("""
[Gestión de usuarios]
a) Bloquear usuario
b) Desbloquear usuario
""");

        System.out.print("Opcion:");
        char op = context.getScanner().nextLine().trim().toLowerCase().charAt(0);

        System.out.println("Nick del usuario:");
        String nick = context.getScanner().nextLine().trim();

		User u = userManager.findByNick(nick);
        if (!(u instanceof Player p)) {
            System.out.println("No existe ese jugador.\n");
            return;
        }

		switch (op) {
			case 'a':
				p.block();          // <-- añade este método a Player
				userManager.save();
				System.out.println("Jugador bloqueado.\n");
				break;
			case 'b':
				p.unblock();        // <-- añade este método a Player
				userManager.save();
				System.out.println("Jugador desbloqueado.\n");
				break;
			default:
				System.out.println("Opción inválida.\n");
				break;
		}
	}
	
}
