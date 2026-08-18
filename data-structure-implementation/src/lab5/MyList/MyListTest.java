package lab5.MyList;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyListTest {

    // SQUARE TESTS

    @Test
    public void testIterativeSquareMutateMyList() {
        MyList list = MyList.ofEntries(1, 2, 3);
        MyList.iterSquareMutList(list);
        assertEquals(MyList.ofEntries(1, 4, 9), list);
    }

    @Test
    public void testEmptyIterSquareMutate() {
        MyList list = MyList.ofEntries();
        MyList.iterSquareMutList(list);
        assertEquals(MyList.ofEntries(), list);
    }

    @Test
    public void testRecursiveSquareMutateMyList_1() {
        MyList list = MyList.ofEntries(1, 2, 3);
        MyList.recSquareMutList(list);
        assertEquals(MyList.ofEntries(1, 4, 9), list);
    }

    @Test
    public void testRecursiveSquareMutateMyList_2() {
        MyList list = MyList.ofEntries();
        MyList.recSquareMutList(list);
        assertEquals(MyList.ofEntries(), list);
    }


    @Test
    public void testIterativeSquareImmutateMyList() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.iterSquareList(list1);
        assertEquals(MyList.ofEntries(1, 2, 3), list1);
        assertEquals(MyList.ofEntries(1, 4, 9), list2);
    }

    @Test
    public void testRecursiveSquareImmutateMyList_1() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.recSquareList(list1);
        assertEquals(MyList.ofEntries(1, 2, 3), list1);
        assertEquals(MyList.ofEntries(1, 4, 9), list2);
    }

    @Test
    public void testRecursiveSquareImmutateMyList_2() {
        MyList list1 = MyList.ofEntries(4);
        MyList list2 = MyList.recSquareList(list1);
        assertEquals(MyList.ofEntries(4), list1);
        assertEquals(MyList.ofEntries(16), list2);
    }

    @Test
    public void testRecursiveSquareImmutateMyList_3() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.recSquareList(list1);
        assertEquals(MyList.ofEntries(), list1);
        assertEquals(MyList.ofEntries(), list2);
    }




    // CATENATE TESTS

    // ITER MUTATE TESTS

    @Test
    public void testIterativeCatenateMutateMyList_1() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(1, 2, 3, 4, 5, 6);
        assertEquals(exp, MyList.iterCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(1, 2, 3, 4, 5, 6), list1);
    }

    @Test
    public void testIterativeCatenateMutateMyList_2() {
        MyList list1 = MyList.ofEntries(5);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(5, 4, 5, 6);
        assertEquals(exp, MyList.iterCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(5, 4, 5, 6), list1);
    }

    @Test
    public void testEmptyIterMutate() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries();
        assertEquals(exp, MyList.iterCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testAEmptyIterMutate() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries(5);
        MyList exp = MyList.ofEntries(5);
        assertEquals(exp, MyList.iterCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testBEmptyIterMutate() {
        MyList list1 = MyList.ofEntries(5);
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries(5);
        assertEquals(exp, MyList.iterCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(5), list1);
    }
	
	// add your own test cases

	
	
	
    // REC MUTATE TESTS

    @Test
    public void testRecursiveCatenateMutateMyList_1() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(1, 2, 3, 4, 5, 6);
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(1, 2, 3, 4, 5, 6), list1);
    }

    @Test
    public void testRecursiveCatenateMutateMyList_2() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries();
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testRecursiveCatenateMutateMyList_3() {
        MyList list1 = MyList.ofEntries(6);
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries(6);
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(6), list1);
    }

    @Test
    public void testRecursiveCatenateMutateMyList_4() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries(3);
        MyList exp = MyList.ofEntries(3);
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testRecursiveCatenateMutateMyList_5() {
        MyList list1 = MyList.ofEntries(1);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(1, 4, 5, 6);
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(1, 4, 5, 6), list1);
    }

    @Test
    public void testRecursiveCatenateMutateMyList_6() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.ofEntries(4);
        MyList exp = MyList.ofEntries(1, 2, 3, 4);
        assertEquals(exp, MyList.recCatMutList(list1, list2));
        assertEquals(MyList.ofEntries(1, 2, 3, 4), list1);
    }







    // ITER IMMUTATE TESTS

    @Test
    public void testIterativeCatenateImmutateMyList_1() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(1, 2, 3, 4, 5, 6);
        assertEquals(exp, MyList.iterCatList(list1, list2));
        assertEquals(MyList.ofEntries(1, 2, 3), list1);
    }

    @Test
    public void testIterativeCatenateImmutateMyList_2() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries();
        assertEquals(exp, MyList.iterCatList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testIterativeCatenateImmutateMyList_3() {
        MyList list1 = MyList.ofEntries(4);
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries(4);
        assertEquals(exp, MyList.iterCatList(list1, list2));
        assertEquals(MyList.ofEntries(4), list1);
    }

    @Test
    public void testIterativeCatenateImmutateMyList_4() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries(5);
        MyList exp = MyList.ofEntries(5);
        assertEquals(exp, MyList.iterCatList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    // add your own test cases
	
	
	

    
    
    // REC IMMUTATE TESTS

    @Test
    public void testRecursiveCatenateImmutateMyList_1() {
        MyList list1 = MyList.ofEntries(1, 2, 3);
        MyList list2 = MyList.ofEntries(4, 5, 6);
        MyList exp = MyList.ofEntries(1, 2, 3, 4, 5, 6);
        assertEquals(exp, MyList.recCatList(list1, list2));
        assertEquals(MyList.ofEntries(1, 2, 3), list1);
    }

    @Test
    public void testRecursiveCatenateImmutateMyList_2() {
        MyList list1 = MyList.ofEntries(3);
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries(3);
        assertEquals(exp, MyList.recCatList(list1, list2));
        assertEquals(MyList.ofEntries(3), list1);
    }

    @Test
    public void testRecursiveCatenateImmutateMyList_3() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries(5);
        MyList exp = MyList.ofEntries(5);
        assertEquals(exp, MyList.recCatList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }

    @Test
    public void testRecursiveCatenateImmutateMyList_4() {
        MyList list1 = MyList.ofEntries();
        MyList list2 = MyList.ofEntries();
        MyList exp = MyList.ofEntries();
        assertEquals(exp, MyList.recCatList(list1, list2));
        assertEquals(MyList.ofEntries(), list1);
    }


	
	
	
	


}
