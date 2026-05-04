/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import domain.Player;
import domain.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ignacio Jerónimo Martín i.jeronimo.2024@alumnos.urjc.es
 */
public class UserManager {

	private static final String FILE_NAME = "./data/users.dat";
	private Map<String, User> usuarios;

	public UserManager(){
		load();	
	}

	// ========= LOAD =========
	@SuppressWarnings("unchecked")
	public void load() {
		File file = new File(FILE_NAME);

		if (!file.exists()) {
			usuarios = new HashMap<>();
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file))) {

			usuarios = (HashMap<String, User>) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			usuarios = new HashMap<>();
		}
		
		for (User u : usuarios.values()) {
    		if (u instanceof Player p) {
        		Player.updateNextRegisterNumber(p.getRegisterNumber());
    		}
		}

	}

	// ========= SAVE =========
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(FILE_NAME))) {

			oos.writeObject(usuarios);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public User findByNick(String nick) {
		return usuarios.get(nick);
	}

	public void add(User u) {
		usuarios.put(u.getNick(), u);
	}

	public void remove(String nick) {
		usuarios.remove(nick);
	}

	public Collection<User> getUsers() {
		return usuarios.values();
	}

    public void setUsuarios(Map<String, User> usuarios) {
        this.usuarios = usuarios;
    }
	
}
