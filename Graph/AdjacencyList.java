import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList implements GraphInterface{

    private HashMap<Vertex,List<Vertex>> map;

    public AdjacencyList(){
        this.map = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex v, List<Vertex> ls) {
        if(ls == null){
            ls = new ArrayList<Vertex>();
        }
        this.map.put(v,ls);
        for(Vertex v1 : ls ){
            if(!this.map.containsKey(v1)){
                List<Vertex> temp = new ArrayList<>();
                temp.add(v);
                this.map.put(v1,temp);
            }else{
                this.map.get(v1).add(v);
            }
        }
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        if(this.map.containsKey(v1) && this.map.containsKey(v2)){
            this.map.get(v1).add(v2);
            this.map.get(v2).add(v1);
        }else if (this.map.containsKey(v1)){
            this.map.get(v1).add(v2);
            this.map.put(v2,new ArrayList<Vertex>());
            this.map.get(v2).add(v1);
        }else if (this.map.containsKey((v2))){
            this.map.get(v2).add(v1);
            this.map.put(v1,new ArrayList<Vertex>());
            this.map.get(v1).add(v2);
        }else{
            this.map.put(v1,new ArrayList<Vertex>());
            this.map.put(v2,new ArrayList<Vertex>());
            this.map.get(v1).add(v2);
            this.map.get(v2).add(v1);
        }
    }

    @Override
    public boolean removeVertex(Vertex v) {
        if(this.map.containsKey((v))){
            List<Vertex> ls = this.map.get((v));
            this.map.remove(v);
            for(Vertex v1 : ls){
                this.map.get(v1).remove(v);
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean removeEdge(Vertex v1, Vertex v2) {
        if(!this.map.containsKey(v1) || !this.map.containsKey(v2)){
            return false;
        }
        this.map.get(v1).remove(v2);
        this.map.get(v2).remove(v1);
        return true;
    }

    @Override
    public List<Vertex> getIncidentVertices(Vertex v1) {
        if(this.map.containsKey(v1)){
            return this.map.get(v1);
        }else{
            return null;
        }
    }

    @Override
    public List<Vertex> getVetices() {
        List<Vertex> ls = new ArrayList<>();
        this.map.forEach((k,v) -> ls.add(k));
        return ls;
    }

    @Override
    public String toString() {
        String s = "";
        this.map.forEach((k,v) -> System.out.println("Vertex name: "+k+" Incident vertices: "+v));
        return s;
    }

    public static List<List<Vertex>> BFS(GraphInterface g, Vertex v){
        HashMap<Vertex,Boolean> seen = new HashMap<>();
        HashMap<Vertex,Vertex> parent = new HashMap<>();

        for(Vertex v1 : g.getVetices()){
            seen.put(v1,false);
            parent.put(v1,null);
        }

        List<Vertex> current = new ArrayList<>();
        List<Vertex> next = new ArrayList<>();
        List<List<Vertex>> layers = new ArrayList<>();

        current.add(v);
        seen.put(v,true);

        while(current.size() != 0){
            for(Vertex v1 : current){
                for(Vertex v2 : g.getIncidentVertices(v1)){
                    if(!seen.get(v2)){
                        seen.put(v2,true);
                        next.add(v2);
                        parent.put(v2,v1);
                    }
                }
            }

            List<Vertex> temp = new ArrayList<>();
            current.forEach(x -> temp.add(x));
            layers.add(temp);
            current.clear();
            next.forEach(x -> current.add(x));
            next.clear();
        }

        return layers;
    }

    public static void DFS(GraphInterface g, Vertex v){
        HashMap<Vertex,Boolean> seen = new HashMap<>();
        HashMap<Vertex,Vertex> parent = new HashMap<>();

        for(Vertex v1 : g.getVetices()){
            seen.put(v1,false);
            parent.put(v1,null);
        }
        search(g,v,seen,parent);

        parent.forEach((key,value) -> System.out.println("The vertex is "+key+" Its parent is "+value));
    }

    public static void search(GraphInterface g, Vertex v, HashMap<Vertex,Boolean> seen, HashMap<Vertex,Vertex> parent){
        seen.put(v,true);
        for(Vertex v1 : g.getIncidentVertices(v)){
            if(!seen.get(v1)){
                parent.put(v1,v);
                search(g,v1,seen,parent);
            }
        }
    }

    public static void main(String[] args){
        GraphInterface g = new AdjacencyList();

        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v2, v4);
        g.addEdge(v3, v5);
        g.addEdge(v5, v6);
        g.addEdge(v5, v7);
        g.addEdge(v5, v4);
        System.out.println(g);

        System.out.println("BFS begin");
        BFS(g, v1).forEach(x -> System.out.println(x));;
        DFS(g,v1);
    }
}
