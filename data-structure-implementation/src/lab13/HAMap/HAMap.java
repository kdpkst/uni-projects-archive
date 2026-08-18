package lab13.HAMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  Hash-based Map
 */
public class HAMap<K, V> implements Iterable<K> {

    /**
     * Represents a key-value pair.
     */
    private class Entry {
        K key;
        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;

    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numBuckets;
    private int numEntries;
    private final double loadFactor;

    /**
     * @return a set of the keys contained in this map.
     */
    public HashSet<K> keySet() {
        return keySet;
    }

    /**
     * @return the number of entries in this map.
     */
    public int size() {
        return numEntries;
    }

    /**
     * @return the number of buckets in this map.
     */
    public int getNumBuckets() {
        return numBuckets;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */
    
	// INCLUDE your helper methods in EACH of your submissions that use them
	private int reduce(K k, int capacity){
        return Math.floorMod(k.hashCode(), capacity);
    }

    // only double the size of the hash buckets
    private void resize(){

        ArrayList<ArrayList<Entry>> originBuckets = this.buckets;
        int originBucketNum = this.numBuckets;

        this.numBuckets = 2 * this.numBuckets;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < getNumBuckets(); i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < originBucketNum; i++) {
            ArrayList<Entry> entryArr = originBuckets.get(i);
            for (Entry entry : entryArr){
                int index = reduce(entry.key, this.numBuckets);
                this.buckets.get(index).add(entry);
            }
        }
    }



    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE #13.2 CONSTRUCTORS

    public HAMap(int initialCapacity, double loadFactor) {
        this.numBuckets = initialCapacity;
		this.loadFactor = loadFactor;
        this.numEntries = 0;

        this.keySet = new HashSet<>();
        this.buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

    }


    public HAMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HAMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }


    // LAB EXERCISE #13.3 CLEAR

    /**
     * Removes all of the entries from this map.
     */
    public void clear() {

        this.keySet = new HashSet<>();
        this.buckets = new ArrayList<>();
        for (int i = 0; i < this.numBuckets; i++) {
            this.buckets.add(new ArrayList<>());
        }
        this.numEntries = 0;
		
    }


    // LAB EXERCISE #13.4 CONTAINS KEY and ITERATOR

    /**
     * @param key to be checked
     * @return true iff this map contains an entry with the specified key
     */
    public boolean containsKey(K key) {
        if (key == null) return false;
        return this.keySet.contains(key);
    }

    /**
     * @return an Iterator that iterates over the stored keys
     */
    @Override
    public Iterator<K> iterator() {
		return this.keySet.iterator();
    }


    // CODING ASSIGNMENT #13.1 GET
    /**
     * @param key of the value to be returned
     * @return the value to which the specified key is mapped
     *         null if this map contains no entries of the key
     */
    public V get(K key) {
		if (key == null) return null;
        if (!this.containsKey(key)){
            return null;
        }

        int index = reduce(key, getNumBuckets());
        ArrayList<Entry> entryArr = this.buckets.get(index);
        for (Entry entry : entryArr){
            if (entry.key.equals(key)){
                return entry.value;
            }
        }

		return null;
    }


    // CODING ASSIGNMENT #13.2 PUT

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained an entry with that key, the old value is replaced.
     * The key is not null.
     * @param key of the entry to be added
     * @param value of the entry to be added
     */
    public void put(K key, V value) {
		if (key == null) return;

        double entryNum = this.numEntries;
        double bucketsNum = this.numBuckets;
        double ratio = entryNum / bucketsNum;
        if (ratio >= this.loadFactor){
            this.resize();
        }

        int index = reduce(key, getNumBuckets());
        ArrayList<Entry> entryArr = this.buckets.get(index);
        if (this.containsKey(key)){
            for (Entry entry : entryArr) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                }
            }
        }
        else {
            entryArr.add(new Entry(key, value));
            numEntries++;
            keySet.add(key);
        }
		
		
    }
	
	
    // CODING ASSIGNMENT #13.3 REMOVE

    /**
     * Removes the entry for the specified key only if it is
     * currently mapped to the specified value.
     * @param key of the entry to be removed
     * @param value of the entry to be removed
     * @return the value if entry found,
     *         null otherwise
     */
    public V remove(K key, V value) {
		if (!containsKey(key)) return null;

        int index = reduce(key, this.getNumBuckets());
        ArrayList<Entry> entryArray = this.buckets.get(index);
        for (Entry entry : entryArray){
            if (entry.key.equals(key) && entry.value.equals(value)){
                V v = entry.value;
                entryArray.remove(entry);
                keySet.remove(key);
                numEntries--;
                return v;
            }
        }
		
		return null;
    }

    @Override
    public String toString() {
        String haMap = "{";
        for (int i = 0; i < getNumBuckets(); i++) {
            ArrayList<Entry> entryList = buckets.get(i);
            for (Entry entry : entryList){
                haMap = haMap + "(" + entry.key + "," + entry.value + "),";
            }
        }
        String _haMap = haMap.substring(0, haMap.length() - 1);
        haMap = _haMap + "}";
        return haMap;

    }

}
