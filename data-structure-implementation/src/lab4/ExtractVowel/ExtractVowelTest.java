package lab4.ExtractVowel;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExtractVowelTest {
    @Test
    public void testExtractVowels() {
        String str = "i love you 3000";
        assertEquals("ioeou", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_1() {
        String str = "";
        assertEquals("", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_2() {
        String str = "sdjk ppt ";
        assertEquals("", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_3() {
        String str = "a";
        assertEquals("a", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_4() {
        String str = "a e  pkk o g";
        assertEquals("aeo", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_5() {
        String str = "aiueo";
        assertEquals("aiueo", ExtractVowel.extractVowel(str));
    }

    @Test
    public void testExtractVowels_6() {
        String str = "hello master!!";
        assertEquals("eoae", ExtractVowel.extractVowel(str));
    }


}
