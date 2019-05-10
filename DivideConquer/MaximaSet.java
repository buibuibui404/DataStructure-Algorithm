import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximaSet{
    public static List<Pair> maximaSet(List<Pair> ls){
        Collections.sort(ls);

        if(ls.size() < 2){
            return ls;
        }

        int middle = ls.size()/2;
        List<Pair> left = new ArrayList<>();
        List<Pair> right = new ArrayList<>();

        for(int i = 0; i < middle; i++){
            left.add(ls.get(i));
        }
        for(int i = middle; i < ls.size(); i++){
            right.add(ls.get(i));
        }

        List<Pair> maxSetLeft = maximaSet(left);
        List<Pair> maxSetRight = maximaSet(right);

        return merge(maxSetLeft, maxSetRight);

    }

    public static List<Pair> merge(List<Pair> left, List<Pair> right){
        int minY;
        List<Pair> result = new ArrayList<>();

        if(right.size() > 0){
            minY = right.get(0).getY();
        }else{
            return left;
        }

        left.stream().filter(x -> x.getY() > minY).forEach(x -> result.add(x));
        right.forEach(x -> result.add(x));

        return result;
    }

    public static void main(String[] args) {
        List<Pair> ls = new ArrayList<>();
        ls.add(new Pair(1, 5));
        ls.add(new Pair(2, 5));
        ls.add(new Pair(3, 5));
        ls.add(new Pair(4, 5));
        ls.add(new Pair(5, 5));
        ls.add(new Pair(9, 5));
        ls.add(new Pair(7, 5));
        ls.add(new Pair(8, 6));
        System.out.println(ls);
        System.out.println();
        System.out.println(maximaSet(ls));

    }
}
