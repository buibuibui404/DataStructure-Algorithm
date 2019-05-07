import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class WeightedGraph{
    private HashMap<Vertex,List<Vertex>> map;
    private HashMap<Vertex,HashMap<Vertex,Integer>> weightMap;

    public WeightedGraph(){
        this.map = new HashMap<>();
        this.weightMap = new HashMap<>();
    }

    public void addVertex(Vertex v, List<Vertex> ls, HashMap<Vertex,Integer> weights) {
        if(ls == null){
            ls = new ArrayList<Vertex>();
        }

        if(weights == null){
            weights = new HashMap<Vertex,Integer>();
        }

        this.map.put(v,ls);
        this.weightMap.put(v, weights);

        for(Vertex v1 : ls ){
            if(!this.map.containsKey(v1)){
                List<Vertex> temp = new ArrayList<>();
                HashMap<Vertex,Integer> temp2 = new HashMap<>();

                temp.add(v);
                temp2.put(v, weights.get(v1));

                this.map.put(v1,temp);
                this.weightMap.put(v1, temp2);

            }else{
                this.map.get(v1).add(v);
                this.weightMap.get(v1).put(v, weights.get(v1));
            }
        }
    }

    public void addEdge(Vertex v1, Vertex v2, Integer weight) {
        if(this.map.containsKey(v1) && this.map.containsKey(v2)){
            this.map.get(v1).add(v2);
            this.map.get(v2).add(v1);
            this.weightMap.get(v1).put(v2, weight);
            this.weightMap.get(v2).put(v1, weight);
        }else if (this.map.containsKey(v1)){
            this.map.get(v1).add(v2);
            this.weightMap.get(v1).put(v2, weight);

            this.map.put(v2,new ArrayList<Vertex>());
            this.map.get(v2).add(v1);
            this.weightMap.put(v2, new HashMap<Vertex, Integer>());
            this.weightMap.get(v2).put(v1, weight);
        }else if (this.map.containsKey((v2))){
            this.map.get(v2).add(v1);
            this.weightMap.get(v2).put(v1, weight);

            this.map.put(v1,new ArrayList<Vertex>());
            this.map.get(v1).add(v2);
            this.weightMap.put(v1, new HashMap<>());
            this.weightMap.get(v1).put(v1, weight);
        }else{
            this.map.put(v1,new ArrayList<Vertex>());
            this.map.put(v2,new ArrayList<Vertex>());
            this.map.get(v1).add(v2);
            this.map.get(v2).add(v1);

            this.weightMap.put(v1, new HashMap<Vertex, Integer>());
            this.weightMap.put(v2, new HashMap<Vertex, Integer>());
            this.weightMap.get(v1).put(v2, weight);
            this.weightMap.get(v2).put(v1, weight);
        }
    }

    public boolean removeVertex(Vertex v) {
        if(this.map.containsKey((v))){
            List<Vertex> ls = this.map.get((v));
            this.map.remove(v);
            this.weightMap.remove(v);
            for(Vertex v1 : ls){
                this.map.get(v1).remove(v);
                this.weightMap.get(v1).remove(v);
            }
        }else{
            return false;
        }
        return true;
    }

    public boolean removeEdge(Vertex v1, Vertex v2) {
        if(!this.map.containsKey(v1) || !this.map.containsKey(v2)){
            return false;
        }
        this.map.get(v1).remove(v2);
        this.map.get(v2).remove(v1);
        this.weightMap.get(v1).remove(v2);
        this.weightMap.get(v2).remove(v1);
        return true;
    }

    public List<Vertex> getIncidentVertices(Vertex v1) {
        if(this.map.containsKey(v1)){
            return this.map.get(v1);
        }else{
            return null;
        }
    }

    public List<Vertex> getVetices() {
        List<Vertex> ls = new ArrayList<>();
        this.map.forEach((k,v) -> ls.add(k));
        return ls;
    }

    public String toString() {
        this.map.forEach((k,v) -> System.out.println("Vertex name: "+k+" Incident vertices: "+v+" Weights: "+this.weightMap.get(k)));
        return "";
    }

    public static int Dijkstra(Graph g, Vertex v1, Vertex v2){
        
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph();

        Vertex v1 = new Vertex("a");
        Vertex v2 = new Vertex("b");
        Vertex v3 = new Vertex("c");
        Vertex v4 = new Vertex("d");
        Vertex v5 = new Vertex("e");
        Vertex v6 = new Vertex("f");
        Vertex v7 = new Vertex("g");
        Vertex v8 = new Vertex("h");
        Vertex v9 = new Vertex("i");
        Vertex v10 = new Vertex("g");

        g.addEdge(v1, v2, 9);
        g.addEdge(v1, v3, 14);
        g.addEdge(v1, v4, 15);
        g.addEdge(v2, v6, 24);
        g.addEdge(v3, v6, 18);
        g.addEdge(v3, v5, 30);
        g.addEdge(v3, v4, 5);
        g.addEdge(v4, v5, 5);
        g.addEdge(v4, v8, 44);
        g.addEdge(v5, v6, 2);
        g.addEdge(v5, v7, 11);
        g.addEdge(v5, v8, 16);
        g.addEdge(v7, v6, 6);
        g.addEdge(v7, v8, 6);
        g.addEdge(v8, v6, 19);

        System.out.println(g);
    }
}