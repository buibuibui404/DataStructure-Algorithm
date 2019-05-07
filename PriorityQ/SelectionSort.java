import java.util.List;
import java.util.ArrayList;

public class SelectionSort{
    //  [0,i) is sorted 
    //  unsorted sequence implementation
    //  [i,n] is priority queue
    public static void selectionSort(List<Integer> list){
        for(int i = 0 ; i < list.size() ; i++){
            int s = i;
            for(int j = i ; j < list.size() ; j++){
                if(list.get(s) > list.get(j)){
                    s = j;
                }
            }
            int temp = list.get(i);
            list.set(i, list.get(s));
            list.set(s, temp);
        }
    }

    public static void main(String[] args){
        List<Integer> ls = new ArrayList<>();
        ls.add(9);
        ls.add(6);
        ls.add(7);
        ls.add(3);
        ls.add(5);
        ls.add(8);
        ls.add(1);
        ls.add(4);
        ls.add(2);
        ls.add(7);
        ls.add(7);
        System.out.println(ls);
        selectionSort(ls);
        System.out.println(ls);

    }
}