import java.lang.Comparable;
import java.util.*;

public class Pair<T> implements Comparable<Pair<T>>{
    private T key;
    private Integer value;

    public Pair(T key, Integer value){
        this.key = key;
        this.value = value;
    }

    public T getKey(){
        return this.key;
    }

    public Integer getValue(){
        return this.value;
    }

    public String toString(){
        return this.key + "  " + this.value + "  ";
    }

    public void SetValue(Integer i){
        this.value = i;
    }
    
    public int compareTo(Pair<T> p){
        return this.value - p.getValue();
    }
}