package domain;

import control.GameContext;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsCalculatorTest {

    public StatsCalculatorTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test determinista del método battle
     */
    @Test
    public void testBattleDeterministic() {

        // Crear personaje 1 (fuerte)
        GameCharacter c1 = new Hunter();
        c1.setName("Jugador1");
        c1.setHealth(5);
		Minion m1 = new Human("name", 5, "loyalty", c1);
		c1.setMinion(m1);

        // Crear personaje 2 (débil)
        GameCharacter c2 = new Hunter();
        c2.setName("Jugador2");
        c2.setHealth(5);
		Minion m2 = new Human("name", 5, "loyalty", c1);
        c2.setMinion(m2);

        // Crear contexto de juego
        GameContext context = new GameContext(new Scanner(System.in), null);
        context.setCharacter1(c1);
        context.setCharacter2(c2);

        // Crear StatsCalculator sobreescribiendo comportamiento
        StatsCalculator calculator = new StatsCalculator(0) {

            @Override
            public int calculatedamage(GameCharacter c) {
                if (c.getName().equals("Jugador1")) {
                    return 10; // fuerte
                } else {
                    return 1; // débil
                }
            }

            @Override
            public int calculatedefense(GameCharacter c) {
                if (c.getName().equals("Jugador1")) {
                    return 10;
                } else {
                    return 1;
                }
            }

            @Override
            public int[] calculatehealth(GameCharacter c) {
                return new int[]{5, 5}; // vida fija para ambos
            }
        };

        // Ejecutar batalla
        GameContext result1 = calculator.battle(context);

		context.setCharacter1(c1);
        context.setCharacter2(c2);

        // Comprobaciones
        assertNotNull(result1);
        assertFalse(result1.getDraw());

        // El ganador debe ser Jugador1
        assertEquals("Jugador1", result1.getCharacter1().getName());
        assertEquals("Jugador2", result1.getCharacter2().getName());


        // Comprobar que hubo al menos una ronda
        assertTrue(calculator.getAndResetRounds() > 0);
    }

	
}