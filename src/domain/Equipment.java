/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public abstract class Equipment implements Describable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private final String name;
        private final String description;
	private final int attackModifier;
	private final int defenseModifier;

	public Equipment(String n,String descript, int am, int dm){
		name = n;
		attackModifier = am; //se suma
		defenseModifier = dm; //se suma
                description = descript;
	}

        @Override
	public String getName() {
		return name;
	}

	public int getAttackModifier() {
		return attackModifier;
	}

	public int getDefenseModifier() {
		return defenseModifier;
	}

        @Override
        public String getDescription() {
            return description;
        }	
}
