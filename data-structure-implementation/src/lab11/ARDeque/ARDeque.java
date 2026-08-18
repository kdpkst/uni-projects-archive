package lab11.ARDeque;

import java.util.Iterator;

public class ARDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * @return the size of the array used in the deque.
     */
    public int itemsLength() {
        return items.length;
    }

    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */

    // add your own helper methods here
	
	
	
	
	// INCLUDE in your submission
	// if you use them in your method

    /*
     ***** HELPER METHODS END *****
     */
	 
	 
	// add your own ARDeque codes from previous labs
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.items[(this.nextFirst + i + 1) % this.items.length];
        }
        this.nextFirst = capacity - 1;
        this.nextLast = this.size;
        this.items = newItems;
    }


    @Override
    public void addLast(T item) {
        if (this.size == this.items.length) resize(this.items.length << 1);
        this.items[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1) % this.items.length;
        this.size++;
    }


    @Override
    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.items[(this.nextFirst + i + 1) % this.items.length] + " ");
        }
        System.out.println();
    }

    /**
     * Gets the item at the given index.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the index-th item of the deque.
     * @throws IndexOutOfBoundsException if no item exists at the given index.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException("Index " + index + " is not valid");
        return this.items[(this.nextFirst + index + 1) % this.items.length];
    }


    @Override
    public void addFirst(T item) {
        if (this.size == this.items.length) resize(this.items.length << 1);
        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.items.length) % this.items.length;
        this.size++;
    }


    @Override
    public T delFirst() {
        if (this.size < 1) return null;
        T ret = this.items[(this.nextFirst + 1) % this.items.length];
        this.nextFirst = (this.nextFirst + 1) % this.items.length;
        this.size--;
        if (this.size <= this.items.length >> 2) resize(this.items.length >> 1);
        return ret;
    }

    @Override
    public T delLast() {
        if (this.size < 1) return null;
        T ret = this.items[(this.nextLast - 1 + this.items.length) % this.items.length];
        nextLast = (this.nextLast - 1 + this.items.length) % this.items.length;
        this.size--;
        if (this.size <= this.items.length >> 2) resize(this.items.length >> 1);
        return ret;
    }

	// CODING ASSIGNMENT #11.3  ITERATOR

    /**
     * Make an iterator
     */
    @Override
    public Iterator<T> iterator() {
		return new ARDequeIterator();
    }

    private class ARDequeIterator implements Iterator<T> {

        private int index;

        public ARDequeIterator() {index = 0;}

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (hasNext()){
                T nextItem = get(index);
                index++;
                return nextItem;
            }

            return null;
        }
    }

}