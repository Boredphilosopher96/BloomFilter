public interface BloomFilterInterface {
    void add(String s);
    boolean contains(String s);
    int filterSize();
    int dataSize();
    int numHashFunctions();
    void clear();
}
