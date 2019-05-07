public interface Pq{
    public int size();
    public boolean insert(int p);
    public Integer min();
    public Integer removeMin();
    public boolean remove(int p);
    public boolean replaceValue(int oldValue, int newValue);
}