package lab3.MatchSwap;

import org.junit.Test;

import static java.util.Map.entry;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;


public class MatchSwapTest {

    @Test
    public void testSwap_1() {
        List<String> list = Arrays.asList("apple", "avocado");
        List<String> expected = Arrays.asList("avocado", "apple");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testSwap_2() {
        List<String> list = Arrays.asList("ab", "ac", "ad", "ae", "af");
        List<String> expected = Arrays.asList("ac", "ab", "ae", "ad", "af");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testSwap_3() {
        List<String> list = Arrays.asList("ap", "bp", "cp", "aq", "cq", "bq");
        List<String> expected = Arrays.asList("aq", "bq", "cq", "ap", "cp", "bp");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testSwap_4() {
        List<String> list = Arrays.asList("hello");
        List<String> expected = Arrays.asList("hello");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }


    @Test
    public void testSwap_5() {
        List<String> list = Arrays.asList("bc", "bc", "bc", "cr", "ct");
        List<String> expected = Arrays.asList("bc", "bc", "bc", "ct", "cr");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

	
}