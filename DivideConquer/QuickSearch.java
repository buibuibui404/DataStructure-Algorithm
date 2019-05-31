import java.util.*;

// given an unsorted list holding n numbers and a integer k, find the kth largest number

public class QuickSearch{
    public static int search(List<Integer> nums, int k){
        if(nums.size() == 1){
            return nums.get(0);
        }
        
        int n = nums.get(0); // the random number
        
        List<Integer> left = new LinkedList<>();
        List<Integer> equals = new LinkedList<>();
        List<Integer> right = new LinkedList<>();  // LinkedList will have a better performance than ArrayList, since a lot of numbers 
                                                    //may be added to the list.
        
        for(int i : nums){
            if(i < n){
                left.add(i);
            }else if( i == n){
                equals.add(i);
            }else{
                right.add(i);
            }
        }
        
        if(right.size() >= k){
            return search( right ,k);
        }else if(k <= right.size() + equals.size()){
            return n;
        }else{
            return search( left ,k - right.size() - equals.size());
        }
        
    }

    public static void main(String[] args){
        List<Integer> ls = new LinkedList<>();
        ls.add(5);
        ls.add(7);
        ls.add(3);
        ls.add(2);
        ls.add(13);
        ls.add(35);
        ls.add(72);

        // [5, 7, 3, 2, 13, 35, 72]

        System.out.println(search(ls, 2));
    }
}
