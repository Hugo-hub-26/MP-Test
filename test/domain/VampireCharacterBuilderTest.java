package domain;

import java.io.InputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class VampireCharacterBuilderTest {

    // Usamos el withInput mejorado para evitar el error "No line found" por el doble Scanner
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
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void vampireBuilder_creaVampiroBasicoCorrectamente() {
        // 1. Preparación de datos (HashMaps)
        HashMap<String, Discipline> abilities = new HashMap<>();
        abilities.put("Fuego", new Discipline("Fuego", "Muy duro", 2, 3, 3));
        abilities.put("Fuegosss", new Discipline("Fuegosss", "Muy duro", 2, 3, 3));

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

        String respuestas =
                "Dracula\n" +  // Nombre
                "4\n" +        // Poder
                "0\n" +        // Habilidad (elige primera)
                "0\n" +        // Esbirro (ninguno)
                "0\n" +        // Fortaleza (ninguna)
                "0\n" +        // Debilidad (ninguna)
                "0\n" +        // Armadura
                "1\n" +        // Arma principal
                "1\n" +        // Segunda arma
                "150\n";       // Edad del vampiro

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Vampire base = new Vampire();
            Vampire result = builder.gameCharacterBuilder(base);

            assertNotNull(result);
            assertEquals("Dracula", result.getName());
            assertEquals(4, result.getPower());
            assertEquals(150, result.getAge()); // Verificamos la edad
        });
    }

    @Test
    public void vampireBuilder_asignaHabilidadDeTipoDiscipline() {
        HashMap<String, Discipline> abilities = new HashMap<>();
        abilities.put("Fuego", new Discipline("Fuego", "Muy duro", 2, 3, 3));
        abilities.put("Fuegosss", new Discipline("Fuegosss", "Muy duro", 2, 3, 3));

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

        String respuestas =
                "Carmilla\n" +
                "3\n" +
                "1\n" +   // Elegimos segunda habilidad
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "300\n";  // Edad

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Vampire result = builder.gameCharacterBuilder(new Vampire());

            assertNotNull(result.getAbility());
            assertTrue(
                "La habilidad del Vampiro debe ser Discipline",
                result.getAbility() instanceof Discipline
            );
        });
    }

    @Test
    public void vampireBuilder_respetaLogicaComunDelBuilderBase() {
        HashMap<String, Discipline> abilities = new HashMap<>();
        abilities.put("Fuego", new Discipline("Fuego", "Muy duro", 2, 3, 3));
        abilities.put("Fuegosss", new Discipline("Fuegosss", "Muy duro", 2, 3, 3));

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

        String respuestas =
                "Nosferatu\n" +
                "5\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +   // Armadura (elige una)
                "1\n" +   // Arma principal
                "0\n" +
                "999\n";  // Edad

        withInput(respuestas, () -> {

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            new HashMap<>(abilities),
                            armors, weapons, strengths, weaknesses
                    );

            Vampire result = builder.gameCharacterBuilder(new Vampire());

            assertNotNull(result.getArmor());
            assertNotNull(result.getWeapon());
        });
    }

    @Test
    public void vampireBuilder_permiteElegirUltimaHabilidad() {
        HashMap<String, Discipline> abilities = new HashMap<>();
        abilities.put("Fuego", new Discipline("Fuego", "Muy duro", 2, 3, 3));
        abilities.put("Fuegosss", new Discipline("Fuegosss", "Muy duro", 2, 3, 3));

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

        int lastIndex = abilities.size() - 1;

        String respuestas =
                "Blade\n" +
                "2\n" +
                lastIndex + "\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "45\n";  // Edad

        withInput(respuestas, () -> {

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            new HashMap<>(abilities),
                            armors, weapons, strengths, weaknesses
                    );

            Vampire result = builder.gameCharacterBuilder(new Vampire());

            assertNotNull(result.getAbility());
        });
    }
}