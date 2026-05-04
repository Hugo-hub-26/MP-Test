/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import domain.Armor;
import domain.Describable;
import domain.Discipline;
import domain.Gift;
import domain.Player;
import domain.Strength;
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
    private HashMap<String,Discipline> discipline;
    private HashMap<String,Gift> gift;
    private HashMap<String,Will> will;
    private HashMap<String,Armor> armor; 
    private HashMap<String,Weapons> weapon; 
    private HashMap<String,Strength> strength; 
    private HashMap<String,Weakness> weakness; 

    public EditCharacterCommand(GameContext context) {
        this.context = context;
        this.discipline = context.getCatalog().getDiscipline();
        this.gift = context.getCatalog().getGift();
        this.will = context.getCatalog().getWill();
        this.armor = context.getCatalog().getArmor();
        this.weapon = context.getCatalog().getWeapon();
        this.strength = context.getCatalog().getStrength();
        this.weakness = context.getCatalog().getWeakness();
    }
	
    @Override
	public void execute() {
            int election=0;
            if (context.getCurrentUser() instanceof Player pl){
                do{
                    System.out.println("Elige que quieres cambiar");
                    System.out.println("0) No cambiar nada");
                    System.out.println("1) La armadura");
                    System.out.println("2) Las armas");
                    election = requestNumber("Escoge",0,2,context.getScanner());        
                    if (election==1){
                        
                    }
                }while (election!=0);
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
    
    protected String[] showOptions(HashMap<String, ? extends Describable> options, Scanner sc, boolean mode, String message){
        LinkedList<String> inventary = new LinkedList<>();
        int number = 0; 
        if (mode){
            System.out.println(number+") Dejar de elegir");
            number ++;
        }
        for (Describable desc: options.values()){
            System.out.println(number + ") Se llama: " + desc.getName());
            System.out.println("La descripcion del "+ message + ": " + desc.getDescription());
            inventary.add(desc.getName());
            number ++;
        }
        
        return inventary.toArray(new String[0]);
    }
}
