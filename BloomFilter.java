import java.util.BitSet;
import org.apache.commons.codec.digest.MurmurHash2;
public class BloomFilter implements BloomFilterInterface {
    private final BitSet bitSet;
    private int dataSize = 0;
    public BloomFilter(int setSize) {
        this.bitSet = new BitSet(setSize);
    }
    public void add(String s) {
        int position = getHashedPosition(s);
        this.bitSet.set(position);
    }

    public int getHashedPosition(String s) {
        int hashedValue = getHashValue(s);
        return hashedValue % this.bitSet.size();
    }

    public int getHashValue(String s) {
        return MurmurHash2.hash32(s);
    }

    public boolean contains(String s) {
        int position = getHashedPosition(s);
        return this.bitSet.get(position);
    }

    public int filterSize() {
        return this.bitSet.size();
    }

    public int dataSize() {
        return this.dataSize;
    }

    public int numHashes() {
        return 0;
    }

    public double falsePositiveProbability() {
        return 0;
    }

    public void clear() {
        this.bitSet.clear();
        this.dataSize = 0;
    }

    public static int getRecommendedSize() {
//        m = -(n * math.log(p))/(math.log(2)**2)
//        return int(m)
        return 0;
    }
}
