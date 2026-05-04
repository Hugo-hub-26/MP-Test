/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Hugo Martínez González
 */
public class VampireEditCharacter extends EditGameCharacter {
    
    public VampireEditCharacter(HashMap<String, ? extends Ability> ability, HashMap<String, Armor> armor, HashMap<String, Weapons> weapon, HashMap<String, Strength> strength, HashMap<String, Weakness> weakness) {
        super(ability, armor, weapon, strength, weakness);
    }
    
    public void changeAge(GameCharacter characterr, Scanner sc){
        Vampire vamp = (Vampire) characterr;
        vamp.setAge(requestNumber("Introduce la nueva edad del vampiro", 30, 10000, sc));
    }
    
}
