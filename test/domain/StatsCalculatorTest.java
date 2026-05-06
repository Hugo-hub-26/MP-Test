/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package domain;

import control.GameContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class StatsCalculatorTest {
	
	public StatsCalculatorTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of resetRounds method, of class StatsCalculator.
	 */
	@Test
	public void testResetRounds() {
		System.out.println("resetRounds");
		StatsCalculator instance = null;
		instance.resetRounds();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAndResetRounds method, of class StatsCalculator.
	 */
	@Test
	public void testGetAndResetRounds() {
		System.out.println("getAndResetRounds");
		StatsCalculator instance = null;
		int expResult = 0;
		int result = instance.getAndResetRounds();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of resetMinionsAlive method, of class StatsCalculator.
	 */
	@Test
	public void testResetMinionsAlive() {
		System.out.println("resetMinionsAlive");
		StatsCalculator instance = null;
		instance.resetMinionsAlive();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAndResetMinionsAlive method, of class StatsCalculator.
	 */
	@Test
	public void testGetAndResetMinionsAlive() {
		System.out.println("getAndResetMinionsAlive");
		StatsCalculator instance = null;
		boolean expResult = false;
		boolean result = instance.getAndResetMinionsAlive();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of calculatedamage method, of class StatsCalculator.
	 */
	@Test
	public void testCalculatedamage() {
		System.out.println("calculatedamage");
		GameCharacter c = null;
		StatsCalculator instance = null;
		int expResult = 0;
		int result = instance.calculatedamage(c);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of calculatedefense method, of class StatsCalculator.
	 */
	@Test
	public void testCalculatedefense() {
		System.out.println("calculatedefense");
		GameCharacter c = null;
		StatsCalculator instance = null;
		int expResult = 0;
		int result = instance.calculatedefense(c);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of calculatehealth method, of class StatsCalculator.
	 */
	@Test
	public void testCalculatehealth() {
		System.out.println("calculatehealth");
		GameCharacter c = null;
		StatsCalculator instance = null;
		int[] expResult = null;
		int[] result = instance.calculatehealth(c);
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of battle method, of class StatsCalculator.
	 */
	@Test
	public void testBattle() {
		System.out.println("battle");
		GameContext g = null;
		StatsCalculator instance = null;
		GameContext expResult = null;
		GameContext result = instance.battle(g);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
