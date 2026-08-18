package lab2.AppearOnce;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppearOnceTest {

    @Test
    public void testAppearOnce1() {
        char expectedCharacter = 'b';
        assertEquals(expectedCharacter, AppearOnce.appearOnce("abca"));
    }

    @Test
    public void testAppearOnce2() {
        assertEquals('\n', AppearOnce.appearOnce("abccba"));
    }

    @Test
    public void testAppearOnce3() {
        assertEquals('d', AppearOnce.appearOnce("abccbacd"));
    }

    @Test
    public void testAppearOnce4() {
        assertEquals('f', AppearOnce.appearOnce("f"));
    }


}