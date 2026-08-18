package lab3.CountRuns;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import lab3.EvenAppend.EvenAppend;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountRunsTest {

    @Test
    public void test_1() {
        List<Integer> list = Arrays.asList(1, 2, 2, 2, 3);
        assertEquals(1, CountRuns.countRuns(list));
    }

    @Test
    public void test_2() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5, 5);
        assertEquals(2, CountRuns.countRuns(list));
    }

    @Test
    public void test_3() {
        List<Integer> list = Arrays.asList();
        assertEquals(0, CountRuns.countRuns(list));
    }

    @Test
    public void test_4() {
        List<Integer> list = Arrays.asList(1);
        assertEquals(0, CountRuns.countRuns(list));
    }

    @Test
    public void test_5() {
        List<Integer> list = Arrays.asList(8, 2);
        assertEquals(0, CountRuns.countRuns(list));
    }

    @Test
    public void test_6() {
        List<Integer> list = Arrays.asList(3, 3);
        assertEquals(1, CountRuns.countRuns(list));
    }


    @Test
    public void test_7() {
        List<Integer> list = Arrays.asList(6, 6, 6, 6);
        assertEquals(1 , CountRuns.countRuns(list));
    }

    @Test
    public void test_8() {
        List<Integer> list = Arrays.asList(1, 5, 5, 1, 1, 4, 6, 6);
        assertEquals(3, CountRuns.countRuns(list));
    }

    @Test
    public void test_9() {
        List<Integer> list = Arrays.asList(1, 1, 5, 1, 1, 4, 6);
        assertEquals(2, CountRuns.countRuns(list));
    }

    @Test
    public void test_10() {
        List<Integer> list = Arrays.asList(1, 1, 1, 1, 5, 2, 2, 1, 6,7,0,5,4, 4, 4, 4, 6);
        assertEquals(3, CountRuns.countRuns(list));
    }


}