package lab10.ARDequeIterator;

public class ARDequeIterator<T> {

    private final ARDeque<T> deque;
    private int index;


    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // LAB EXERCISE #9.1 CONSTRUCTOR AND HASNEXT

    /**
     * Make an iterator.
     * @param deque deque to iterate over
     */
    @SuppressWarnings("unchecked")
    public ARDequeIterator(ARDeque deque) {
        this.deque = deque;
		this.index = 0;
    }

    /**
     * Test whether the iterator has more items to return.
     * @return true if next() will return another item,
     *         false if all items have been returned
     */
    @SuppressWarnings("unchecked")
    public boolean hasNext() {
        return this.index < deque.size();
    }


    // LAB EXERCISE #9.2 NEXT

    /**
     * Get the next item of the deque.
     * Requires: hasNext() returns true.
     * Modifies: this iterator to advance it to the item
     *           following the returned item.
     * @return next item of the deque
     */
    @SuppressWarnings("unchecked")
    public T next() {
		if (hasNext()){
            T object = deque.get(index);
            index++;
            return object;
        }
		
		return null;
    }


    public static void main(String[] args) {
        ARDeque<String> deque = new ARDeque<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        ARDequeIterator<String> iter = new ARDequeIterator<>(deque);
        while (iter.hasNext()) {
            String str = iter.next();
            System.out.print(str + " ");
        }
        System.out.println();
		
    }
}