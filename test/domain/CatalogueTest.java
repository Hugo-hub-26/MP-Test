/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package domain;

import java.util.HashMap;
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
public class CatalogueTest {
	
	public CatalogueTest() {
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
	 * Test of getDiscipline method, of class Catalogue.
	 */
	@Test
	public void testGetDiscipline() {
		System.out.println("getDiscipline");
		Catalogue instance = new Catalogue();
		HashMap<String, Discipline> expResult = null;
		HashMap<String, Discipline> result = instance.getDiscipline();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getGift method, of class Catalogue.
	 */
	@Test
	public void testGetGift() {
		System.out.println("getGift");
		Catalogue instance = new Catalogue();
		HashMap<String, Gift> expResult = null;
		HashMap<String, Gift> result = instance.getGift();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getWill method, of class Catalogue.
	 */
	@Test
	public void testGetWill() {
		System.out.println("getWill");
		Catalogue instance = new Catalogue();
		HashMap<String, Will> expResult = null;
		HashMap<String, Will> result = instance.getWill();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getArmor method, of class Catalogue.
	 */
	@Test
	public void testGetArmor() {
		System.out.println("getArmor");
		Catalogue instance = new Catalogue();
		HashMap<String, Armor> expResult = null;
		HashMap<String, Armor> result = instance.getArmor();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getWeapon method, of class Catalogue.
	 */
	@Test
	public void testGetWeapon() {
		System.out.println("getWeapon");
		Catalogue instance = new Catalogue();
		HashMap<String, Weapons> expResult = null;
		HashMap<String, Weapons> result = instance.getWeapon();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStrength method, of class Catalogue.
	 */
	@Test
	public void testGetStrength() {
		System.out.println("getStrength");
		Catalogue instance = new Catalogue();
		HashMap<String, Strength> expResult = null;
		HashMap<String, Strength> result = instance.getStrength();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getWeakness method, of class Catalogue.
	 */
	@Test
	public void testGetWeakness() {
		System.out.println("getWeakness");
		Catalogue instance = new Catalogue();
		HashMap<String, Weakness> expResult = null;
		HashMap<String, Weakness> result = instance.getWeakness();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
