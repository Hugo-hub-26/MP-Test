/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package domain;

import java.util.HashMap;
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
        // Inicializamos el algoritmo antes de cada prueba
        instance = new DamageAlgorithm(1);
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
        
		HashMap<String, Gift> abilities = new HashMap<>();
        abilities.put("Fuego", new Gift("Fuego", "Muy duro", 2, 3));
        abilities.put("Fuegosss", new Gift("Fuegosss", "Muy duro", 2, 3));

        HashMap<String, Armor> armors = new HashMap<>();
        armors.put("Cota de Malla", new Armor("Cota de Malla", "Protección metálica", 0, 3));
        armors.put("Cotas de Mallas", new Armor("Cotas de Mallas", "Protección metálica", 0, 3));

        HashMap<String, Weapons> weapons = new HashMap<>();
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));
        weapons.put("Masndoble", new Weapons("Masndoble", "Espada pesada", 4, 0, true));

        HashMap<String, Strength> strengths = new HashMap<>();
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));
        strengths.put("Inmunae", new Strength("Inmuane", "No recibe veneno", 1, 1));

        HashMap<String, Weakness> weaknesses = new HashMap<>();
        weaknesses.put("Vulnerable", new Weakness("Vulnerable", "Recibe más daño fuego", -1, 2));
        weaknesses.put("Vulnersaable", new Weakness("Vulneraable", "Recibe más daño fuego", -1, 2));
        
        GameCharacter c = new Hunter();
		c = new HunterCharacterBuilder(abilities, armors, weapons, strengths, weaknesses).gameCharacterBuilder(c);

        // Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // El daño esperado debe ser exactamente su poder base
        assertEquals("El daño debe ser igual al poder base si no hay armas ni modificadores", 3, result);
    }
}