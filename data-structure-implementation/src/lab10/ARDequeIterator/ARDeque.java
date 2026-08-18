package lab10.ARDequeIterator;

public class ARDeque<T> {
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
     ******************* HELPER METHODS START *******************
     ***** Include your helper method(s) in EACH Submission *****
     *********************** that uses it ***********************
     */


    /* Resizes the underlying array to the target capacity. */
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
    /*
     ******************** HELPER METHODS END ********************
     */


    // LAB EXERCISE #9.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    @SuppressWarnings("unchecked")
    public ARDeque() {
        this.size = 0;
        this.items = (T[]) new Object[4];
        this.nextFirst = 1;
        this.nextLast = 2;
    }

    // LAB EXERCISE #9.2 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item is a type T object to be added.
     */
    public void addLast(T item) {
        if (this.size == this.items.length) resize(this.items.length << 1);
        this.items[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1) % this.items.length;
        this.size++;
    }


    // LAB EXERCISE #9.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.items[(this.nextFirst + i + 1) % this.items.length] + " ");
        }
        System.out.println();
    }

    // LAB EXERCISE #9.4 GET ITEM

    /**
     * Gets the item at the given index.
     * Does not mutate the deque.
     *
     * @param index is an index where 0 is the front.
     * @return the index-th item of the deque.
     * @throws IndexOutOfBoundsException if no item exists at the given index.
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException("Index " + index + " is not valid");
        return this.items[(this.nextFirst + index + 1) % this.items.length];
    }

    // CODING ASSIGNMENT #9.1 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item is a type T object to be added.
     */
    public void addFirst(T item) {
        if (this.size == this.items.length) resize(this.items.length << 1);
        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.items.length) % this.items.length;
        this.size++;
    }


    // CODING ASSIGNMENT #9.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     *
     * @return the first item of the deque, null if it does not exist.
     */
    public T delFirst() {
        if (this.size < 1) return null;
        T ret = this.items[(this.nextFirst + 1) % this.items.length];
        this.nextFirst = (this.nextFirst + 1) % this.items.length;
        this.size--;
        if (this.size <= this.items.length >> 2) resize(this.items.length >> 1);
        return ret;
    }


    // CODING ASSIGNMENT #9.3 DELETE BACK

    /**
     * Deletes and returns the item at the back  of the deque.
     * If no such item exists, returns null.
     *
     * @return the last item of the deque, null if it does not exist.
     */
    public T delLast() {
        if (this.size < 1) return null;
        T ret = this.items[(this.nextLast - 1 + this.items.length) % this.items.length];
        nextLast = (this.nextLast - 1 + this.items.length) % this.items.length;
        this.size--;
        if (this.size <= this.items.length >> 2) resize(this.items.length >> 1);
        return ret;
    }


    // CODING ASSIGNMENT #9.4 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
     *
     * @param other is another ARDeque<T> object.
     */
    @SuppressWarnings("unchecked")
    public ARDeque(ARDeque<T> other) {
        this.size = other.size;
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
        this.items = (T[]) new Object[other.items.length];
        for (int i = 0; i < other.items.length; i++) {
            this.items[i] = other.items[i];
        }
    }
}
