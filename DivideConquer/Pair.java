import java.lang.Comparable;

public class Pair implements Comparable<Pair>{

    private int x;
    private int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }

    public int compareTo(Pair p){
        return this.x - p.getX();
    }

    public String toString(){
        return "X: "+x+" Y: "+y+"\n";
    }
}