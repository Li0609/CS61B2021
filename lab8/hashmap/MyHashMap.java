package hashmap;

import java.util.*;


/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int positions;
    private double loadfactor;
    private int size;
    private Set<K> h;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this.positions = 16;
        buckets = new Collection[16];
        loadfactor = 0.75;
        size = 0;
        h = new HashSet();
    }

    public MyHashMap(int initialSize) {
        this.positions = initialSize;
        buckets = new Collection[positions];
        loadfactor = 0.75;
        size = 0;
        HashSet<K> h= new HashSet<K>();

    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        positions = initialSize;
        this.loadfactor = maxLoad;
        buckets = new Collection[positions];
        size = 0;
        HashSet<K> h= new HashSet<K>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    private void resize(){
        /*
        positions = positions*2;
        buckets = new Collection[positions];
        loadfactor = size / positions;
        */
    }
    @Override
    public void clear() {
        size = 0;
        buckets = createTable(positions);
        HashSet<K> h= new HashSet<K>();
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0){
            return false;
        }
        int i = Math.floorMod(key.hashCode(),positions);
        Collection<Node> node = buckets[i];
        if(node == null){
           return false;
        }
        Iterator<Node> nn = node.iterator();
        while (nn.hasNext()) {
            if(nn.next().key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        /*
        if(containsKey(key) == false){
            return null;
        }

         */
        int i = Math.floorMod(key.hashCode(),positions);
        if(buckets[i] == null){
            return null;
        }
        Iterator<Node> nn = buckets[i].iterator();
        for(Node kk :buckets[i]){
            if(kk.key.equals(key)){
                return kk.value;
            }
        }
        return null;
         /*
        return null;
          */
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(size / positions >loadfactor){
            resize();
        }
        int i = Math.floorMod(key.hashCode(),positions);
        if(buckets[i] == null){
            buckets[i] = createBucket();
        }
        if(containsKey(key)){
            for(Node x : buckets[i]){
                if(x.key == key){
                    buckets[i].remove(x);
                    break;
                }
            }
            size = size -1;
        }
        buckets[i].add(createNode(key,value));
        size = size + 1;
        h.add(key);
    }

    @Override
    public Set<K> keySet() {
        return h;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    /*
    private class ArraySetIterator implements Iterator<K> {
        private int wizPos;
        public ArraySetIterator() { wizPos = 0; }
        public boolean hasNext() { return wizPos < size; }
        public K next() {
            K returnItem = buckets[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

     */

    @Override
    public Iterator<K> iterator() {
        return null;
    }

}
