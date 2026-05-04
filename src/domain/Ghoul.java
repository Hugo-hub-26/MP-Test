
package domain;

/**
 *
 * @author Hugo Martínez González
 */
public class Ghoul implements Minion{
    
    private String name;
    private int health;
    private GameCharacter owner;
    private int dependence;
    
        public Ghoul(String name, int health, GameCharacter owner, int dependence) {
        this.name = name;
        this.health = health;
        this.owner = owner;
        this.dependence = dependence;
    }
    
    public GameCharacter getOwner() {
        return owner;
    }

    public int getDependence() {
        return dependence;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDependence(int dependence) {
        this.dependence = dependence;
    }
    
    
}
