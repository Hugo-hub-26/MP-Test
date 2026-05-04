
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
        if (attitude<0){
            this.attitude = 0;            
        } 
        this.attitude = attitude;
    }
    
    @Override
    public Gift getAbility() {
        return (Gift) super.getAbility();
    }
    
}
