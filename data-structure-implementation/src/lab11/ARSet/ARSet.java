package lab11.ARSet;

import java.util.Iterator;

public class ARSet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    /**
     * Create an empty set.
     */
    @SuppressWarnings("unchecked")
    public ARSet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * @return the number of items in the set
     */
    public int size() {
        return size;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // LAB EXERCISE #11.3  ITERATOR

    /**
     * Make an iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ARSetIterator();
    }

    private class ARSetIterator implements Iterator<T> {
        private int index;

        public ARSetIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T nextItem = items[index];
            index++;
            return nextItem;
        }
    }


    // CODING ASSIGNMENT #11.1  CONTAINS

    /**
     * Checks whether an item is inside the set.
     * @param item to be checked
     * @return true iff the set contains the item
     */
    public boolean contains(T item) {
        if (item == null)
            return false;

        for (int i = 0; i < size; ++i) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }


    // CODING ASSIGNMENT #11.2  ADD

    /**
     * Adds an item into the set if it is not already inside.
     * @param item to be added inside the set.
     * @throws IllegalArgumentException if item is null.
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (contains(item)) {
            return;
        }
        items[size] = item;
        size++;
    }

}