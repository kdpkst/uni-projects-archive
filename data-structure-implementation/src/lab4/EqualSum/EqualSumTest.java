package lab4.EqualSum;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class EqualSumTest {
    @Test
    public void testTrueEqualSum() {
        List<Integer> list = Arrays.asList(2, 3, 5);
        assertEquals(true, EqualSum.equalSum(list));
    }

    @Test
    public void testTrueEqualSum_1() {
        List<Integer> list = Arrays.asList();
        assertEquals(true, EqualSum.equalSum(list));
    }

    @Test
    public void testTrueEqualSum_2() {
        List<Integer> list = Arrays.asList(0);
        assertEquals(true, EqualSum.equalSum(list));
    }

    @Test
    public void testTrueEqualSum_3() {
        List<Integer> list = Arrays.asList(1, 4, -5, -7, 3, 6, -2);
        assertEquals(true, EqualSum.equalSum(list));
    }

    @Test
    public void testFalseEqualSum() {
        List<Integer> list = Arrays.asList(2, 2, 5);
        assertEquals(false, EqualSum.equalSum(list));
    }

    @Test
    public void testFalseEqualSum_1() {
        List<Integer> list = Arrays.asList(1);
        assertEquals(false, EqualSum.equalSum(list));
    }

    @Test
    public void testFalseEqualSum_2() {
        List<Integer> list = Arrays.asList(1, 0, 9, 4);
        assertEquals(false, EqualSum.equalSum(list));
    }



	
}
