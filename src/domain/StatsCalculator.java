package domain;

import control.GameContext;

public class StatsCalculator {
	private int type;
    private final Algorithm DamageStrategy ;
    private final  Algorithm DefenseStrategy;
    private int rounds=0;
    private boolean minionsAlive=false;
    
	public StatsCalculator(int type){
    	this.DamageStrategy= new DamageAlgorithm(type);
	    this.DefenseStrategy= new DefenceAlgorithm(type);
		this.type = type;
    }

	public int calculatedamage(GameCharacter c) {
    return this.DamageStrategy.execute(c);
    }

	public int calculatedefense(GameCharacter c) {
    return this.DefenseStrategy.execute(c);
    }
    public int[] calculatehealth(GameCharacter c){
     int[] result={c.getHealth(),c.getMinionHealth()};
    return result;
    }

    public GameContext battle(GameContext g){
    boolean win=false;
    int     atk1=0;
    int     atk2=1;
    int[]   stats1=calculatehealth(g.getCharacter1());
    int[]   stats2=calculatehealth(g.getCharacter2());
    GameCharacter winner = null;
    GameCharacter looser = null;
    int     dmg1;
    int     dmg2;
    int     def1;
    int     def2;
    int     rounds=0;
    boolean survivalminioms;
    while (win==false){
        atk1=0;
        atk2=0;
        if (stats1[0]<=0 && stats2[0]<=0){
            win=true;
            g.setDraw(true);

        }
        else if (stats1[0]<=0){
            win=true;
            winner=g.getCharacter2();
            looser=g.getCharacter1();
            g.setDraw(false);
            this.rounds=rounds;
            this.minionsAlive=(stats2[1]>0);
        }
        else if (stats2[0]<=0){
            win=true;
            winner=g.getCharacter1();
            looser=g.getCharacter2();
            g.setDraw(false); 
            this.rounds=rounds;
            this.minionsAlive=(stats1[1]>0); 
        } else {
        dmg1= calculatedamage(g.getCharacter1());
        dmg2= calculatedamage(g.getCharacter2());
        def1= calculatedefense(g.getCharacter1());
        def2= calculatedefense(g.getCharacter2());
        for (int i = 0; i<dmg1;i++){
            int al = (int)(Math.random() * 6) + 1;
            if (al>=5 )  {
                atk1+=1;
            }
         
        }
        for (int i = 0; i<dmg2;i++){
            int al = (int)(Math.random() * 6) + 1;
            if (al>=5 )  {
                atk2+=1;
            }
         
        }
    if (atk1>def2){
        if (stats2[1]<=0){stats2[0]-=1; 
            System.out.println(g.getCharacter1().getName()+ " ha asestado un golpe");
            System.out.println(g.getCharacter2().getName()+ " tiene "+
            stats2[0]+ "puntos de vida");
            if (g.getCharacter2() instanceof Lycanthrope){
                ((Lycanthrope) g.getCharacter2()).setRage(((Lycanthrope) g.getCharacter2()).getRage() + 3);
            }
            if (g.getCharacter2() instanceof Hunter){
                ((Hunter)g.getCharacter2()).setAttitude(((Hunter)g.getCharacter2()).getAttitude() - 1);
            }
        }  
         else {
            stats2[1]-=1;
            System.out.println(g.getCharacter1().getName()+ " ha asestado un golpe");
            System.out.println("los esbirros de "+g.getCharacter2().getName()+ " tienen "+
            stats2[1]+ " puntos de vida");
          }
    if (g.getCharacter1() instanceof Vampire){
        ((Vampire) g.getCharacter1()).setBloodPoints(((Vampire) g.getCharacter1()).getBloodPoints()+4);
    }
    }
    if (atk2>def1){
        if (stats1[1]<=0){
            stats1[0]-=1;
            System.out.println(g.getCharacter2().getName() + " ha asestado un golpe");
            System.out.println(g.getCharacter1().getName()+ " tiene "+
            stats1[0]+ "puntos de vida");
            if (g.getCharacter1() instanceof Lycanthrope){
                ((Lycanthrope) g.getCharacter1()).setRage(((Lycanthrope) g.getCharacter1()).getRage() + 3);
            }
            if (g.getCharacter2() instanceof Hunter){
                ((Hunter)g.getCharacter2()).setAttitude(((Hunter)g.getCharacter2()).getAttitude() - 1);
            }
        }  
        
         else {
            stats1[1]-=1; 
            System.out.println(g.getCharacter2().getName() + " ha asestado un golpe");
            System.out.println("los esbirros de "+g.getCharacter1().getName()+ " tienen "+
            stats1[1]+ " puntos de vida");
         }
         if (g.getCharacter2() instanceof Vampire){
            ((Vampire) g.getCharacter2()).setBloodPoints(((Vampire) g.getCharacter2()).getBloodPoints()+4);
        }
        }
        }
    rounds+=1;
    }
    if (g.getCharacter1() instanceof Lycanthrope){
        ((Lycanthrope) g.getCharacter1()).setRage(0);
    }
    if (g.getCharacter2() instanceof Lycanthrope){
        ((Lycanthrope) g.getCharacter2()).setRage(0);
    }
    if (g.getCharacter1() instanceof Vampire){
        ((Vampire) g.getCharacter1()).setBloodPoints(0);
    }
    if (g.getCharacter2() instanceof Vampire){
        ((Vampire) g.getCharacter2()).setBloodPoints(0);
    }
    g.setCharacter1(winner);
    g.setCharacter2(looser);
    return g; 

    } 

}