package domain;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class HunterCharacterBuilderTest {

    @Test
    public void testGameCharacterBuilderManual() {
        System.out.println("--- INICIANDO TEST AUTOMATIZADO ---");

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

        // 2. Definición de respuestas automáticas
        String respuestas = "Legolas\n" +    // Nombre
                            "4\n" +          // Poder
                            "0\n" +          // Habilidad
                            "0\n" +          // Esbirro (0: Ninguno)
                            "1\n" +          // Fortaleza (elige una)
                            "0\n" +          // Fortaleza (para)
                            "1\n" +          // Debilidad (elige una)
                            "0\n" +          // Debilidad (para)
                            "0\n" +          // Armadura
                            "0\n" +          // Arma principal
                            "0\n";           // Segunda arma

        // 3. Redirección de entrada
        java.io.InputStream sysInBackup = System.in;
        System.setIn(new java.io.ByteArrayInputStream(respuestas.getBytes()));
        HashMap<String, Ability> a= new HashMap<>(abilities);
        try {
            // 4. Instancia y ÚNICA Ejecución
            HunterCharacterBuilder instance = new HunterCharacterBuilder(
                    a, armors, weapons, strengths, weaknesses
            );
            Hunter h = new Hunter();
            Hunter result = instance.gameCharacterBuilder(h);

            // 5. Verificaciones
            assertNotNull("El personaje debería haberse creado", result);
            assertEquals("Legolas", result.getName());
            assertEquals(4, result.getPower());
            
            System.out.println("\n--- RESULTADO DEL TEST ---");
            System.out.println("Personaje: " + result.getName() + " con poder " + result.getPower());

        } finally {
            // 6. Restaurar teclado original
            System.setIn(sysInBackup);
        }
    }
}