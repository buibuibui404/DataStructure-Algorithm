import java.util.ArrayList;
import java.util.List;

public class InsertionSort{
    // [0,i) priority Q
    // sorted sequence implementation
    // [i,n) rest
    public static void insertionSort(List<Integer> list){
        for(int i = 0 ; i < list.size() ; i++){
            int s = i;
            int j = i;
            while(j < list.size()){
                if(list.get(s) > list.get(j)){
                    s = j;
                }
                j++;
            }
            int temp = list.get(i);
            list.set(i , list.get(s));
            list.set(s , temp);
        }
    }

    public static void main(String[] args){
        List<Integer> ls = new ArrayList<>();
        ls.add(9);
        ls.add(1);
        ls.add(6);
        ls.add(5);
        ls.add(4);
        ls.add(4);
        ls.add(8);
        ls.add(4);
        ls.add(2);
        System.out.println(ls);
        insertionSort(ls);
        System.out.println(ls);
    }
}

