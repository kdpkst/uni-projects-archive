package lab5.MyList;

public class MyList {
    private int value;
    private MyList next;

    public MyList(int value, MyList next) {
        this.value = value;
        this.next = next;
    }

    // LAB EXERCISE #5.1 MYLIST ITERATIVE SQUARE MUTATE 

    /**
     * Square the elements of a MyList. Mutates the MyList.
     * @param list is a MyList object.
     */
    public static void iterSquareMutList(MyList list) {
        while (list != null){
            list.value = list.value * list.value;
            list = list.next;
        }
    }


    // LAB EXERCISE #5.2 MYLIST RECURSIVE SQUARE MUTATE 

    /**
     * Square the elements of a MyList. Mutates the MyList.
     * @param list is a MyList object.
     */
    public static void recSquareMutList(MyList list) {

        if (list == null)
            list = null;

        else {
            // base case
            if (list.next == null)
                list.value = list.value * list.value;
                // recursive step
            else {
                list.value = list.value * list.value;
                recSquareMutList(list.next);
            }
        }

    }


    // LAB EXERCISE #5.3 MYLIST ITERATIVE SQUARE IMMUTATE 

    /**
     * Square the elements of a MyList. Does not mutate the MyList.
     * @param list is a MyList object.
     * @return another MyList with all of input MyList's element squared.
     */
    public static MyList iterSquareList(MyList list) {

        if(list == null) return null;

        MyList result = new MyList(list.value * list.value,null);
        MyList ptr = result;
        list = list.next;
        while(list != null){
            ptr.next = new MyList(list.value * list.value,null);
            list = list.next;
            ptr = ptr.next;
        }

        return result;

    }


    /*
    this is another correct method:
    public static MyList iterSquareList(MyList list) {
        if (list == null) return null;

        int index = list.iterSize() - 1;
        MyList p = new MyList(list.get(index) * list.get(index), null);
        while (index > 0){
            index--;
            p = new MyList(list.get(index) * list.get(index), p);
        }
        return p;

    }

    */

    // LAB EXERCISE #5.4 MYLIST RECURSIVE SQUARE IMMUTATE 

    /**
     * Square the elements of a MyList. Does not mutate the MyList.
     * @param list is a MyList object.
     * @return another MyList with all of input MyList's element squared.
     */
    public static MyList recSquareList(MyList list) {
        // base case
        if (list == null)
            return null;
        // recursive step
        return new MyList(list.value * list.value, recSquareList(list.next));

    }


    // CODING ASSIGNMENT #5.1 MYLIST ITERATIVE CATENATE MUTATE 

    /**
     * if listA is null, then no need to change listA (because in this situation, listA cannot be changed!).
     * Catenate two MyLists, listA and listB. Mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList iterCatMutList(MyList listA, MyList listB) {

        if (listA == null)
            return listB;

        MyList p = listA;
        while(p.next != null){
            p = p.next;
        }
        p.next = listB;
        return listA;

    }


    // CODING ASSIGNMENT #5.2 MYLIST RECURSIVE CATENATE MUTATE 

    /**
     * Catenate two MyLists, listA and listB. Mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList recCatMutList(MyList listA, MyList listB) {

        if (listA == null)
            return listB;

        // base case
        if (listA.next == null){
            listA.next = listB;
            return listA;
        }
		
        // recursive step
        recCatMutList(listA.next, listB);
        return listA;

//        return new MyList(listA.value, recCatList(listA.next, listB));

    }


    // CODING ASSIGNMENT #5.3 MYLIST ITERATIVE CATENATE IMMUTATE 

    /**
     * Catenate two MyLists, listA and listB. Does not mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList iterCatList(MyList listA, MyList listB) {

        if (listA == null)
            return listB;

        MyList result = new MyList(listA.value, null);
        MyList p = result;
        listA = listA.next;
        while (listA != null){
            p.next = new MyList(listA.value, null);
            p = p.next;
            listA = listA.next;
        }
        p.next = listB;

        return result;

//        if (listA == null) {
//            return listB;
//        }
//
//        if (listB == null) {
//            return listA;
//        }
//
//        int iB = listB.iterSize() - 1;
//        MyList l = new MyList(listB.get(iB), null);
//        for (int idxB = listB.iterSize() - 2; idxB >= 0; --idxB){
//            l = new MyList(listB.get(idxB), l);
//        }
//
//        for (int idxA = listA.iterSize() - 1; idxA >= 0; --idxA){
//            l = new MyList(listA.get(idxA), l);
//
//        }
//
//		return l;
    }


    // CODING ASSIGNMENT #5.4 MYLIST RECURSIVE CATENATE IMMUTATE 

    /**
     * Catenate two MyLists, listA and listB. Does not mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList recCatList(MyList listA, MyList listB) {

        // base case
        if (listA == null)
            return listB;

        // recursive step
        return new MyList(listA.value, recCatList(listA.next, listB));

    }


    /*
     *
     *****  Do NOT modify the codes below from the lecture notes!  *****
     *****  Only for your JUnit Testing purposes!                  *****
     *
     */


    /**
     * @return the size of the MyList iteratively.
     */
    public int iterSize() {
        MyList p = this;
        int size = 0;
        while (p != null) {
            size += 1;
            p = p.next;
        }
        return size;
    }

    /**
     * @return the size of the MyList recursively.
     */
    public int recSize() {
        // base case
        if (next == null) {
            return 1;
        }
        // recursive step
        return 1 + this.next.recSize();
    }

    /**
     * @param i is a valid index of MyList.
     * @return the ith value of this MyList.
     */
    public int get(int i) {
        // base case
        if (i == 0) {
            return value;
        }
        // recursive step
        return next.get(i - 1);
    }

    /**
     * @param args is a variable number of integers.
     * @return a new MyList containing the integers in args.
     */
    public static MyList ofEntries(Integer... args) {
        MyList result, p;
        if (args.length > 0) {
            result = new MyList(args[0], null);
        } else {
            return null;
        }
        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.next) {
            p.next = new MyList(args[k], null);
        }
        return result;
    }

    /**
     * @param l is a MyList object.
     * @return true iff l is a MyList object containing the same sequence of
     * integers as this.
     */
    public boolean equals(Object l) {
        if (!(l instanceof MyList)) {
            return false;
        }
        MyList list = (MyList) l;
        MyList p;
        for (p = this; p != null && list != null; p = p.next, list = list.next) {
            if (p.value != list.value) {
                return false;
            }
        }
        if (p != null || list != null) {
            return false;
        }
        return true;
    }

    public String toString() {
        int size = this.recSize();
        String output= "[";
        for (int i = 0; i < size; i++) {
            output = output + this.get(i);
            if (i != size-1)
                output = output + ", ";
        }
        output = output + "]";
        return output;
    }

    public static void main(String[] args) {
        MyList list1 = MyList.ofEntries(2, 2, 4, 6);
        System.out.println(list1);

    }

	
	
}
