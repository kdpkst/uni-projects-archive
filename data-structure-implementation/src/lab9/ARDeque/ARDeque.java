package lab9.ARDeque;


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
		T[] newArr = (T[]) new Object[capacity];

        int start = 0;
        if (nextFirst == items.length - 1) {start = 0;}
        else {start = nextFirst + 1;}

        int count = 0;
        while(count < size){
            newArr[count + 1] = items[start];
            if (start == items.length - 1) {start = 0;}
            else {start++;}
            count++;
        }

        nextFirst = 0;
        nextLast = count + 1;
        items = newArr;
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
        items = (T[]) new Object[4];
        nextFirst = 1;
        nextLast = 2;
        size = 0;
    }


    // LAB EXERCISE #9.2 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     * @param item is a type T object to be added.
     */
    public void addLast(T item) {
		if (size == items.length){
            resize(items.length * 2);
        }

        items[nextLast] = item;
        if (nextLast == items.length - 1) {nextLast = 0;}
        else {nextLast++;}
		size++;
		
    }


    // LAB EXERCISE #9.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    public void printDeque() {
		int start = 0;
		int end = 0;
        if (nextFirst == items.length - 1) {start = 0;}
        else {start = nextFirst + 1;}

        if (nextLast == 0) {end = items.length - 1;}
        else {end = nextLast - 1;}

        int count = 0;
        while(count < size){
            if (count == size - 1){System.out.println(items[end]);}
            else {System.out.print(items[start] + " ");}

            if (start == items.length - 1) {start = 0;}
            else {start++;}
            count++;
        }

        if (size == 0){
            System.out.println(" ");
        }

    }


    // LAB EXERCISE #9.4 GET ITEM

    /**
     * Gets the item at the given index.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the index-th item of the deque.
     * @throws IndexOutOfBoundsException if no item exists at the given index.
     */
    public T get(int index) {
		if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException("Index " + index + " is not valid");

        // for below. A simple version: return items[(nextFirst + 1 + index) % itemsLength()];
        int start = 0;
        if (nextFirst == items.length - 1) {start = 0;}
        else {start = nextFirst + 1;}

        if (start + index > items.length - 1) {
            int realInd = index + start - items.length;
            return items[realInd];
        }
        else{return items[start + index];}


    }


    // CODING ASSIGNMENT #9.1 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     * @param item is a type T object to be added.
     */
    public void addFirst(T item) {

        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {nextFirst = items.length - 1;}
        else {nextFirst--;}
        size++;

    }


    // CODING ASSIGNMENT #9.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the first item of the deque, null if it does not exist.
     */
    public T delFirst() {

        if (size == 0) return null;

        int first = 0;
        if (nextFirst == items.length - 1) {first = 0;}
        else {first = nextFirst + 1;}

        T temp = items[first];
        items[first] = null;

        if (nextFirst == items.length - 1) {nextFirst = 0;}
        else {nextFirst++;}

        size--;
        if (size <= itemsLength()/4){
            resize(itemsLength() / 2);
        }
		return temp;
    }


    // CODING ASSIGNMENT #9.3 DELETE BACK

    /**
     * Deletes and returns the item at the back  of the deque.
     * If no such item exists, returns null.
     * @return the last item of the deque, null if it does not exist.
     */
    public T delLast() {

        if (size == 0) return null;

        int last = 0;
        if (nextLast == 0){last = itemsLength() - 1;}
        else {last = nextLast - 1;}

        T temp = items[last];
        items[last] = null;

        if (nextLast == 0) {nextLast = items.length - 1;}
        else {nextLast--;}

        size--;
        if (size <= itemsLength() / 4){
            resize(itemsLength() / 2);
        }
		return temp;

    }


    // CODING ASSIGNMENT #9.4 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
     * @param other is another ARDeque<T> object.
     */
    @SuppressWarnings("unchecked")
    public ARDeque(ARDeque<T> other) {

        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
        this.size = other.size;

		this.items = (T[]) new Object[other.itemsLength()];

        for (int i = 0; i < other.itemsLength(); ++i){
            this.items[i] = other.items[i];
        }

	}


}
