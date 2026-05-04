
package domain;

import java.io.Serializable;

/**
 *
 * @author Hugo Martínez González
 */
public abstract class Ability implements Describable, Serializable{
	private static final long serialVersionUID = 1L;
	private final String name;
        private final String description;
	private final int attackValue;
        private final int defenseValue;

	public Ability(String name, String description, int attack, int defense){
		this.name = name;
		attackValue = attack;
                defenseValue = defense;
                this.description = description;
	}

	public String getName() {
		return name;
	}

        public String getDescription(){
            return description;
        }
        
	public int getAttackValue() {
		return attackValue;
	}	
        
        public int getDefenseValue() {
		return defenseValue;
	}
}
