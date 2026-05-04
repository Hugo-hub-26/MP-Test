

package domain;


/**
 *
 * @author Hugo Martínez González
 */
public class Lycanthrope extends GameCharacter {
    private int height;
    private int weight;
    private int rage =0;
    private boolean mode = false;
    
    public int getHeigth() {
        return height;
    }

    public boolean isMode() {
        return mode;
    }

    public int getWeigth() {
        return weight;
    }

    public int getRage() {
        return rage;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight (int weight) {
        this.weight = weight;
    }
    
    @Override
    public Will getAbility() {
        return (Will) super.getAbility();
    }
    
    public void Transformation(){
        if (!isMode()){
            setHeight(height+75);
            setWeight(weight+100);
            mode = true;
        }else{
            setHeight(height-75);
            setWeight(weight-100);
            mode = false;
        }
    }

    public void setRage(int rage) {
        if(rage>3){
            this.rage=3;
        }
        this.rage = rage;
    }
    
}
