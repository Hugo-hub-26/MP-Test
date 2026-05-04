/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import control.UserManager;
import domain.Armor;
import domain.Describable;
import domain.Discipline;
import domain.Gift;
import domain.HunterEditCharacter;
import domain.Player;
import domain.Strength;
import domain.User;
import domain.Weakness;
import domain.Weapons;
import domain.Will;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class EditCharacterCommand implements Command{
    private final GameContext context;
    private final UserManager um;
    private HashMap<String,Discipline> discipline;
    private HashMap<String,Gift> gift;
    private HashMap<String,Will> will;
    private HashMap<String,Armor> armor; 
    private HashMap<String,Weapons> weapon; 
    private HashMap<String,Strength> strength; 
    private HashMap<String,Weakness> weakness; 

    public EditCharacterCommand(GameContext context, UserManager um) {
        this.context = context;
        this.discipline = context.getCatalog().getDiscipline();
        this.gift = context.getCatalog().getGift();
        this.will = context.getCatalog().getWill();
        this.armor = context.getCatalog().getArmor();
        this.weapon = context.getCatalog().getWeapon();
        this.strength = context.getCatalog().getStrength();
        this.weakness = context.getCatalog().getWeakness();
        this.um = um;
    }
	
    @Override
	public void execute() {
            int election=0;
            if (context.getCurrentUser() instanceof Player pl){
                if(pl.getGameCharacter()!=null){
                    do{
                        System.out.println("Elige que quieres cambiar");
                        System.out.println("0) No cambiar nada");
                        System.out.println("1) La armadura");
                        System.out.println("2) Las armas");
                        election = requestNumber("Escoge",0,2,context.getScanner());  
                        HunterEditCharacter hed = new HunterEditCharacter(gift, armor, weapon, strength, weakness);
                        if (election==1){
                            hed.changePrincipalEquipment(pl.getGameCharacter(), context.getScanner(), true);
                        }else{
                            hed.changePrincipalEquipment(pl.getGameCharacter(), context.getScanner(), false);  
                        }
                    }while (election!=0);
                }else{
                    System.out.println("No tienes un personaje para editar");
                }
            } else{
                System.out.println("Elige al usuario al que quieres editarle el personaje");
                String[] userss = showOptions(um.getUsuarios());
                
                
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
        
    protected String requestString(String message, Scanner sc){
        String name;
        do{
            System.out.println(message);
            name = sc.nextLine();
        }while(name.isBlank());
        return name;
    }
    
    protected String[] showOptions(HashMap<String, User> options, Scanner sc){
        LinkedList<String> inventary = new LinkedList<>();
        int number = 0; 
        System.out.println(number+") Dejar de elegir");
        number ++;
        for (User us: options.values()){
            System.out.println(number + ") Se llama: " + us.getName());
            System.out.println("La cantidad de oro es: " + );
            inventary.add(desc.getName());
            number ++;
        }
        
        return inventary.toArray(new String[0]);
    }
}
