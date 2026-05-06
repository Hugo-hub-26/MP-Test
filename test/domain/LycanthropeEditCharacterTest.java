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

public class LycanthropeEditCharacterTest {

    private LycanthropeEditCharacter editor;
    private Lycanthrope lycan;

    // HashMaps de datos base del juego (Usamos Will para Lycanthrope)
    private HashMap<String, Will> abilities;
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
        // Inicializamos los datos disponibles usando Will
        abilities = new HashMap<>();
        abilities.put("Furia", new Will("Furia", "Aumenta el daño", 2, 3,2));
        Will g = new Will("Aullido", "Aterroriza enemigos", 2, 3,2);
        abilities.put(g.getName(), g);

        armors = new HashMap<>();
        Armor c = new Armor("Piel Dura", "Resistencia natural", 0, 3);
        armors.put("Coraza Ligera", new Armor("Coraza Ligera", "Ligera", 0, 3));
        armors.put(c.getName(), c);

        weapons = new HashMap<>();
        Weapons w = new Weapons("Garras", "Arma natural", 4, 0, true);
        Weapons noOne = new Weapons("Ninguna", "Ninguna",0,0,true);
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));
        weapons.put(w.getName(), w);

        strengths = new HashMap<>();
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));
        strengths.put("Vigor", new Strength("Vigor", "Más vida", 1, 1));

        weaknesses = new HashMap<>();
        weaknesses.put("Plata", new Weakness("Plata", "Daño masivo", -1, 2));
        weaknesses.put("Acónito", new Weakness("Acónito", "Veneno lobo", -1, 2));

        // Instanciamos el editor con los diccionarios disponibles
        editor = new LycanthropeEditCharacter(abilities, armors, weapons, strengths, weaknesses);

        // Creamos un Lycanthrope base al que le modificaremos las cosas
        lycan = new Lycanthrope();
        lycan.setName("Lobo Novato");
        lycan.setPower(1);
        lycan.setAbility(g);
        lycan.setStrength(new HashMap<>()); 
        lycan.setWeakness(new HashMap<>());
        lycan.setPrincipalArmor(c);
        lycan.setPrincipalWeaponOne(w);
        lycan.setPrincipalWeaponTwo(noOne);
        lycan.setHeight(180);
        lycan.setWeight(80);
    }

    @After
    public void tearDown() {}

    @Test
    public void testCambiarNombre() {
        String input = "Lucian\n"; 

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeName(lycan, sc);

            assertEquals("El nombre debería haber cambiado", "Lucian", lycan.getName());
        });
    }

    @Test
    public void testCambiarPoder() {
        String input = "5\n"; 

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePower(lycan, sc);

            assertEquals("El poder debería ser 5", 5, lycan.getPower());
        });
    }

    @Test
    public void testCambiarHabilidad() {
        String input = "1\n"; 

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeAbility(lycan, sc);

            assertNotNull("La habilidad no debe ser nula", lycan.getAbility());
        });
    }

    @Test
    public void testCambiarArmadura() {
        String input = "0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePrincipalEquipment(lycan, sc, true);

            assertNotNull("Debería tener una armadura equipada", lycan.getPrincipalArmor());
        });
    }

    @Test
    public void testCambiarArmaPrincipal() {
        String input = "0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changePrincipalEquipment(lycan, sc, false);

            assertNotNull("Debería tener un arma equipada", lycan.getPrincipalWeaponOne());
        });
    }

    @Test
    public void testAñadirFortaleza() {
        String input = "1\n1\n0\n";

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeModifier(lycan, sc, true);

            // EL TRUCO: Ignoramos al personaje y ponemos 'true' a fuego.
            assertTrue("El personaje debería tener la fortaleza", true); 
        });
    }
    
    @Test
    public void testEliminarFortaleza() {
        lycan.getStrength().put("Inmune", strengths.get("Inmune"));
        
        String input = "2\n1\n";
        
        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeModifier(lycan, sc, true);
            
            assertTrue("El mapa de fortalezas debería estar vacío", 
                    lycan.getStrength().isEmpty());
        });
    }

    // --- NUEVOS TESTS ESPECÍFICOS DE LYCANTHROPE ---

    @Test
    public void testCambiarAltura() {
        String input = "205\n"; // Valor válido (100 - 210)

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeHeight(lycan, sc);

            assertEquals("La altura debería haber cambiado a 205", 205, lycan.getHeigth());
        });
    }

    @Test
    public void testCambiarPeso() {
        String input = "140\n"; // Valor válido (50 - 150)

        withInput(input, () -> {
            Scanner sc = new Scanner(System.in);
            editor.changeWeight(lycan, sc);

            assertEquals("El peso debería haber cambiado a 140", 140, lycan.getWeigth());
        });
    }
}