/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import java.util.Iterator;
/**
 * Algoritmo de daño. Implementa el patrón
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class DamageAlgorithm implements Algorithm{
private int type;
	@Override
	public int execute(GameCharacter c) {

        int dmg = c.getPower()+c.getAbility().getAttackValue()+
        c.getPrincipalWeaponOne().getAttackModifier()
        +c.getPrincipalWeaponTwo().getAttackModifier();

            if (c instanceof Hunter) {

                dmg+=((Hunter) c).getAttitude();

            } else if (c instanceof Lycanthrope) {
                Lycanthrope lycan = (Lycanthrope) c;
                if (lycan.getRage() >= lycan.getAbility().getRageValue()) {
                    dmg += lycan.getRage();
                } else {
                    dmg -= c.getAbility().getAttackValue();
                    dmg += lycan.getRage();
                }

            } else if (c instanceof Vampire) {

                if (((Vampire) c).getBloodPoints()>=5){ 

                dmg+=2;

                if (((Vampire) c).getBloodPoints()<((Vampire) c).getAbility().getBloodValue())

                    dmg-= c.getAbility().getAttackValue();
                }
                else 
                ((Vampire) c).setBloodPoints(((Vampire) c).getBloodPoints()
                -((Vampire) c).getAbility().getBloodValue());
            }

            //values accede a la columa derecha de la lista, 
            //el iterador no puede iterar respecto a una lista de más
            //de una columna.
        Iterator<Strength> it = c.getStrength().values().iterator();
        while (it.hasNext()) {
            // dmg+=((Strength) it.next()).getValue();
            if (it.next().getType()==type){
            dmg+=it.next().getValue();
            }
        }
        Iterator<Weakness> it1 = c.getWeakness().values().iterator();
        while (it1.hasNext()) {
            // dmg+=((Strength) it.next()).getValue();
            if(it1.next().getType()==type){
            dmg-=it1.next().getValue();
            }

        }
        
        return dmg;
    }
    public DamageAlgorithm (int type){
        this.type=type;
    }
	}
	

