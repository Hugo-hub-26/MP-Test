/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Iterator; // Add necessary imports

/**
 * Algoritmo de defensa. Implementa el patrón
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class DefenceAlgorithm implements Algorithm {
private int type; 
    @Override
    public int execute(GameCharacter c) {

        int def = c.getPower()+c.getAbility().getDefenseValue()+
        c.getPrincipalArmor().getDefenseModifier();

            if (c instanceof Hunter) {

                def+=((Hunter) c).getAttitude();

            } else if (c instanceof Lycanthrope) {
                Lycanthrope lycan = (Lycanthrope) c;
                if (lycan.getRage() >= lycan.getAbility().getRageValue()) {
                    def += lycan.getAbility().getDefenseValue();
                }
                def += lycan.getRage();

            } else if (c instanceof Vampire) {
                if (((Vampire) c).getBloodPoints()>=5){ 
                    def+=2;
                }
            }

            //values accede a la columa derecha de la lista, 
            //el iterador no puede iterar respecto a una lista de más
            //de una columna.
        Iterator<Strength> it = c.getStrength().values().iterator();
        while (it.hasNext()) {
            // def+=((Strength) it.next()).getValue();
            if (it.next().getType()==type){
            def+=it.next().getValue();
            }
        }
        Iterator<Weakness> it1 = c.getWeakness().values().iterator();
        while (it1.hasNext()) {
            // def+=((Strength) it.next()).getValue();
            if (it1.next().getType()==type){
            def-=it1.next().getValue();
            }
        }
        
        return def;
    }
    public DefenceAlgorithm(int type){
        this.type=type;
    }
}


