import java.util.*;

public class WeightedGraph implements GraphInterface {

    private HashMap<Integer, Vertex> adjacencyList;
    private int n;
    private ArrayList<Integer> idList;

    public WeightedGraph(){
        this.adjacencyList = new HashMap<>();
        this.n = 0; // the number of edges
        this.idList = new ArrayList<>(); // to store different id
    }

    @Override
    public void addEdge(int a, int b, int Weight) {
        if(this.adjacencyList.containsKey(a) && this.adjacencyList.containsKey(b)){
            Vertex v1 = this.adjacencyList.get(a);
            Vertex v2 = this.adjacencyList.get(b);
            Edge e1 = new Edge(v1, v2, Weight);
            if(!this.adjacencyList.get(a).getEdges().containsKey(b)){
                n++;
            }
            v1.addEdge(v2, e1);
            v2.addEdge(v1, e1);
        }else if(this.adjacencyList.containsKey(a)){
            this.idList.add(b);
            Vertex v1 = this.adjacencyList.get(a);
            Vertex v2 = new Vertex(b);
            Edge e1 = new Edge(v1, v2, Weight);
            v1.addEdge(v2, e1);
            v2.addEdge(v1, e1);
            this.adjacencyList.put(b, v2);
            n++;
        }else if(this.adjacencyList.containsKey(b)){
            this.idList.add(a);
            Vertex v2 = this.adjacencyList.get(b);
            Vertex v1 = new Vertex(a);
            Edge e1 = new Edge(v1, v2, Weight);
            v1.addEdge(v2, e1);
            v2.addEdge(v1, e1);   
            this.adjacencyList.put(a, v1);
            n++;
        }else{
            this.idList.add(a);
            this.idList.add(b);
            Vertex v1 = new Vertex(a);
            Vertex v2 = new Vertex(b);
            Edge e1 = new Edge(v1, v2, Weight);
            v1.addEdge(v2, e1);
            v2.addEdge(v1, e1);
            this.adjacencyList.put(a, v1);
            this.adjacencyList.put(b, v2);
            n++;
        }
        
    }

    @Override
    public Edge[] edges() {
        Edge[] result = new Edge[n];
        for(int i = 0; i < this.idList.size(); i++){
            int theMini = i;
            for(int j = i; j < this.idList.size(); j++){
                if(this.idList.get(theMini) > this.idList.get(j)){
                    theMini = j;
                }
            }
            int temp = this.idList.get(theMini);
            this.idList.set(theMini, this.idList.get(i));
            this.idList.set(i, temp);
        }

        ArrayList<Edge> ls = new ArrayList<>();
        for(int i: this.idList){
            Vertex v = this.adjacencyList.get(i);
            HashMap<Integer, Edge> edges = v.getEdges();
            for(int j: this.idList){
                if(edges.containsKey(j)){
                    Edge e = edges.get(j);
                    if(!ls.contains(e)){
                        ls.add(e);
                    }
                }else{
                    continue;
                }
            }
        }
        return ls.toArray(result);
    }

    public HashMap<Integer, Integer> Dijkstra(int s, int en){
        ArrayList<Pair<Integer>> ls = new ArrayList<>();
        HashMap<Integer, Integer> D = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();

        for(int i: this.idList){
            if(i == s){
                continue;
            }
            ls.add(new Pair<Integer>(i, Integer.MAX_VALUE));
            D.put(i, Integer.MAX_VALUE);
            parent.put(i, null);
        }
        ls.add(new Pair<Integer>(s, 0));
        D.put(s,0);
        Collections.sort(ls);

        while(ls.size() > 0){
            Pair<Integer> u = ls.get(0);
            u.getKey();
            ls.remove(0);
            for(int i: this.adjacencyList.get(u.getKey()).getNeighbors()){
                int newWeight = D.get(u.getKey()) + this.adjacencyList.get(u.getKey()).getEdgeTo(this.adjacencyList.get(i)).getWeight();
                if(newWeight < D.get(i)){
                    D.put(i, newWeight);
                    for(Pair<Integer> p: ls){
                        if(p.getKey() == i){
                            p.SetValue(newWeight);
                            break;
                        }
                    }
                    Collections.sort(ls);
                    parent.put(i, u.getKey());
                }
            }
        }
        return parent;
    }

    public ArrayList<Edge> Prim(){
        int V = idList.size(); // the number of vertices
        ArrayList<Integer> S = new ArrayList<>(); // to store the vertices in S
        ArrayList<Edge> T = new ArrayList<>();

        S.add(this.idList.get(0));

        while(V > S.size()){
            ArrayList<Edge> temp3 = new ArrayList<>();  // to store edges
            for(int i: S){                            
                Vertex v = this.adjacencyList.get(i);
                ArrayList<Integer> temp2 = v.getNeighbors(); // to store neighbors;
                for(int j: temp2){
                    if(!S.contains(j)){
                        temp3.add(v.getEdgeTo(this.adjacencyList.get(j)));
                    }
                }
            }
            Edge theMin = findtheMini(temp3);
            T.add(theMin);
            if(S.contains(theMin.getA().getId())){
                S.add(theMin.getB().getId());
            }else{
                S.add(theMin.getA().getId());
            }
        }
        return T;
    }


    public static Edge findtheMini(ArrayList<Edge> ls){
        Edge min = ls.get(0);
        for(Edge e: ls){
            if(e.getWeight() < min.getWeight()){
                min = e;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph();
        g.addEdge(1, 2, 9);
        g.addEdge(2, 3, 24);
        g.addEdge(1, 6, 14);
        g.addEdge(1, 7, 15);
        g.addEdge(6, 3, 18);
        g.addEdge(6, 5, 30);
        g.addEdge(6, 7, 5);
        g.addEdge(7, 5, 20);
        g.addEdge(5, 4, 11);
        g.addEdge(5, 3, 2);
        g.addEdge(5, 8, 16);
        g.addEdge(7, 8, 44);
        g.addEdge(4, 3, 6);
        g.addEdge(4, 8, 6);
        g.addEdge(3, 8, 19);

        System.out.println(Arrays.toString(g.edges()));
        System.out.println(g.Dijkstra(1, 8));
        System.out.println(g.Prim());


    }
}
