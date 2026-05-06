package domain;

import control.GameContext;
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
        c1.setMinionHealth(5);

        // Crear personaje 2 (débil)
        GameCharacter c2 = new Hunter();
        c2.setName("Jugador2");
        c2.setHealth(5);
        c2.setMinionHealth(5);

        // Crear contexto de juego
        GameContext context = new GameContext();
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
        GameContext result = calculator.battle(context);

        // Comprobaciones
        assertNotNull(result);
        assertFalse(result.isDraw());

        // El ganador debe ser Jugador1
        assertEquals("Jugador1", result.getCharacter1().getName());
        assertEquals("Jugador2", result.getCharacter2().getName());

        // Comprobar que hubo al menos una ronda
        assertTrue(calculator.getAndResetRounds() > 0);
    }
}