/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.BattleMode;
import control.GameContext;
import control.UserManager;
import domain.Challenge;
import domain.ChallengeMediator;
import domain.HunterEditCharacter;
import domain.Player;
import java.util.List;
import java.util.Scanner;

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
                System.out.println("¿Quieres editar las armas y armaduras principales de tu personaje?");
                System.out.println("0) No");
                System.out.println("1) Si");
                int election = requestNumber("Escoge",0,1,context.getScanner());
                if (election==1){
                    HunterEditCharacter editor = new HunterEditCharacter(context.getCatalog().getGift(),context.getCatalog().getArmor(), context.getCatalog().getWeapon(),context.getCatalog().getStrength(),context.getCatalog().getWeakness());
                    int election1;
                    do{
                            System.out.println("¿Que quieres editar?");
                            System.out.println("0) No hacer nada");
                            System.out.println("1) Armaduras");                        
                            System.out.println("2) Armas");           
                            election1 = requestNumber("Escoge",0,2,context.getScanner());
                            if (election1==1){
                                editor.changePrincipalEquipment(context.getCharacter2(), context.getScanner(), true);
                            } else if (election1==2){
                                editor.changePrincipalEquipment(context.getCharacter2(), context.getScanner(), false);
                            }                        
                    }while(election1!=0);
                }

                // Aquí ENTRAS al combate:
                context.setNextMode(new BattleMode(context, authManager, userManager, mediator, ch));
                return;
            }

			if (c == 'b') {
                ch.rejectByPlayer();
                System.out.println("Desafío rechazado.\n");
                return;
            }

            System.out.println("Opción inválida.\n");
        }
    }
    protected int requestNumber(String message, int min, int max, Scanner sc){
        int number =0;
        boolean proof;
        do {
            proof =false;
            try {
                System.out.println(message + "(" + min + "-" + max + ")");
                number = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un numero valido.");
                proof = true;
            }
        } while (number < min || number > max || proof); 
        return number;               
    }
}
	

