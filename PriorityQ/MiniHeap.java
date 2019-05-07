import java.util.ArrayList;
import java.util.List;


public class MiniHeap implements Pq{
    private List<Integer> heap;

    public Heap(){
        this.heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean insert(int p) {
        this.heap.add(p);
        int index = this.heap.size() - 1;
        
        if(index == 0){
            return true;
        }

        int parent = (index-1)/2;
        while(this.heap.get(parent) > p){
            this.heap.set(index, this.heap.get(parent));
            this.heap.set(parent, p);
            index = parent;
            if(index == 0){
                return true;
            }
            parent = (index-1)/2;
        }
        return true;
    }

    @Override
    public Integer min() {
        if(this.heap.size() != 0){
            return this.heap.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer removeMin() {
        if(this.size() == 0){
            return null;
        }
        int result = this.heap.get(0);
        int last = this.heap.get(this.size()-1);
        this.heap.set(0, last);
        this.heap.remove(this.size()-1);

        int index = 0;
        int left = 2*index+1;
        int right = 2*index+2;

        while(true){
            if(this.size()-1 < left){
                return result;
            }else if(this.size()-1 < right && this.heap.get(left) < last){
                this.heap.set(index, this.heap.get(left));
                this.heap.set(left, last);
                return result;
            }else if(this.size()-1 < right){
                return result;
            }else if(this.heap.get(left) > this.heap.get(right) && this.heap.get(index) > this.heap.get(right)){
                this.heap.set(index, this.heap.get(right));
                this.heap.set(right, last);

                index = right;
                left = 2*index+1;
                right = 2*index+2;
            }else if(this.heap.get(left) < this.heap.get(right) && this.heap.get(index) > this.heap.get(left)){
                this.heap.set(index, this.heap.get(left));
                this.heap.set(left, last);

                index = left;
                left = 2*index+1;
                right = 2*index+2;
            }else{
                return result;
            }
            
        }
    }

    @Override
    public boolean remove(int p) {
        Integer min;
        if(this.size() == 0){
            return false;
        }

        Integer index = this.find(p,0);
        if(index == null){
            return false;
        }

        min = this.heap.get(0);
        for(int i = 1; i < this.heap.size(); i++){
            if(this.heap.get(i) < min){
                min = this.heap.get(i);
            }
        }
        min -= 1;
        this.heap.set(index, min);
        this.moveDown(index, min);
        this.heap.remove(min);
        return true;
    }

    @Override
    public boolean replaceValue(int p, int i) {
        Integer index = this.find(p, 0);
        if(index != null){
            Integer value = this.heap.get(index);
            this.heap.set(index, i);
            if(value > i){
                this.moveUp(index, i);
            }else{
                this.moveDown(index, i);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        System.out.println(this.heap);
        return "";
    }

    public Integer find(int p, int index){
        int left = 2*index + 1;
        int right = 2*index + 2;
        if(this.heap.get(index) == p){
            return index;
        }

        if(this.size()-1 < left){
            return null;
        }else if(this.size()-1 < right){
            return find(p, left);
        }else{
            Integer l = find(p, left);
            Integer r = find(p, right);
            if(l == null && r == null){
                return null;
            }else if(l != null){
                return l;
            }else{
                return r;
            }
        }
    }

    public Integer moveUp(int index, int v){
        Integer parent = (index-1)/2;
        if(parent < 0){
            return index;
        }

        if(this.heap.get(parent) > v){
            this.heap.set(index, this.heap.get(parent));
            this.heap.set(parent, v);
            return this.moveUp(parent, v);
        }else{
            return index;
        }
    }

    public Integer moveDown(int index, int v){
        Integer left = 2*index + 1;
        Integer right = 2*index + 2;
        if(right <= this.heap.size()-1){
            if(this.heap.get(right) < this.heap.get(left) && this.heap.get(right) < v){
                this.heap.set(index, this.heap.get(right));
                this.heap.set(right, v);
                return this.moveDown(right, v);
            }else if(this.heap.get(left) < this.heap.get(right) && this.heap.get(left) < v){
                this.heap.set(index, this.heap.get(left));
                this.heap.set(left, v);
                return this.moveDown(left, v);
            }else{
                return index;
            }
        }else if(left <= this.heap.size()-1 && this.heap.get(left) < v){
            this.heap.set(index, this.heap.get(left));
            this.heap.set(left, v);
            return this.moveDown(index, v);
        }else{
            return index;
        }
    }

    public static void main(String[] args){
        Heap h = new Heap();
        h.insert(10);
        h.insert(9);
        h.insert(4);
        h.insert(2);
        h.insert(8);
        h.insert(7);
        h.insert(6);
        h.insert(5);
        h.insert(3);
        h.insert(1);


        System.out.println(h);
        
        System.out.println(h.replaceValue(1, 11));
        System.out.println(h);
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);
        
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);

        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);
        
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);
        
        h.removeMin();
        h.remove(10);
        System.out.println(h.find(10,0));
        System.out.println(h);
        
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);
        
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h.replaceValue(10, 100));
        System.out.println(h);
        
        h.removeMin();
        System.out.println(h.find(10,0));
        System.out.println(h);
        
    }
}