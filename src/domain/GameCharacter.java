
package domain;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public abstract class GameCharacter implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;	
	private int health;
	private int power;
  	private Ability ability;
  	private HashMap<String,Armor> armor;
  	private HashMap<String,Weapons> weapon;       
  	private Minion minion;
   	private HashMap<String, Strength> strength;
   	private HashMap<String, Weakness> weakness;
   	private Armor principalArmor;
   	private Weapons principalWeaponOne;
   	private Weapons principalWeaponTwo;

    public GameCharacter() {
   	}

   	public void setName(String name) {
   		this.name = name;
   	}

	public void setHealth(int health) {
   		this.health = health;
    }
        
	public String getName() {
		return name;
	}

	public int getHealth() {
        return health;
    }

	public int getPower() {
		return power;
	}

    public Ability getAbility() {
        return ability;
    }

    public Armor getPrincipalArmor() {
        return principalArmor;
    }

    public Weapons getPrincipalWeaponOne() {
        return principalWeaponOne;
    }

    public Weapons getPrincipalWeaponTwo() {
        return principalWeaponTwo;
    }

    public HashMap<String, Armor> getArmor() {
        return armor;
    }

    public HashMap<String, Weapons> getWeapon() {
        return weapon;
    }

    public Minion getMinion() {
        return minion;
    }
    
    public HashMap<String, Strength> getStrength() {
        return strength;
    }

    public HashMap<String, Weakness> getWeakness() {
        return weakness;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void setArmor(HashMap<String, Armor> armor) {
        this.armor = armor;
    }

    public void setWeapon(HashMap<String, Weapons> weapon) {
        this.weapon = weapon;
    }

    public void setMinion(Minion minion) {
        this.minion = minion;
    }

    public void setStrength(HashMap<String, Strength> strength) {
        this.strength = strength;
    }

    public void setWeakness(HashMap<String, Weakness> weakness) {
        this.weakness = weakness;
    }

    public void setPrincipalArmor(Armor principalArmor) {
        this.principalArmor = principalArmor;
    }

    public void setPrincipalWeaponOne(Weapons principalWeaponOne) {
        this.principalWeaponOne = principalWeaponOne;
    }

    public void setPrincipalWeaponTwo(Weapons principalWeaponTwo) {
        this.principalWeaponTwo = principalWeaponTwo;
    }
  
    public void setPower(int power){
        this.power = power;
    }

    public int getMinionHealth() {
        if (minion != null){
            return minion.getHealth();
        }else return 0;
    }
}

