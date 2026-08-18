package lab7.LLDeque;

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


    // CODING ASSIGNMENT #7.1 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
	 * @param other is another LLDeque<T> object.
     */
    public LLDeque(LLDeque<T> other) {
        size = other.size();
        sentinel = new Node(null, null, null);

        Node pThis = sentinel;
		Node pOther = other.sentinel.next;

        // !!! Notice here "other" is a circular doubly-linked list,
        // which means the stop condition cannot be "pOther != null"
        while (pOther != other.sentinel){
            pThis.next = new Node(pThis, pOther.item, null);
            pOther = pOther.next;
            pThis = pThis.next;
        }

        sentinel.prev = pThis;
        pThis.next = sentinel;

//   below is a simple version:
//        this();
//
//        for (int i = 0; i < other.size; i++) {
//            this.addLast(other.iterGet(i));
//        }

//        size = other.size();

    }


    // CODING ASSIGNMENT #7.2 ADD NOT NULL TO FRONT

    /**
     * Adds a non-null item of type T to the front of the deque.
     * @param item is a type T object.
     * @throws IllegalArgumentException if the item is null.
     */
    public void addFirst(T item) {
		if (item == null) {
            throw new IllegalArgumentException();
        }

        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;

        size++;
    }


    // CODING ASSIGNMENT #7.3 ADD LEGAL ITEM TO FRONT

    /**
     * Adds the first item of type T to the front of the deque,
     * or the second item of type T instead if the first item is illegal.
     * @param item1 is a type T object.
     * @param item2 is a type T object.
     */
    public void addLegalFirst(T item1, T item2) {
		try{
            this.addFirst(item1);
        }
        catch(IllegalArgumentException iae){
            this.addFirst(item2);
        }
		
    }

	
	
	
	
    /*
     *************************************************************
     * You can copy and paste Lab 6 codes below if you want to use it
     *************************************************************
     */

    public LLDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


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

    public void addLast(T item) {

        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;

    }
	
	
	

}
