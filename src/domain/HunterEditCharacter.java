/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.HashMap;

/**
 *
 * @author Hugo Martínez González
 */
public class HunterEditCharacter extends EditGameCharacter{
    
    public HunterEditCharacter(HashMap<String, ? extends Ability> ability, HashMap<String, Armor> armor, HashMap<String, Weapons> weapon, HashMap<String, Strength> strength, HashMap<String, Weakness> weakness) {
        super(ability, armor, weapon, strength, weakness);
    }
    
}
