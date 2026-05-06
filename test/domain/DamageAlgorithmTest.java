/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package domain;

import java.util.HashMap;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la lógica de cálculo de daño.
 * @author Miguel Pradillo Bartolomé
 */
public class DamageAlgorithmTest {
    
    private DamageAlgorithm instance;

    public DamageAlgorithmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new DamageAlgorithm();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of execute method, of class DamageAlgorithm.
     * Escenario 1: Personaje solo con poder base (sin armas ni modificadores).
     */
    @Test
    public void testExecute_BasePowerOnly() {
        System.out.println("execute - Solo Poder Base");
        
        // Arrange: Creamos un personaje "falso" (Stub) que solo devuelve 3 de poder
        GameCharacter c = new GameCharacter("TestChar", 5, 3, null, 
                new HashMap<>(), new HashMap<>(), null, 
                new LinkedList<>(), new LinkedList<>(), null, null) {
            @Override
            public int getPower() {
                return 3; 
            }
        };

        //Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // Assert: El daño esperado debe ser exactamente su poder base
        assertEquals("El daño debe ser igual al poder base si no hay armas ni modificadores", 3, result);
    }

    /**
     * Test of execute method, of class DamageAlgorithm.
     * Escenario 2: Personaje con poder base + Arma equipada.
     */
    @Test
    public void testExecute_WithWeapons() {
        System.out.println("execute - Con Armas");
        
        // Arrange: Preparamos un arma con 2 de ataque
        HashMap<String, Weapons> armas = new HashMap<>();
        Weapons espada = new Weapons("Espada Larga", 2, 0, 1); // Nombre, Atq, Def, Manos
        armas.put("Espada", espada);
        
        // Creamos el personaje Stub devolviendo 4 de poder base y el mapa de armas
        GameCharacter c = new GameCharacter("TestCharWeapons", 5, 4, null, 
                new HashMap<>(), armas, null, 
                new LinkedList<>(), new LinkedList<>(), null, null) {
            @Override
            public int getPower() { return 4; }
            
            @Override
            public HashMap<String, Weapons> getWeapon() { return armas; }
        };

        //Ejecutamos el cálculo (Poder Base 4 + Arma 2 = 6)
        int expResult = 6; 
        int result = instance.execute(c);
        
        // Assert
        assertEquals("El daño debe ser la suma del poder base y el ataque del arma", expResult, result);
    }

    /**
     * Test of execute method, of class DamageAlgorithm.
     * Escenario 3: Personaje con modificadores de Fortaleza que aumentan su daño.
     */
    @Test
    public void testExecute_WithStrengths() {
        System.out.println("execute - Con Modificadores (Fortalezas)");
        
        // Arrange: Preparamos una fortaleza que sume 1 al ataque
        LinkedList<Strength> fortalezas = new LinkedList<>();
        Strength furia = new Strength("Furia", 1); // Asumiendo constructor (Nombre, ValorAtaque)
        fortalezas.add(furia);
        
        GameCharacter c = new GameCharacter("TestCharStrengths", 5, 3, null, 
                new HashMap<>(), new HashMap<>(), null, 
                fortalezas, new LinkedList<>(), null, null) {
            @Override
            public int getPower() { return 3; }
            
            @Override
            public LinkedList<Strength> getStrength() { return fortalezas; }
        };

        //Ejecutamos el cálculo (Poder Base 3 + Fortaleza 1 = 4)
        int expResult = 4;
        int result = instance.execute(c);
        
        // Assert
        assertEquals("El daño debe incluir los modificadores activos de las fortalezas", expResult, result);
    }
}