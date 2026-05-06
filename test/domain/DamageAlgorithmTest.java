/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package domain;

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
public class DamageAlgorithmTest {
	
	public DamageAlgorithmTest() {
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
	 * Test of execute method, of class DamageAlgorithm.
	 */
	@Test
	public void testExecute() {
		System.out.println("execute");
		GameCharacter c = null;
		DamageAlgorithm instance = new DamageAlgorithm();
		int expResult = 0;
		int result = instance.execute(c);
		assertEquals(expResult, result);
	}

    @Test
    public void testHunterDamage() {
        // Arrange
        Hunter hunter = new Hunter();
        hunter.setPower(10);
        hunter.setAttitude(3);

        Ability ability = new Ability();
        ability.setAttackValue(5);
        hunter.setAbility(ability);

        Weapon w1 = new Weapon();
        w1.setAttackModifier(2);
        Weapon w2 = new Weapon();
        w2.setAttackModifier(1);

        hunter.setPrincipalWeaponOne(w1);
        hunter.setPrincipalWeaponTwo(w2);

        hunter.setStrength(new java.util.HashMap<>());
        hunter.setWeakness(new java.util.HashMap<>());

        DamageAlgorithm algorithm = new DamageAlgorithm(0);

        // Act
        int result = algorithm.execute(hunter);

        // Assert
        int expected = 10 + 5 + 2 + 1 + 3;
        assertEquals(expected, result);
    }

    @Test
    public void testLycanthropeDamageWithRageAboveThreshold() {
        Lycanthrope lycan = new Lycanthrope();
        lycan.setPower(10);
        lycan.setRage(8);

        Ability ability = new Ability();
        ability.setAttackValue(4);
        ability.setRageValue(5);
        lycan.setAbility(ability);

        Weapon w1 = new Weapon();
        w1.setAttackModifier(2);
        Weapon w2 = new Weapon();
        w2.setAttackModifier(2);

        lycan.setPrincipalWeaponOne(w1);
        lycan.setPrincipalWeaponTwo(w2);

        lycan.setStrength(new java.util.HashMap<>());
        lycan.setWeakness(new java.util.HashMap<>());

        DamageAlgorithm algorithm = new DamageAlgorithm(0);

        int result = algorithm.execute(lycan);

        int expected = 10 + 4 + 2 + 2 + 8;
        assertEquals(expected, result);
    }

    @Test
    public void testVampireDamageWithLowBlood() {
        Vampire vampire = new Vampire();
        vampire.setPower(10);
        vampire.setBloodPoints(2);

        Ability ability = new Ability();
        ability.setAttackValue(5);
        ability.setBloodValue(4);
        vampire.setAbility(ability);

        Weapon w1 = new Weapon();
        w1.setAttackModifier(1);
        Weapon w2 = new Weapon();
        w2.setAttackModifier(1);

        vampire.setPrincipalWeaponOne(w1);
        vampire.setPrincipalWeaponTwo(w2);

        vampire.setStrength(new java.util.HashMap<>());
        vampire.setWeakness(new java.util.HashMap<>());

        DamageAlgorithm algorithm = new DamageAlgorithm(0);

        int result = algorithm.execute(vampire);

        int expected = 10 + 5 + 1 + 1 - 5; // pierde attackValue
        assertEquals(expected, result);
    }

    @Test
    public void testStrengthApplied() {
        Hunter hunter = new Hunter();
        hunter.setPower(5);

        Ability ability = new Ability();
        ability.setAttackValue(5);
        hunter.setAbility(ability);

        Weapon w1 = new Weapon();
        w1.setAttackModifier(0);
        Weapon w2 = new Weapon();
        w2.setAttackModifier(0);

        hunter.setPrincipalWeaponOne(w1);
        hunter.setPrincipalWeaponTwo(w2);

        Strength strength = new Strength(1, 3); // type=1, value=3
        java.util.Map<String, Strength> strengths = new java.util.HashMap<>();
        strengths.put("s1", strength);

        hunter.setStrength(strengths);
        hunter.setWeakness(new java.util.HashMap<>());

        DamageAlgorithm algorithm = new DamageAlgorithm(1);

        int result = algorithm.execute(hunter);

        int expected = 5 + 5 + 3;
        assertEquals(expected, result);
    }

}

