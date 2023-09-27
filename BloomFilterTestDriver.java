public class BloomFilterTestDriver {
    public static void main(String[] args) {
        String[] list = {"Apple", "Ball", "Cat", "Dog"};
        String[] testList = {"Apple", "Meow", "Bow", "Mat", "Dog"};
        BloomFilter bf = new BloomFilter(10);
        for (String s : list) {
            bf.add(s);
        }

        for (String s : testList) {
            System.out.println("Does the bf contain " + s + " : " + (bf.contains(s)?"may be yes":"definitely not!"));
        }

    }
}
