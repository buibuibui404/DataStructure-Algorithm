
import java.util.ArrayList;
import java.util.List;

public interface GraphInterface {
    public void addVertex(Vertex v, List<Vertex> ls);
    public void addEdge(Vertex v1, Vertex v2);
    public boolean removeVertex(Vertex v);
    public boolean removeEdge(Vertex v1, Vertex v2);
    public List<Vertex> getIncidentVertices(Vertex v1);
    public List<Vertex> getVetices();
    public String toString();
}
