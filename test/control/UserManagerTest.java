/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package control;

import domain.User;
import java.util.Collection;
import java.util.Map;
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
public class UserManagerTest {
	
	public UserManagerTest() {
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
	 * Test of load method, of class UserManager.
	 */
	@Test
	public void testLoad() {
		System.out.println("load");
		UserManager instance = new UserManager();
		instance.load();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of save method, of class UserManager.
	 */
	@Test
	public void testSave() {
		System.out.println("save");
		UserManager instance = new UserManager();
		instance.save();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findByNick method, of class UserManager.
	 */
	@Test
	public void testFindByNick() {
		System.out.println("findByNick");
		String nick = "";
		UserManager instance = new UserManager();
		User expResult = null;
		User result = instance.findByNick(nick);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of add method, of class UserManager.
	 */
	@Test
	public void testAdd() {
		System.out.println("add");
		User u = null;
		UserManager instance = new UserManager();
		instance.add(u);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of remove method, of class UserManager.
	 */
	@Test
	public void testRemove() {
		System.out.println("remove");
		String nick = "";
		UserManager instance = new UserManager();
		instance.remove(nick);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUsers method, of class UserManager.
	 */
	@Test
	public void testGetUsers() {
		System.out.println("getUsers");
		UserManager instance = new UserManager();
		Collection<User> expResult = null;
		Collection<User> result = instance.getUsers();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUsuarios method, of class UserManager.
	 */
	@Test
	public void testGetUsuarios() {
		System.out.println("getUsuarios");
		UserManager instance = new UserManager();
		Map<String, User> expResult = null;
		Map<String, User> result = instance.getUsuarios();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
