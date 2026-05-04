/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Hugo Martínez González
 */
public class Catalogue {
	private final HashMap<String,Discipline> disciplineMap = new HashMap<>();
	private final HashMap<String,Gift> giftMap = new HashMap<>();
	private final HashMap<String,Will> willMap = new HashMap<>();
	private final HashMap<String,Armor> armorMap = new HashMap<>(); 
	private final HashMap<String,Weapons> weaponMap = new HashMap<>(); 
	private final HashMap<String,Strength> strengthMap = new HashMap<>(); 
	private final HashMap<String,Weakness> weaknessMap = new HashMap<>(); 

	public Catalogue() throws FileNotFoundException{
		load();
	}

	private void load() throws FileNotFoundException{
		String route = "./data/catalogue.csv";
		/*
		String[] routes = {"./data/discipline.csv", "./data/gift.csv", 
			"./data/will.csv", "./data/armor.csv", "./data/weapon.csv",
			"./data/strength.csv", "./data/weakness.csv"};
                // o tambien se puede hacer con un file solo y asi todo en un cvs, yo podrondira esto
                //Yo pondria todo aqui, es decir coger el otro metodo y ponerlo aqui con un solo archivo en plan
                //y cambiar el string[] routes y el for por el otro metodo y con un fileName solo en pla:
                //"./data/conjuntoDePropiedades.csv"
                
		//Bucle de carga de los HashMap
		for (String fileName: routes){
                    loadFile(fileName);
		}
		*/
		loadFile(route);
	}
	
    public HashMap<String, Discipline> getDiscipline() {
        return disciplineMap;
    }

    public HashMap<String, Gift> getGift() {
        return giftMap;
    }

    public HashMap<String, Will> getWill() {
        return willMap;
    }

    public HashMap<String, Armor> getArmor() {
        return armorMap;
    }

    public HashMap<String, Weapons> getWeapon() {
        return weaponMap;
    }

    public HashMap<String, Strength> getStrength() {
        return strengthMap;
    }

    public HashMap<String, Weakness> getWeakness() {
        return weaknessMap;
    }

    private void loadFile(String fileName) throws FileNotFoundException{ 
        String[] properties = new String[6];
        try (Scanner sc = new Scanner(new FileInputStream(fileName))){ 
            sc.nextLine(); 
            while (sc.hasNextLine()){ 
                properties = sc.nextLine().replace("\"", "").split(",");     
                switch (properties[0].toLowerCase()){
                    case "discipline":{
                        disciplineMap.put(properties[1], new Discipline(properties[1],properties[2],transform(properties[3]), transform(properties[4]), transform(properties[5])));
                        break;
                    }
                    case "gift":{
                      giftMap.put(properties[1], new Gift(properties[1],properties[2],transform(properties[3]), transform(properties[4])));
                      break;  
                    }
                    case "will":{
                        willMap.put(properties[1], new Will(properties[1],properties[2],transform(properties[3]), transform(properties[4]), transform(properties[5])));
                        break;
                    }
                    case "armor":{
                        armorMap.put(properties[1], new Armor(properties[1],properties[2],transform(properties[3]), transform(properties[4])));
                        break;
                    }
                    case "weapon":{
                        weaponMap.put(properties[1], new Weapons(properties[1],properties[2],transform(properties[3]), transform(properties[4]), Boolean.parseBoolean(properties[5])));
                        break;
                    }
                    case "strength":{
                        strengthMap.put(properties[1], new Strength(properties[1],properties[2],transform(properties[3]), transform(properties[4])));
                        break;
                    }
                    case "weakness":{
                        weaknessMap.put(properties[1], new Weakness(properties[1],properties[2],transform(properties[3]), transform(properties[4])));
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) { 
            System.out.println("Error: No se pudo encontrar el archivo: " + e.getMessage()); 
        } catch (Exception e) { 
            System.out.println("Ocurrió un error inesperado: " + e.getMessage()); 
        } 
    }
    
    private int transform(String word){
        int number =0;
        try {
            number = Integer.parseInt(word);
        } catch (NumberFormatException e) {
            System.out.println("Error: Fichero del programa dañado");
        }
        return number;
    }
}
