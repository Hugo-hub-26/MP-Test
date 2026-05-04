/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import control.UserManager;
import domain.Armor;
import domain.Discipline;
import domain.Gift;
import domain.Hunter;
import domain.HunterEditCharacter;
import domain.Lycanthrope;
import domain.LycanthropeEditCharacter;
import domain.Player;
import domain.Strength;
import domain.User;
import domain.Vampire;
import domain.VampireEditCharacter;
import domain.Weakness;
import domain.Weapons;
import domain.Will;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.e
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
                HashMap<String,Player> players = new HashMap<>();
                for(User us : um.getUsuarios().values()){
                    if (us instanceof Player pl){
                        players.put(pl.getName(), pl);
                    }    
                }
                System.out.println("Elige al usuario al que quieres editarle el personaje");
                String[] userss = showOptions(players,context.getScanner());
                election = requestNumber("Elige una opcion",0, userss.length , context.getScanner());
                if (election!=0){
                    int election1=0;
                    Player mainPlayer = players.get(userss[election-1]); 
                    if (mainPlayer.getGameCharacter() != null){
                        if (mainPlayer.getGameCharacter() instanceof Vampire vamp){
                            VampireEditCharacter vampEdit = new VampireEditCharacter(discipline,armor,weapon,strength,weakness);
                            do{
                                vampEdit.menu();
                                election1=requestNumber("Escoge",0,9,context.getScanner());
                                switch (election1){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        vampEdit.changeName(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 2:{
                                        vampEdit.changePower(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 3:{
                                        vampEdit.changeAbility(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 4:{
                                        vampEdit.changeMinion(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 5:{
                                        vampEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 6:{
                                        vampEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                    case 7:{
                                        vampEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 8:{
                                        vampEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                    case 9:{
                                        vampEdit.changeAge(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                               }
                            }while(election1!=0);
                        }else if (mainPlayer.getGameCharacter() instanceof Lycanthrope lycan){
                            LycanthropeEditCharacter lycanEdit = new LycanthropeEditCharacter(will,armor,weapon,strength,weakness);
                            do{
                                lycanEdit.menu();
                                election1=requestNumber("Escoge",0,10,context.getScanner());
                                switch (election1){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        lycanEdit.changeName(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 2:{
                                        lycanEdit.changePower(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 3:{
                                        lycanEdit.changeAbility(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 4:{
                                        lycanEdit.changeMinion(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 5:{
                                        lycanEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 6:{
                                        lycanEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                    case 7:{
                                        lycanEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 8:{
                                        lycanEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                    case 9:{
                                        lycanEdit.changeHeight(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 10:{
                                        lycanEdit.changeWeight(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;    
                                    }
                               }
                            }while(election1!=0);
                        }else if (mainPlayer.getGameCharacter() instanceof Hunter hunt){
                            HunterEditCharacter huntEdit = new HunterEditCharacter(gift,armor,weapon,strength,weakness);   
                            do{
                                huntEdit.menu();
                                election1=requestNumber("Escoge",0,8,context.getScanner());
                                switch (election1){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        huntEdit.changeName(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 2:{
                                        huntEdit.changePower(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 3:{
                                        huntEdit.changeAbility(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 4:{
                                        huntEdit.changeMinion(mainPlayer.getGameCharacter(), context.getScanner());
                                        break;
                                    }
                                    case 5:{
                                        huntEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 6:{
                                        huntEdit.changeModifier(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                    case 7:{
                                        huntEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),true);
                                        break;
                                    }
                                    case 8:{
                                        huntEdit.changePrincipalEquipment(mainPlayer.getGameCharacter(), context.getScanner(),false);
                                        break;
                                    }
                                }
                            }while(election1!=0);                        
                        }
                    } else{
                        System.out.println("Este jugador no tiene personaje para editar");
                    }
                }
                
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
    
    protected String[] showOptions(HashMap<String, Player> options, Scanner sc){
        LinkedList<String> inventary = new LinkedList<>();
        int number = 0; 
        System.out.println(number+") No editar");
        number ++;
        for (Player pl: options.values()){
            System.out.println(number + ") Se llama: " + pl.getName());
            System.out.println("La cantidad de oro es: " + pl.getGold());
            inventary.add(pl.getName());
            number ++;
        }
        return inventary.toArray(new String[0]);
    }
}
