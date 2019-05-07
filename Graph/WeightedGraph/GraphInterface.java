import java.util.List;

public interface GraphInterface {
    void addEdge(int a, int b, int lifetime);
    Edge[] edges();
}
