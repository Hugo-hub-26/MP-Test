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
<<<<<<< HEAD
        // Inicializamos el algoritmo antes de cada prueba
        instance = new DamageAlgorithm(1);
=======
        instance = new DamageAlgorithm();
>>>>>>> e396613e456f62282c76584337cdc7eaa7ab3bc2
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
        
<<<<<<< HEAD
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
=======
        // Instanciamos la habilidad (Voluntad) requerida por el Cazador
        Ability habilidad = new Will("Concentración", 1, 1, 1);
        
        // Construimos el personaje usando los métodos reales de CharacterBuilder
        GameCharacter c = (GameCharacter) new HunterCharacterBuilder()
                .withName("Cazador Novato")
                .withBaseStats(5, 3) // Salud = 5, Poder = 3
                .withAbility(habilidad)
                .build();
>>>>>>> e396613e456f62282c76584337cdc7eaa7ab3bc2

        // ACT: Ejecutamos el algoritmo
        int result = instance.execute(c);
        
        // ASSERT: Comprueba que el resultado es igual al poder base
        assertEquals("El daño debe ser igual al poder base si no hay armas ni modificadores", 3, result);
    }
<<<<<<< HEAD
=======

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
>>>>>>> e396613e456f62282c76584337cdc7eaa7ab3bc2
}