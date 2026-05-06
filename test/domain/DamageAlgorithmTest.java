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
 * Pruebas unitarias para la lógica de cálculo de daño utilizando el Builder.
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
     * Escenario 1: Personaje solo con poder base (sin armas ni modificadores).
     */
    @Test
    public void testExecute_BasePowerOnly() {
        System.out.println("execute - Solo Poder Base");
        
        // Instanciamos la habilidad (Voluntad) requerida por el Cazador
        Ability habilidad = new Will("Concentración", 1, 1, 1);
        
        // Construimos el personaje usando los métodos reales de CharacterBuilder
        GameCharacter c = (GameCharacter) new HunterCharacterBuilder()
                .withName("Cazador Novato")
                .withBaseStats(5, 3) // Salud = 5, Poder = 3
                .withAbility(habilidad)
                .build();

        // ACT: Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // ASSERT: Comprueba que el resultado es igual al poder base
        assertEquals("El daño debe ser igual al poder base si no hay armas ni modificadores", 3, result);
    }

    /**
     * Escenario 2: Personaje con poder base + Arma equipada.
     */
    @Test
    public void testExecute_WithWeapons() {
        System.out.println("execute - Con Armas");
        
        // Instanciamos un arma con 2 puntos de ataque
        Weapons arma = new Weapons("Espada Larga", 2, 0, 1); 
        HashMap<String, Weapons> armasMap = new HashMap<>();
        armasMap.put("Espada Larga", arma);
        
        Ability habilidad = new Will("Concentración", 1, 1, 1);
        
        // Construimos el personaje inyectándole el mapa de armas
        GameCharacter c = (GameCharacter) new HunterCharacterBuilder()
                .withName("Cazador Armado")
                .withBaseStats(5, 4) // Poder base = 4
                .withAbility(habilidad)
                .withWeapons(armasMap)
                .build();

        // Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // ASSERT: 4 (poder base) + 2 (ataque del arma) = 6
        int expResult = 6; 
        assertEquals("El daño debe ser la suma del poder base y el ataque del arma", expResult, result);
    }

    /**
     * Escenario 3: Personaje con modificadores de Fortaleza.
     */
    @Test
    public void testExecute_WithStrengths() {
        System.out.println("execute - Con Modificadores (Fortalezas)");
        
        // Instanciamos una Fortaleza que sume 1 punto al modificador
        Strength fortaleza = new Strength("Furia", 1); 
        LinkedList<Strength> fortalezasList = new LinkedList<>();
        fortalezasList.add(fortaleza);
        
        Ability habilidad = new Will("Concentración", 1, 1, 1);
        
        // Construimos el personaje inyectándole la lista de fortalezas
        GameCharacter c = (GameCharacter) new HunterCharacterBuilder()
                .withName("Cazador Fuerte")
                .withBaseStats(5, 3) // Poder base = 3
                .withAbility(habilidad)
                .withStrengths(fortalezasList)
                .build();

        // Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // ASSERT: 3 (poder base) + 1 (bonificador de fortaleza) = 4
        int expResult = 4;
        assertEquals("El daño debe incluir los modificadores activos de las fortalezas", expResult, result);
    }
}