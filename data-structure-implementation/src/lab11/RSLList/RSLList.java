package lab11.RSLList;

// LAB EXERCISE #11.2 RSLLIST ROTATE RIGHT

/** Rotating Singly-Linked List */

// complete the class declaration so that
// RSLList becomes a subclass of SLList
public class RSLList<T> extends SLList<T> {

    /**
     * Rotates list to the right.
     * Do nothing if the list is empty.
     */
    public void rotateRight() {
        if(size() == 0)
            return;

        T x = delLast();
        addFirst(x);
    }
}