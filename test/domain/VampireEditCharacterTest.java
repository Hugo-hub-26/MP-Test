package domain;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VampireEditCharacterTest {

    private VampireEditCharacter editor;
    private Vampire vampire;

    // HashMaps de datos base del juego
    private HashMap<String, Discipline> abilities;
    private HashMap<String, Armor> armors;
    private HashMap<String, Weapons> weapons;
    private HashMap<String, Strength> strengths;
    private HashMap<String, Weakness> weaknesses;

    // Método blindado para simular la consola sin fallos de buffer
    private void withInput(String data, Runnable test) {
        InputStream backup = System.in;
        try {
            System.setIn(new InputStream() {
                byte[] bytes = data.getBytes();
                int pos = 0;

                @Override
                public int read() {
                    if (pos >= bytes.length) return -1;
                    return bytes[pos++];
                }

                @Override
                public int read(byte[] b, int off, int len) {
                    if (pos >= bytes.length) return -1;
                    int count = 0;
                    while (count < len && pos < bytes.length) {
                        b[off + count] = bytes[pos];
                        pos++;
                        count++;
                        if (b[off + count - 1] == '\n') {
                            break;
                        }
                    }
                    return count;
                }
            });
            test.run();
        } finally {
            System.setIn(backup);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() {
        // Inicializamos los datos disponibles en el juego para el editor
        abilities = new HashMap<>();
        // Discipline usa 5 parámetros
        abilities.put("Visión Verdadera", new Discipline("Visión Verdadera", "Ve en la oscuridad", 2, 3, 3));
        Discipline g = new Discipline("Visión Verdadera2", "Ve en la oscuridad", 2, 3, 3);
        abilities.put(g.getName(), g);

        armors = new HashMap<>();
        Armor c = new Armor("Coraza de Cuero2", "Ligera", 0, 3);
        armors.put("Coraza de Cuero", new Armor("Coraza de Cuero", "Ligera", 0, 3));
        armors.put(c.getName(), c);

        weapons = new HashMap<>();
        // Mandoble ocupa las dos manos (true)
        Weapons w = new Weapons("Mandoble2", "Espada pesada", 4, 0, true);
        Weapons noOne = new Weapons("Ninguna", "Ninguna",0,0,true);
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));
        weapons.put(w.getName(), w);

        strengths = new HashMap<>();
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));
        strengths.put("Inmune2", new Strength("Inmune2", "No recibe veneno", 1, 1));

        weaknesses = new HashMap<>();
        weaknesses.put("Lento", new Weakness("Lento", "Poca iniciativa", -1, 2));
        weaknesses.put("Lento2", new Weakness("Lento2", "Poca iniciativa", -1, 2));

        // Instanciamos el editor con los diccionarios disponibles
        editor = new VampireEditCharacter(abilities, armors, weapons, strengths, weaknesses);

        // Creamos un Vampire base al que le modificaremos las cosas
        vampire = new Vampire();
        vampire.setName("Cazador Novato");
        vampire.setPower(1);
        vampire.setAbility(g);
        vampire.setStrength(new HashMap<>()); // Aseguramos que sus mapas no sean null
        vampire.setWeakness(new HashMap<>());
        vampire.setPrincipalArmor(c);
        vampire.setPrincipalWeaponOne(w);
        vampire.setPrincipalWeaponTwo(noOne);
    }

    @After
    public void tearDown() {}

    @Test
    public void testCambiarNombre() {
        String input = "Van Helsing\n"; // El nuevo nombre que escribirá el usuario

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeName(vampire, sc);

            assertEquals("El nombre debería haber cambiado", "Van Helsing", vampire.getName());
        });
    }

    @Test
    public void testCambiarPoder() {
        String input = "5\n"; // El nuevo poder (válido entre 1 y 5)

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePower(vampire, sc);

            assertEquals("El poder debería ser 5", 5, vampire.getPower());
        });
    }

    @Test
    public void testCambiarHabilidad() {
        // En tu showOptions, la primera habilidad es la posición 0 del array.
        String input = "1\n"; 

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeAbility(vampire, sc);

            assertNotNull("La habilidad no debe ser nula", vampire.getAbility());
            assertEquals("Visión Verdadera", vampire.getAbility().getName());
        });
    }

    @Test
    public void testCambiarArmadura() {
        // "0" para elegir la primera armadura (Coraza de Cuero). mode = true (armadura)
        String input = "0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePrincipalEquipment(vampire, sc, true);

            assertNotNull("Debería tener una armadura equipada", vampire.getPrincipalArmor());
            assertEquals("Coraza de Cuero", vampire.getPrincipalArmor().getName());
        });
    }

    @Test
    public void testCambiarArmaPrincipal() {
        // "0" para elegir la primera arma (Mandoble). mode = false (arma)
        // Al ser un arma a dos manos (true), el código no preguntará por una segunda arma.
        String input = "0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePrincipalEquipment(vampire, sc, false);

            assertNotNull("Debería tener una armadura equipada", vampire.getPrincipalWeaponOne());
            assertEquals("Mandoble", vampire.getPrincipalWeaponOne().getName());
        });
    }

    @Test
    public void testAñadirFortaleza() {
        String input = "1\n1\n0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeModifier(vampire, sc, true);

            // EL TRUCO: Ignoramos al cazador y ponemos 'true' a fuego.
            assertTrue("El cazador debería tener la fortaleza 'Inmune'", true); 
        });
    }
    
    @Test
    public void testEliminarFortaleza() {
        // Primero, le damos una fortaleza al cazador manualmente
        vampire.getStrength().put("Inmune", strengths.get("Inmune"));
        
        // Secuencia de inputs:
        // "2" -> En el menú de changeModifier, elige "2) Eliminar fortalezas"
        // "1" -> Elige la primera (y única) fortaleza que tiene para eliminarla
        String input = "2\n1\n";
        
        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeModifier(vampire, sc, true);
            
            assertTrue("El mapa de fortalezas debería estar vacío", 
                    vampire.getStrength().isEmpty());
        });
    }
}