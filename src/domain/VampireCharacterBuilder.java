package domain;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Constructor específico (Builder) para la raza Vampiro.
 * Recopila la información paso a paso y ensambla el objeto final.
 * * @author Miguel Pradillo Bartolomé
 */

public class VampireCharacterBuilder extends SimilarGameCharacterBuilder {
    private int age;

    public VampireCharacterBuilder(HashMap<String, ? extends Ability> ability, HashMap<String, Armor> armor, HashMap<String, Weapons> weapon, HashMap<String, Strength> strength, HashMap<String, Weakness> weakness) {
        super(ability, armor, weapon, strength, weakness);
    }

    @Override
    public Vampire gameCharacterBuilder(GameCharacter characterr){
        Vampire vamp =(Vampire) super.gameCharacterBuilder(characterr);
        Scanner sc = new Scanner(System.in);
        int age = requestNumber("Introduce la edad del vampiro", 30, 10000, sc);
        vamp.setAge(age);
        return vamp;
    }
    
    
    @Override
    protected void setAbility(GameCharacter characterr, Scanner sc){
        System.out.println("Escoge la habilidad especial de tu personaje:");
        String[] inventary = showOptions(ability,sc,false,"habilidad");
        String name = inventary[requestNumber("Escoge la habilidad de tu personaje pulsando el número", 0, inventary.length-1, sc)];
        Discipline disc = (Discipline) ability.get(name);
        characterr.setAbility(disc); 
    }
    
        
        @Override
        public void setMinion(GameCharacter characterr, Scanner sc, Demon demon){//revisar la logica muy bien
        String message;
        if (demon== null){
            message = "Elige el esbirro que quiere que tenga tu personaje:\n 0) Ninguno\n1) Demonio \n 2) Ghoul";
        }else message = "Elige el esbirro que quiere que tenga tu demonio:\n 0) Ninguno\n1) Demonio \n 2) Ghoul";
        switch (requestNumber(message,0,2,sc)) {
            case 0:{
                if (demon==null){
                    characterr.setMinion(null);
                }
                break;
            }
            case 1: {
                String name = requestString("Elige el nombre del esbirro", sc);
                int health = requestNumber("Elige la salud del esbirro",1,3,sc);
                String pact = requestString("Describe la depndencia del esbirro",sc);
                if (demon==null){
                    Demon demonion = new Demon(name,health,characterr,pact,null);
                    setMinion(characterr,sc,demonion);
                    characterr.setMinion(demonion);
                }else{
                    Demon demonion = new Demon(name,health,characterr,pact,null);
                    setMinion(characterr,sc,demonion);
                    demon.addMinion(demonion);
                }
                break;
            }    
            case 2:{
                String name = requestString("Elige el nombre del esbirro", sc);
                int health = requestNumber("Elige la salud del esbirro",1,3,sc);
                int dependence = requestNumber("Elige la dependencia del esbirro", 1,5,sc);
                if (demon == null){
                    characterr.setMinion(new Ghoul(name,health, characterr,dependence));
                }else demon.addMinion(new Ghoul(name,health, characterr,dependence));
                break;
            }                
        }
    }
}