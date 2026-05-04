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
public class LycanthropeEditCharacter extends EditGameCharacter {
    
    public LycanthropeEditCharacter(HashMap<String, ? extends Ability> ability, HashMap<String, Armor> armor, HashMap<String, Weapons> weapon, HashMap<String, Strength> strength, HashMap<String, Weakness> weakness) {
        super(ability, armor, weapon, strength, weakness);
    }
    
    public void changeHeight(GameCharacter characterr, Scanner sc){
        Lycanthrope lycan = (Lycanthrope) characterr;
        lycan.setHeight(requestNumber("Introduce la nueva edad del vampiro", 100, 210, sc));
    }
            
    public void changeWeight(GameCharacter characterr, Scanner sc){
       Lycanthrope lycan = (Lycanthrope) characterr;
        lycan.setWeight(requestNumber("Introduce la nueva edad del vampiro", 50, 150, sc));
    }
    
        @Override
    public void menu(){
        System.out.println("0) Dejar de editar");
        System.out.println("1) Editar el nombre");
        System.out.println("2) Editar el poder");
        System.out.println("3) Editar la habilidad");
        System.out.println("4) Editar el minion");
        System.out.println("5) Editar las fortalezas");
        System.out.println("6) Editar las debilidades");
        System.out.println("7) Editar la armadura principal");
        System.out.println("8) Editar la arma o armas principal");
        System.out.println("9) Editar la altura");
        System.out.println("9) Editar el peso");
    }
}
