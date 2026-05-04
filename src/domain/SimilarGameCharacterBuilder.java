
package domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Hugo MArtínez González
 */
public abstract class SimilarGameCharacterBuilder implements GameCharacterBuilder {
    
        protected HashMap<String,Ability> ability;
        private HashMap<String,Armor> armor; 
        private HashMap<String,Weapons> weapon; 
        private HashMap<String,Strength> strength; 
        private HashMap<String,Weakness> weakness; 

    public SimilarGameCharacterBuilder(HashMap<String, ? extends Ability> ability, HashMap<String, Armor> armor, HashMap<String, Weapons> weapon, HashMap<String,Strength> strength, HashMap<String,Weakness> weakness) {
        this.ability = (HashMap<String, Ability>) ability;        
        this.armor = armor;
        this.weapon = weapon;
        this.strength = strength;
        this.weakness = weakness;
    }
    
        @Override
    public GameCharacter gameCharacterBuilder(GameCharacter characterr){
        Scanner sc = new Scanner(System.in);
        setName(characterr,sc);
        setPower(characterr,sc);
        characterr.setHealth(5);
        setAbility(characterr,sc);
        setMinion(characterr,sc,null);
        setModifier(characterr,sc,true);
        setModifier(characterr,sc,false);
        characterr.setArmor(armor);
        characterr.setWeapon(weapon);
        setPrincipalEquipment(characterr,sc,true);
        setPrincipalEquipment(characterr,sc,false);
        return characterr;
    }
    
    private void setName(GameCharacter characterr, Scanner sc){
        characterr.setName(requestString("Elige el nombre de tu personaje",sc));
    }
    
    private void setPower(GameCharacter characterr, Scanner sc) { 
        characterr.setPower(requestNumber("Elige el poder de tu personaje, debe estar entre 1 y 5: ",1,5,sc));
    }
    
    protected void setAbility(GameCharacter characterr, Scanner sc){
        System.out.println("Escoge la habilidad especial de tu personaje:");
        String[] inventary = showOptions(ability,sc,false,"habilidad");
        String name = inventary[requestNumber("Escoge la habilidad de tu personaje pulsando el numero", 0, inventary.length-1, sc)];
        characterr.setAbility(ability.get(name)); 
    }
    
    protected void setMinion(GameCharacter characterr, Scanner sc, Demon demon){//revisar la logica muy bien
        String message;
        if (demon== null){
            message = "Elige el esbirro que quiere que tenga tu personaje:\n 0) Ninguno\n1) Demonio \n 2) Ghoul \n 3) Humano";
        }else message = "Elige los esbirros que quiere que tenga tu demonio:\n 0) Ninguno\n1) Demonio \n 2) Ghoul \n 3) Humano";
        switch (requestNumber(message,0,3,sc)) {
            case 0:{
                if (demon==null){
                    characterr.setMinion(null);
                }
                break;
            }
            case 1: {
                String name = requestString("Elige el nombre del esbirro", sc);
                int health = requestNumber("Elige la salud del esbirro",1,3,sc);
                String pact = requestString("Describe la dependencia del esbirro",sc);
                if (demon==null){
                    Demon demonion = new Demon(name,health,characterr,pact,new LinkedList<>());
                    int size;
                    do{
                        size = demonion.getMinions().size();
                        setMinion(characterr,sc,demonion);
                    } while(size!=demonion.getMinions().size());                    characterr.setMinion(demonion);
                }else{
                    Demon demonion = new Demon(name,health,characterr,pact,new LinkedList<>());
                    int size;
                    do{
                        size = demonion.getMinions().size();
                        setMinion(characterr,sc,demonion);
                    } while(size!=demonion.getMinions().size());
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
            case 3:{
                String name = requestString("Elige el nombre del esbirro", sc);
                int health = requestNumber("Elige la salud del esbirro",1,3,sc);
                String[] loyalty = {"ALTA", "MEDIA", "BAJA"};
                String loyal = loyalty[requestNumber("Elige la lealtad del esbirro \n0)ALTA \n1)MEDIA \n2) BAJA", 0,2,sc)];
                if (demon == null){
                    characterr.setMinion(new Human(name,health, loyal ,characterr));
                }else demon.addMinion(new Human(name,health,loyal ,characterr));
                    break;
            }                
        }
    }
    
    private void setModifier(GameCharacter characterr,Scanner sc, boolean mode){
        int election = 0;
        String[] inventary;
        HashMap<String, Modifier> modifierChoosen= new HashMap<>();
        HashMap<String,Modifier> cop;
        String messagee;
        if(mode){
            messagee=("Elige un conjunto de fortalezas");
            cop = new HashMap<>(strength);
        }else{
            messagee=("Elige un conjunto de debilidades");
            cop = new HashMap<>(weakness);
        }
        do{
            System.out.println(messagee);
            inventary = showOptions(cop,sc,true,"modificador");
            election = requestNumber("Selecciona:",0,inventary.length,sc);
            if (election == 0){
                if (mode) {
                    HashMap<String, Strength> finalStrengths = new HashMap<>();
                    for (Modifier mod : modifierChoosen.values()) {
                        finalStrengths.put(mod.getName(), (Strength) mod); 
                    }
                characterr.setStrength(finalStrengths);
                } else {
                    HashMap<String, Weakness> finalWeaknesses = new HashMap<>();
                    for (Modifier mod : modifierChoosen.values()) {
                        finalWeaknesses.put(mod.getName(), (Weakness) mod); 
                    }
                    characterr.setWeakness(finalWeaknesses);
                }
            } else{
               String name = inventary[election-1];
               modifierChoosen.put(name, cop.get(name));
               cop.remove(name);
            }
        }while (election!=0);    
    }
    
    public void setPrincipalEquipment(GameCharacter characterr, Scanner sc, boolean mode){
        String[] inventary;
        HashMap<String, Equipment> equipChosen;
        if (mode){
            System.out.println("Elige una armadura principal");
            equipChosen = new HashMap<>(armor);
        }else{
            System.out.println("Elige un arma principal");
            equipChosen = new HashMap<>(weapon);            
        }    
        inventary = showOptions(equipChosen,sc,false,"equipamiento");
        String name = inventary[requestNumber("Escoge:", 0, inventary.length-1, sc)];
        if (mode){
            characterr.setPrincipalArmor((Armor) equipChosen.get(name));
        }else{
            Weapons weap = (Weapons) equipChosen.get(name);
            Weapons noOne = new Weapons("Ninguna", "Ninguna,", 0, 0, false);
            if (weap.getHandNumber()){
                characterr.setPrincipalWeaponOne(weap);
                characterr.setPrincipalWeaponTwo(noOne);
            }else{
                characterr.setPrincipalWeaponOne(weap);
                HashMap<String,Weapons> oneHandWeapon = new HashMap<>();
                for (Equipment equip: equipChosen.values()){
                    oneHandWeapon.put(equip.getName(), (Weapons) equip);    
                }  
                HashMap<String,Weapons> ohw = new HashMap<>();
                for (Weapons weaps: oneHandWeapon.values()){
                    if (!weaps.getHandNumber()){
                        ohw.put(weaps.getName(), weaps);
                    }
                }
                inventary = showOptions(ohw,sc,true,"equipamiento");
                int position = requestNumber("Escoge:", 0, inventary.length, sc);
                if (position ==0){
                    characterr.setPrincipalWeaponTwo(noOne);
                }else{    
                    name = inventary[position-1];
                    characterr.setPrincipalWeaponTwo(ohw.get(name));
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
    
