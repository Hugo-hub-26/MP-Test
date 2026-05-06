/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package command;

import control.AuthenticationManager;
import control.ChallengeManager;
import control.GameContext;
import control.UserManager;
import domain.Catalogue;
import domain.ChallengeMediator;
import domain.User;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
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
public class RegisterCommandTest {
	
	public RegisterCommandTest() {
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

    private void withInput(String data, Runnable test) {
        InputStream backup = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.run();
        } finally {
            System.setIn(backup);
        }
    }

    @Test
    public void registerCommand_registraUsuarioCorrectamente() {

        String input =
                "test\n" +
                "test\n" +
                "password\n";

        withInput(input, new Runnable() {
			@Override
			public void run() {
				UserManager userManager = new UserManager();
				AuthenticationManager authManager = new AuthenticationManager(userManager);
				Catalogue c = null;
				try {
					c = new Catalogue();
				} catch (FileNotFoundException ex) {
					System.getLogger(RegisterCommandTest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
				}
				GameContext context = new GameContext(new Scanner(System.in), c);
				
				RegisterCommand command = new RegisterCommand(context, userManager, authManager, new ChallengeMediator(new ChallengeManager()));
				
				command.execute();
				
				User u = userManager.findByNick("ignacio123");
				assertNotNull("El usuario debería haberse registrado", u);
			}
		});
    }
}