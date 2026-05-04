/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import control.GameContext;
import domain.Armor;
import domain.Discipline;
import domain.Gift;
import domain.Strength;
import domain.Weakness;
import domain.Weapons;
import domain.Will;
import java.util.HashMap;

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
                        
        
        
        }
}
