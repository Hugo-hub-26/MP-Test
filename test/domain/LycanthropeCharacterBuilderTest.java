package domain;

import java.io.InputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class LycanthropeCharacterBuilderTest {

    // Mantenemos el withInput mejorado para esquivar el error del doble Scanner
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
    public void lycanthropeBuilder_creaLicantropoBasicoCorrectamente() {
        // 1. Preparación de datos (HashMaps)
        HashMap<String, Will> abilities = new HashMap<>();
        abilities.put("Furia", new Will("Furia", "Aumenta el daño físico", 2, 3,3));
        abilities.put("Aullido", new Will("Aullido", "Aterroriza enemigos", 2, 3,3));

        HashMap<String, Armor> armors = new HashMap<>();
        armors.put("Piel Dura", new Armor("Piel Dura", "Resistencia natural", 0, 3));
        armors.put("Cota de Malla", new Armor("Cota de Malla", "Protección metálica", 0, 3));

        HashMap<String, Weapons> weapons = new HashMap<>();
        weapons.put("Garras", new Weapons("Garras", "Arma natural", 4, 0, true));
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));

        HashMap<String, Strength> strengths = new HashMap<>();
        strengths.put("Vigor", new Strength("Vigor", "Más salud", 1, 1));
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));

        HashMap<String, Weakness> weaknesses = new HashMap<>();
        weaknesses.put("Plata", new Weakness("Plata", "Recibe más daño por plata", -1, 2));
        weaknesses.put("Acónito", new Weakness("Acónito", "Debilidad a hierbas", -1, 2));

        String respuestas =
                "Jacob\n" +    // Nombre
                "4\n" +        // Poder
                "0\n" +        // Habilidad (elige primera)
                "0\n" +        // Esbirro (ninguno)
                "0\n" +        // Fortaleza (ninguna)
                "0\n" +        // Debilidad (ninguna)
                "0\n" +        // Armadura
                "1\n" +        // Arma principal
                "1\n" +        // Segunda arma
                "185\n" +      // Altura (válido: 100-210)
                "90\n";        // Peso (válido: 50-150)

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            LycanthropeCharacterBuilder builder =
                    new LycanthropeCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Lycanthrope base = new Lycanthrope();
            Lycanthrope result = builder.gameCharacterBuilder(base);

            assertNotNull(result);
            assertEquals("Jacob", result.getName());
            assertEquals(4, result.getPower());
            assertEquals(185, result.getHeigth()); // Comprueba altura
            assertEquals(90, result.getWeigth());  // Comprueba peso
        });
    }

    @Test
    public void lycanthropeBuilder_asignaHabilidadDeTipoWill() {
        HashMap<String, Will> abilities = new HashMap<>();
        abilities.put("Furia", new Will("Furia", "Aumenta el daño", 2, 3,3));
        abilities.put("Aullido", new Will("Aullido", "Aterroriza", 2, 3,3));

        HashMap<String, Armor> armors = new HashMap<>();
        armors.put("Piel Dura", new Armor("Piel Dura", "Resistencia natural", 0, 3));
        armors.put("Cota de Malla", new Armor("Cota de Malla", "Protección metálica", 0, 3));

        HashMap<String, Weapons> weapons = new HashMap<>();
        weapons.put("Garras", new Weapons("Garras", "Arma natural", 4, 0, true));
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));

        HashMap<String, Strength> strengths = new HashMap<>();
        strengths.put("Vigor", new Strength("Vigor", "Más salud", 1, 1));
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));

        HashMap<String, Weakness> weaknesses = new HashMap<>();
        weaknesses.put("Plata", new Weakness("Plata", "Daño masivo", -1, 2));
        weaknesses.put("Acónito", new Weakness("Acónito", "Veneno lobo", -1, 2));

        String respuestas =
                "Lucian\n" +
                "3\n" +
                "1\n" +   // Elegimos segunda habilidad
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "200\n" + // Altura
                "110\n";  // Peso

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            LycanthropeCharacterBuilder builder =
                    new LycanthropeCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Lycanthrope result = builder.gameCharacterBuilder(new Lycanthrope());

            assertNotNull(result.getAbility());
            assertTrue(
                "La habilidad del Licántropo debe ser de tipo Will",
                result.getAbility() instanceof Will
            );
        });
    }

    @Test
    public void lycanthropeBuilder_respetaLogicaComunDelBuilderBase() {
        HashMap<String, Will> abilities = new HashMap<>();
        abilities.put("Furia", new Will("Furia", "Aumenta el daño", 2, 3,3));
        abilities.put("Aullido", new Will("Aullido", "Aterroriza", 2, 3,3));

        HashMap<String, Armor> armors = new HashMap<>();
        armors.put("Piel Dura", new Armor("Piel Dura", "Resistencia natural", 0, 3));
        armors.put("Cota de Malla", new Armor("Cota de Malla", "Protección metálica", 0, 3));

        HashMap<String, Weapons> weapons = new HashMap<>();
        weapons.put("Garras", new Weapons("Garras", "Arma natural", 4, 0, true));
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));

        HashMap<String, Strength> strengths = new HashMap<>();
        strengths.put("Vigor", new Strength("Vigor", "Más salud", 1, 1));
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));

        HashMap<String, Weakness> weaknesses = new HashMap<>();
        weaknesses.put("Plata", new Weakness("Plata", "Daño masivo", -1, 2));
        weaknesses.put("Acónito", new Weakness("Acónito", "Veneno lobo", -1, 2));

        String respuestas =
                "Fenrir\n" +
                "5\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +   // Armadura (elige una)
                "1\n" +   // Arma principal
                "0\n" +
                "210\n" + // Altura
                "150\n";  // Peso

        withInput(respuestas, () -> {

            LycanthropeCharacterBuilder builder =
                    new LycanthropeCharacterBuilder(
                            new HashMap<>(abilities),
                            armors, weapons, strengths, weaknesses
                    );

            Lycanthrope result = builder.gameCharacterBuilder(new Lycanthrope());

            assertNotNull(result.getArmor());
            assertNotNull(result.getWeapon());
        });
    }

    @Test
    public void lycanthropeBuilder_permiteElegirUltimaHabilidad() {
        HashMap<String, Will> abilities = new HashMap<>();
        abilities.put("Furia", new Will("Furia", "Aumenta el daño", 2, 3,3));
        abilities.put("Aullido", new Will("Aullido", "Aterroriza", 2, 3,3));

        HashMap<String, Armor> armors = new HashMap<>();
        armors.put("Piel Dura", new Armor("Piel Dura", "Resistencia natural", 0, 3));
        armors.put("Cota de Malla", new Armor("Cota de Malla", "Protección metálica", 0, 3));

        HashMap<String, Weapons> weapons = new HashMap<>();
        weapons.put("Garras", new Weapons("Garras", "Arma natural", 4, 0, true));
        weapons.put("Mandoble", new Weapons("Mandoble", "Espada pesada", 4, 0, true));

        HashMap<String, Strength> strengths = new HashMap<>();
        strengths.put("Vigor", new Strength("Vigor", "Más salud", 1, 1));
        strengths.put("Inmune", new Strength("Inmune", "No recibe veneno", 1, 1));

        HashMap<String, Weakness> weaknesses = new HashMap<>();
        weaknesses.put("Plata", new Weakness("Plata", "Daño masivo", -1, 2));
        weaknesses.put("Acónito", new Weakness("Acónito", "Veneno lobo", -1, 2));

        int lastIndex = abilities.size() - 1;

        String respuestas =
                "Remus\n" +
                "2\n" +
                lastIndex + "\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "175\n" + // Altura
                "75\n";   // Peso

        withInput(respuestas, () -> {

            LycanthropeCharacterBuilder builder =
                    new LycanthropeCharacterBuilder(
                            new HashMap<>(abilities),
                            armors, weapons, strengths, weaknesses
                    );

            Lycanthrope result = builder.gameCharacterBuilder(new Lycanthrope());

            assertNotNull(result.getAbility());
        });
    }
}