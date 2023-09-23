public interface BloomFilterInterface {
    public void add(String s);
    public boolean contains(String s);
    public int filterSize();
    public int dataSize();
    public int numHashes();
    public double falsePositiveProbability();
    public void clear();
}
