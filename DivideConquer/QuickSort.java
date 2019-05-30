import java.util.*;

public class QuickSort{
    public static List<Integer> sort(List<Integer> nums){
        if(nums.size() <= 1){
            return nums;
        }

        int n = nums.get(0); // the random number
        
        List<Integer> left = new LinkedList<>();
        List<Integer> equals = new LinkedList<>();
        List<Integer> right = new LinkedList<>();  // LinkedList has a better performance than ArrayList, since a lot of numbers will be added to the list.
        
        for(int i : nums){
            if(i < n){
                left.add(i);
            }else if( i == n){
                equals.add(i);
            }else{
                right.add(i);
            }
        }

        return merge(sort(left), equals, sort(right));
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> equals, List<Integer> right){
        List<Integer> result = new LinkedList<>();

        left.forEach(i -> result.add(i));
        equals.forEach(i -> result.add(i));
        right.forEach(i -> result.add(i));

        return result;
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

        System.out.println(sort(ls));
    }
}