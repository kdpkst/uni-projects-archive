package lab3.SameFirstLetter;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;


public class SameFirstLetterTest {

    @Test
    public void testSingleAppends() {
        List<String> list = Arrays.asList("alice");
        Map<String, String> expected = Map.ofEntries(
                entry("a", "alice")
        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }

    @Test
    public void testTwoAppends() {
        List<String> list = Arrays.asList("alice", "bob", "apple", "banana");
        Map<String, String> expected = Map.ofEntries(
                entry("a", "alice,apple"),
                entry("b", "bob,banana")
        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }

    @Test
    public void testThreeAppends() {
        List<String> list = Arrays.asList("after", "all", "this", "time", "always");
        Map<String, String> expected = Map.ofEntries(
                entry("a", "after,all,always"),
                entry("t", "this,time")
        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }

    @Test
    public void testAppends_1() {
        List<String> list = Arrays.asList("Erick", "egg");
        Map<String, String> expected = Map.ofEntries(
                entry("E", "Erick"),
                entry("e", "egg")
        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }

    @Test
    public void testAppends_2() {
        List<String> list = Arrays.asList("alice", "bob", "curry", "steph", "banana");
        Map<String, String> expected = Map.ofEntries(
                entry("a", "alice"),
                entry("b", "bob,banana"),
                entry("c", "curry"),
                entry("s", "steph")
        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }

    @Test
    public void testAppends_3() {
        List<String> list = Arrays.asList("curry", "cut", "cannot", "cat");
        Map<String, String> expected = Map.ofEntries(
                entry("c", "curry,cut,cannot,cat")

        );
        assertEquals(expected, SameFirstLetter.sameFirstLetter(list));
    }
	
	
}