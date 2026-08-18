package lab2.ConcatContain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConcatContainTest {

    @Test
    public void testConcatContain1() {
        assertEquals(2, ConcatContain.concatContain("ab", "baba"));
    }

    @Test
    public void testConcatContain2() {
        assertEquals(-1, ConcatContain.concatContain("ab", "abcde"));
    }

    @Test
    public void testConcatContain3() {
        assertEquals(0, ConcatContain.concatContain("abb", "abb"));
    }

    @Test
    public void testConcatContain4() {
        assertEquals(10, ConcatContain.concatContain("a", "aaaaaaaaaaa"));
    }

    @Test
    public void testConcatContain5() {
        assertEquals(-1, ConcatContain.concatContain("abd", "abdfej"));
    }

    @Test
    public void testConcatContain6() {
        assertEquals(0, ConcatContain.concatContain("abbced", "bce"));
    }

    @Test
    public void testConcatContain7() {
        assertEquals(0, ConcatContain.concatContain("aaaaaaaaaaaaaaaa", "a"));
    }


    @Test
    public void testConcatContain8() {
        assertEquals(-1, ConcatContain.concatContain("aaaaaaa", "b"));
    }

    @Test
    public void testConcatContain9() {
        assertEquals(0, ConcatContain.concatContain("abababab", "ba"));
    }

    @Test
    public void testConcatContain10() {
        assertEquals(1, ConcatContain.concatContain("abcab", "ba"));
    }





}