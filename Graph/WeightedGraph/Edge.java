import java.lang.Comparable;

public class Edge implements Comparable<Edge>{

    private Vertex a;
    private Vertex b;
    private int Weight;

    public Edge(Vertex a, Vertex b, int Weight) {
        this.a = a;
        this.b = b;
        this.Weight = Weight;
    }

    public String toString() {
        return String.format("V%s-%d-V%s", this.a.getId(), this.Weight, this.b.getId());
    }

    public int getWeight() {
        return Weight;
    }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }

    public int compareTo(Edge e){
        return this.Weight - e.getWeight();
    }
}
