import java.util.*;
import java.lang.*;

public class BinarySearch{
    public static int search(List<Integer> ls, int aim){
        int left = 0;
        int right = ls.size()-1;
        while((right + left )/2 != 0 && (right + left)/2 != left){
            int middle = ls.get((left+right)/2);
            if(middle == aim){
                return (left+right)/2;
            }else if(middle > aim){
                right = (left+right)/2;
            }else{
                left = (left+right)/2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        ls.add(2);
        ls.add(3);
        ls.add(4);
        ls.add(5);
        ls.add(6);
        ls.add(7);
        ls.add(8);

        System.out.println(ls);
        System.out.println(search(ls,3));
        System.out.println(search(ls,0));
        System.out.println(search(ls,7));
        System.out.println(search(ls,10));
    }
}