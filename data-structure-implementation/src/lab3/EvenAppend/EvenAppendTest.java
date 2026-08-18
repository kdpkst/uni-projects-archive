package lab3.EvenAppend;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;


public class EvenAppendTest {

    @Test
    public void testSingleEven() {
        List<String> list = Arrays.asList("a", "b", "a");
        assertEquals("a", EvenAppend.evenAppend(list));
    }
    @Test
    public void testDoubleEven() {
        List<String> list = Arrays.asList("a", "b", "b", "a", "a", "b", "b");
        assertEquals("bab", EvenAppend.evenAppend(list));
    }
    @Test
    public void testNoEven() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        assertEquals("", EvenAppend.evenAppend(list));
    }

    @Test
    public void test_1() {
        List<String> list = Arrays.asList();
        assertEquals("", EvenAppend.evenAppend(list));
    }

    @Test
    public void test_2() {
        List<String> list = Arrays.asList("f");
        assertEquals("", EvenAppend.evenAppend(list));
    }

    @Test
    public void test_3() {
        List<String> list = Arrays.asList("a", "a");
        assertEquals("a", EvenAppend.evenAppend(list));
    }

    @Test
    public void test_4() {
        List<String> list = Arrays.asList("a", "b", "b", "a", "c", "c", "a", "e", "a");
        assertEquals("baca", EvenAppend.evenAppend(list));
    }
	
	
	
	
}
