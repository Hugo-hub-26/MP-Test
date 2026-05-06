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
 * @author Miguel Pradillo Bartolomé
 */
public class CatalogueTest {
    
    private Catalogue instance;
    
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
        instance = new Catalogue();
        instance.load(); 
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getDiscipline method, of class Catalogue.
     */
    @Test
    public void testGetDiscipline() {
        System.out.println("getDiscipline");
        
        HashMap<String, Discipline> result = instance.getDiscipline();
        
        assertNotNull("El mapa de Disciplinas no debería ser nulo", result);
    }

    /**
     * Test of getGift method, of class Catalogue.
     */
    @Test
    public void testGetGift() {
        System.out.println("getGift");
        
        HashMap<String, Gift> result = instance.getGift();
        
        assertNotNull("El mapa de Dones (Gifts) no debería ser nulo", result);
    }

    /**
     * Test of getWill method, of class Catalogue.
     */
    @Test
    public void testGetWill() {
        System.out.println("getWill");
        
        HashMap<String, Will> result = instance.getWill();
        
        assertNotNull("El mapa de Voluntades (Wills) no debería ser nulo", result);
    }

    /**
     * Test of getArmor method, of class Catalogue.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        
        HashMap<String, Armor> result = instance.getArmor();
        
        assertNotNull("El mapa de Armaduras no debería ser nulo", result);
    }

    /**
     * Test of getWeapon method, of class Catalogue.
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        
        HashMap<String, Weapons> result = instance.getWeapon();
        
        assertNotNull("El mapa de Armas no debería ser nulo", result);
    }

    /**
     * Test of getStrength method, of class Catalogue.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        
        HashMap<String, Strength> result = instance.getStrength();
        
        assertNotNull("El mapa de Fortalezas no debería ser nulo", result);
    }

    /**
     * Test of getWeakness method, of class Catalogue.
     */
    @Test
    public void testGetWeakness() {
        System.out.println("getWeakness");
        
        HashMap<String, Weakness> result = instance.getWeakness();
        
        assertNotNull("El mapa de Debilidades no debería ser nulo", result);
    }
    
}