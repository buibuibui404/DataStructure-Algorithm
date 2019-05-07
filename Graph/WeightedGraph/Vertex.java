import java.util.HashMap;
import java.util.ArrayList;

public class Vertex {
    private int id;
    private HashMap<Integer, Edge> edges;
    private ArrayList<Integer> neighbors;

    public Vertex(int id) {
        this.id = id;
        this.edges = new HashMap<>();
        this.neighbors = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public HashMap<Integer, Edge> getEdges() {
        return this.edges;
    }

    public ArrayList<Integer> getNeighbors(){
        return this.neighbors;
    }

    public Edge getEdgeTo(Vertex v) {
        return this.edges.get(v.getId());
    }

    public void addEdge(Vertex v, Edge e) {
        this.edges.put(v.getId(), e);
        this.neighbors.add(v.getId());
    }
}
