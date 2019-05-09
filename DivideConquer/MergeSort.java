import java.util.*;

public class MergeSort{
    public static List<Integer> mergeSort(List<Integer> ls){
        if(ls.size() < 2){
            return ls;
        }

        int middle = ls.size()/2;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for(int i = 0; i < middle; i++){
            left.add(ls.get(i));
        }
        for(int i = middle; i < ls.size(); i++){
            right.add(ls.get(i));
        }

        List<Integer> sortedLeft = mergeSort(left);
        List<Integer> sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right){

        int l = 0; // left pointer
        int r = 0; // right pointer
        int len = left.size() + right.size();
        List<Integer> result = new ArrayList<>();
        
        while( l + r != len ){
            if(r >= right.size() || l < left.size() && left.get(l) > right.get(r)){
                result.add(left.get(l));
                l++;
            }else if(r < right.size()){
                result.add(right.get(r));
                r++;
            }
        }
        return result;
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
        System.out.println(mergeSort(ls));
    }
}