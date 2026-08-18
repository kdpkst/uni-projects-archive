package lab4.DelDuplicate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DelDuplicateTest {

    @Test
    public void testDelDuplicate_1() {
        String input = "aaabbc";
        String expected = "abc";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_2() {
        String input = "aaaaa";
        String expected = "a";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_3() {
        String input = "abcde";
        String expected = "abcde";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_4() {
        String input = "abcbcde";
        String expected = "abcbcde";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_5() {
        String input = "a";
        String expected = "a";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_6() {
        String input = "";
        String expected = "";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }

    @Test
    public void testDelDuplicate_7() {
        String input = "aaabcdccd";
        String expected = "abcdcd";
        assertEquals(expected, DelDuplicate.delDuplicate(input));
    }











}
