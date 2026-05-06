/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package control;

import domain.Player;
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
 * @author Miguel Pradillo Bartolomé
 */
public class UserManagerTest {
    
    private UserManager instance;
    private Player testUser;
    
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
        instance = new UserManager();
        testUser = new Player("Miguel", "miguel_test", "pass123");
    }
    
    @After
    public void tearDown() {
        instance = null;
        testUser = null;
    }

    /**
     * Test of load method, of class UserManager.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        try {
            instance.load();
            assertTrue("El método load se ha ejecutado sin excepciones", true);
        } catch (Exception e) {
            fail("El método load ha lanzado una excepción: " + e.getMessage());
        }
    }

    /**
     * Test of save method, of class UserManager.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        try {
            instance.add(testUser);
            instance.save();
            assertTrue("El método save se ha ejecutado sin excepciones", true);
        } catch (Exception e) {
            fail("El método save ha lanzado una excepción: " + e.getMessage());
        }
    }

    /**
     * Test of findByNick method, of class UserManager.
     */
    @Test
    public void testFindByNick() {
        System.out.println("findByNick");
        instance.add(testUser);
        
        String nick = "miguel_test";
        User expResult = testUser;
        User result = instance.findByNick(nick);
        
        // Verificamos que devuelve el usuario correcto
        assertEquals("Debería encontrar al usuario recién insertado", expResult, result);
        
        // Verificamos el comportamiento al buscar un nick que no existe
        User nullResult = instance.findByNick("usuario_inventado");
        assertNull("Debería devolver null si el nick no existe", nullResult);
    }

    /**
     * Test of add method, of class UserManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        instance.add(testUser);
        
        User result = instance.findByNick("miguel_test");
        assertNotNull("El usuario no debería ser null tras añadirlo", result);
        assertEquals("La instancia recuperada debe ser la misma que la añadida", testUser, result);
    }

    /**
     * Test of remove method, of class UserManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        
        instance.add(testUser);
        assertNotNull(instance.findByNick("miguel_test"));
        
        String nick = "miguel_test";
        instance.remove(nick);
        
        User result = instance.findByNick(nick);
        assertNull("El usuario debería haber sido eliminado del sistema", result);
    }

    /**
     * Test of getUsers method, of class UserManager.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        
        instance.add(testUser);
        
        Collection<User> result = instance.getUsers();
        assertNotNull("La colección devuelta no debe ser nula", result);
        assertTrue("La colección debe contener al usuario de prueba", result.contains(testUser));
    }

    /**
     * Test of getUsuarios method, of class UserManager.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");
        
        instance.add(testUser);
        
        Map<String, User> result = instance.getUsuarios();
        assertNotNull("El mapa devuelto no debe ser nulo", result);
        assertTrue("El mapa debe contener la clave del nick del usuario", result.containsKey("miguel_test"));
        assertEquals("El valor mapeado debe corresponder al usuario insertado", testUser, result.get("miguel_test"));
    }
    
}