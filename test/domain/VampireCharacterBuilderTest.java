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
                "0\n" +        // Armadura (ninguna)
                "1\n" +        // Arma principal (añade una)
                "1\n" +        // Segunda arma (añade otra / sale)
                "150\n";       // Edad del vampiro

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Vampire base = new Vampire();
            Vampire result = builder.gameCharacterBuilder(base);

            // ASSERT: Datos básicos
            assertNotNull("El personaje no debería ser nulo", result);
            assertEquals("Dracula", result.getName());
            assertEquals(4, result.getPower());
            assertEquals(150, result.getAge()); 

            // ASSERT: Habilidad
            assertNotNull("Debería tener una habilidad asignada", result.getAbility());
            assertTrue("La habilidad de un vampiro debe ser tipo Discipline", result.getAbility() instanceof Discipline);
            
            // ASSERT: Equipamiento y Modificadores (Se marcó '0' para todo menos las armas)
            assertTrue("No debería tener armaduras porque se introdujo 0", 
                        result.getArmor() == null || result.getArmor().isEmpty());
            
            assertNotNull("La colección de armas debe estar inicializada", result.getWeapon());
            assertFalse("Debería tener al menos un arma porque se introdujo 1", result.getWeapon().isEmpty());

            assertTrue("No debería tener fortalezas", 
                        result.getStrength() == null || result.getStrength().isEmpty());
            
            assertTrue("No debería tener debilidades", 
                        result.getWeakness() == null || result.getWeakness().isEmpty());

            // ASSERT: Esbirro (Si getMinion() devuelve null o una lista vacía)
            assertTrue("No debería tener esbirros asignados", 
                        result.getMinion() == null || 
                        (result.getMinion() instanceof java.util.Collection && ((java.util.Collection<?>)result.getMinion()).isEmpty()));
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
                "0\n" +   // Esbirro
                "0\n" +   // Fortaleza
                "0\n" +   // Debilidad
                "0\n" +   // Armadura
                "0\n" +   // Arma
                "0\n" +   // Arma 2 (si lo pide)
                "300\n";  // Edad

        withInput(respuestas, () -> {

            HashMap<String, Ability> abil = new HashMap<>(abilities);

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            abil, armors, weapons, strengths, weaknesses
                    );

            Vampire result = builder.gameCharacterBuilder(new Vampire());

            // ASSERT: Habilidad
            assertNotNull("La habilidad no puede ser nula", result.getAbility());
            assertTrue(
                "La habilidad del Vampiro debe ser innegablemente Discipline",
                result.getAbility() instanceof Discipline
            );
            
            // ASSERT: Comprobación de que no se ha equipado NADA extra (puros 0s)
            assertTrue("Colección de armas debe estar vacía", result.getWeapon() == null || result.getWeapon().isEmpty());
            assertTrue("Colección de armaduras debe estar vacía", result.getArmor() == null || result.getArmor().isEmpty());
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
                "0\n" +   // Habilidad
                "0\n" +   // Esbirro
                "0\n" +   // Fortaleza
                "0\n" +   // Debilidad
                "1\n" +   // Armadura (elige una)
                "1\n" +   // Arma principal
                "0\n" +   // Salir de armas
                "999\n";  // Edad

        withInput(respuestas, () -> {

            VampireCharacterBuilder builder =
                    new VampireCharacterBuilder(
                            new HashMap<>(abilities),
                            armors, weapons, strengths, weaknesses
                    );

            Vampire result = builder.gameCharacterBuilder(new Vampire());

            // ASSERT: Equipamiento asignado
            assertNotNull("La colección de armaduras no debe ser nula", result.getArmor());
            assertFalse("Debería tener al menos una armadura equipada", result.getArmor().isEmpty());
            
            assertNotNull("La colección de armas no debe ser nula", result.getWeapon());
            assertFalse("Debería tener al menos un arma equipada", result.getWeapon().isEmpty());
            
            // ASSERT: Resto vacío
            assertTrue("No debería tener fortalezas", result.getStrength() == null || result.getStrength().isEmpty());
            assertTrue("No debería tener debilidades", result.getWeakness() == null || result.getWeakness().isEmpty());
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

            // ASSERT: Validamos correcta asignación en el límite de la lista
            assertNotNull("La habilidad debería haber sido asignada", result.getAbility());
            assertTrue("El nombre debe coincidir", result.getName().equals("Blade"));
            assertTrue("Su edad debe ser la introducida", result.getAge() == 45);
        });
    }
}