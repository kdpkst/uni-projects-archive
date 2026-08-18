package lab6.LLDeque;

public class LLDeque<T> {

    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

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


    // LAB EXERCISE #6.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    public LLDeque() {
		sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
		size = 0;
    }


    // LAB EXERCISE #6.2 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     * @param item is a type T object added to the deque.
     */
    public void addFirst(T item) {

        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;

    }


    // LAB EXERCISE #6.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }


    // LAB EXERCISE #6.4 ITERATIVE GET ITEM

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the ith item of the deque, null if it does not exist.
     */
    public T iterGet(int index) {
		if (index >= size || index < 0 || size == 0) return null;

        Node p = sentinel;
        p = p.next;
        while (index != 0){
            p = p.next;
            index--;
        }

		return p.item;
    }


    // CODING ASSIGNMENT #6.1 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     * @param item is a type T object added to the deque.
     */
    public void addLast(T item) {

        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;

    }


    // CODING ASSIGNMENT #6.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the first item of the deque, null if it does not exist.
     */
    public T delFirst() {
		if (size == 0) return null;

        T originalFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return originalFirst;

    }


    // CODING ASSIGNMENT #6.3 DELETE BACK

    /**
     * Deletes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return the last item of the deque, null if it does not exist.
     */
    public T delLast() {
		if (size == 0) return null;

        T originalLast = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return originalLast;

    }


    // CODING ASSIGNMENT #6.4 RECURSIVE GET ITEM

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the ith item of the deque, null if it does not exist.
     */
    public T recGet(int index) {
        return recGetHelper(index, sentinel.next);
    }

    private T recGetHelper(int index, Node pointer) {
        if (index >= size || index < 0 || size == 0) return null;

        // base case
        if (index == 0){
            return pointer.item;
        }

        // recursive case
        index--;
        pointer = pointer.next;
        return recGetHelper(index, pointer);
    }


    public T remove(int index){
        if (index >= size || index < 0 || size == 0) return null;
        if (index == 0) return delFirst();
        if (index == size - 1) return delLast();

        Node p = sentinel.next;
        while (index != 0){
            p = p.next;
            index--;
        }

        T removedItem = p.item;
        Node pre = p.prev;
        Node nex = p.next;
        pre.next = nex;
        nex.prev = pre;

        size--;
        return removedItem;
    }

    public static void main(String[] args) {
//        LLDeque<String> deque = new LLDeque<>();
//        deque.addFirst("b");
//        deque.addFirst("a");
//        deque.printDeque();


    }

}
