package lab3.MaxStretch;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxStretchTest {

    @Test
    public void testSingleMaxStretch_1() {
        List<Integer> list = Arrays.asList(8, 5, 1, 2, 3, 4, 5, 10);
        assertEquals(6, MaxStretch.maxStretch(list));
    }

    @Test
    public void testSingleMaxStretch_2() {
        List<Integer> list = Arrays.asList(10);
        assertEquals(1, MaxStretch.maxStretch(list));
    }
	
    @Test
    public void testSingleOverlapMaxStretch() {
        List<Integer> list = Arrays.asList(2, 7, 1, 2, 3, 7);
        assertEquals(5, MaxStretch.maxStretch(list));
    }
    
    @Test
    public void testMultipleMaxStretch_1() {
        List<Integer> list = Arrays.asList(5, 2, 2, 5, 2);
        assertEquals(4, MaxStretch.maxStretch(list));
    }

    @Test
    public void testMultipleMaxStretch_2() {
        List<Integer> list = Arrays.asList(5, 2, 2, 5, 2, 2);
        assertEquals(5, MaxStretch.maxStretch(list));
    }

    @Test
    public void testMultipleMaxStretch_3() {
        List<Integer> list = Arrays.asList(5, 2, 2, 5, 2, 1, 4, 5);
        assertEquals(8, MaxStretch.maxStretch(list));
    }

    @Test
    public void testMultipleMaxStretch_4() {
        List<Integer> list = Arrays.asList(5,5,5,5);
        assertEquals(4, MaxStretch.maxStretch(list));
    }

    @Test
    public void testMultipleMaxStretch_5() {
        List<Integer> list = Arrays.asList();
        assertEquals(0, MaxStretch.maxStretch(list));
    }

	
}