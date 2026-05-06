package domain;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class HunterCharacterBuilderTest {

	private void withInput(String data, Runnable test) {
	    InputStream backup = System.in;
	    try {
	        System.setIn(new ByteArrayInputStream(data.getBytes()));
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
	public void hunterBuilder_creaHunterBasicoCorrectamente() {
	// 1. Preparación de datos (HashMaps)
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

    String respuestas =
            "Legolas\n" +  // Nombre
            "4\n" +        // Poder
            "0\n" +        // Habilidad (elige primera)
            "0\n" +        // Esbirro (ninguno)
            "0\n" +        // Fortaleza (ninguna)
            "0\n" +        // Debilidad (ninguna)
            "0\n" +        // Armadura
            "1\n" +        // Arma principal
            "1\n";         // Segunda arma

    withInput(respuestas, () -> {

        HashMap<String, Ability> abil = new HashMap<>(abilities);

        HunterCharacterBuilder builder =
                new HunterCharacterBuilder(
                        abil, armors, weapons, strengths, weaknesses
                );

        Hunter base = new Hunter();
        Hunter result = builder.gameCharacterBuilder(base);

        assertNotNull(result);
        assertEquals("Legolas", result.getName());
        assertEquals(4, result.getPower());
    });
}

	@Test
	public void hunterBuilder_asignaHabilidadDeTipoGift() {
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

	    String respuestas =
            "Artemis\n" +
            "3\n" +
            "1\n" +   // Elegimos segunda habilidad
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n";

	    withInput(respuestas, () -> {

        HashMap<String, Ability> abil = new HashMap<>(abilities);

        HunterCharacterBuilder builder =
                new HunterCharacterBuilder(
                        abil, armors, weapons, strengths, weaknesses
                );

        Hunter result = builder.gameCharacterBuilder(new Hunter());

        assertNotNull(result.getAbility());
        assertTrue(
            "La habilidad de Hunter debe ser Gift",
            result.getAbility() instanceof Gift
	        );
	    });
	}

	@Test
	public void hunterBuilder_respetaLogicaComunDelBuilderBase() {
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

    String respuestas =
            "Robin\n" +
            "5\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "1\n" +   // Armadura (elige una)
            "1\n" +   // Arma principal
            "0\n";

	    withInput(respuestas, () -> {

        HunterCharacterBuilder builder =
                new HunterCharacterBuilder(
                        new HashMap<>(abilities),
                        armors, weapons, strengths, weaknesses
                );

        Hunter result = builder.gameCharacterBuilder(new Hunter());

        assertNotNull(result.getArmor());
        assertNotNull(result.getWeapon());
	    });
	}
	@Test
	public void hunterBuilder_permteElegirUltimaHabilidad() {
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

	    int lastIndex = abilities.size() - 1;

	    String respuestas =
            "Hawkeye\n" +
            "2\n" +
            lastIndex + "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n";

	    withInput(respuestas, () -> {

        HunterCharacterBuilder builder =
                new HunterCharacterBuilder(
                        new HashMap<>(abilities),
                        armors, weapons, strengths, weaknesses
                );

        Hunter result = builder.gameCharacterBuilder(new Hunter());

        assertNotNull(result.getAbility());
    	});
	}
}
