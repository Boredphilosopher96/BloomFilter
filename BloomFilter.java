import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.MurmurHash2;

import java.util.BitSet;
import java.util.Random;

public class BloomFilter implements BloomFilterInterface {
    private final BitSet bitSet;
    private int numHashFunctions = 5;
    private int dataSize = 0;

    private int[] hashFunctionSeedsList;

    public BloomFilter(int setSize) {
        this.bitSet = new BitSet(setSize);
        this.initializeHashFunctions();
    }


    public BloomFilter(int expectedNumItems, int numHashFunctions) {
        this.numHashFunctions = numHashFunctions;
        int bitSetSize = (int) Math.ceil(this.numHashFunctions * 1d * expectedNumItems / Math.log(2));
        this.bitSet = new BitSet(bitSetSize);
        this.initializeHashFunctions();
    }

    private void initializeHashFunctions() {
        this.hashFunctionSeedsList = new int[this.numHashFunctions];
        Random random = new Random();
        for (int i = 0; i < this.hashFunctionSeedsList.length; i++) {
            hashFunctionSeedsList[i] = random.nextInt(1000_000);
        }
    }

    public void add(String s) {
        int position;
        for (int i = 0; i < this.numHashFunctions; i++) {
            position = getHashedPosition(s, this.hashFunctionSeedsList[i]);
            this.bitSet.set(position);
        }
        this.dataSize += 1;
    }

    public int getHashedPosition(String s, int seed) {
        int hashedValue = getHashValue(s, seed);
        return hashedValue % this.bitSet.size();
    }

    public int getHashValue(String s, int seed) {
        byte[] bytes = StringUtils.getBytesUtf8(s);
        return Math.abs(MurmurHash2.hash32(bytes, bytes.length, seed));
    }

    public boolean contains(String s) {
        int position;
        for(int i = 0; i < this.numHashFunctions; i++) {
            position = getHashedPosition(s, this.hashFunctionSeedsList[i]);
            if(!this.bitSet.get(position)) return false;
        }
        return true;
    }

    public int filterSize() {
        return this.bitSet.size();
    }

    public int dataSize() {
        return this.dataSize;
    }

    public int numHashFunctions() {
        return this.numHashFunctions;
    }

    public double getFalsePositiveRate() {
        return 0;
    }

    public void clear() {
        this.bitSet.clear();
        this.dataSize = 0;
    }

}
