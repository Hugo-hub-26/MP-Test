
package domain;


/**
 *
 * @author Hugo Martínez González
 */
public class Hunter extends GameCharacter {
    private int attitude = 3;

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }
    
    @Override
    public Gift getAbility() {
        return (Gift) super.getAbility();
    }
    
}
