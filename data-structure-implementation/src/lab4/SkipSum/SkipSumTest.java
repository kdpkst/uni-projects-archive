package lab4.SkipSum;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class SkipSumTest {

    @Test
    public void testSkipSum_1() {
        assertEquals(true, SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 12));
    }

    @Test
    public void testSkipSum_2() {
        assertEquals(false, SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 7));
    }

    @Test
    public void testSkipSum_3() {
        assertEquals(false, SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 16));
    }

    @Test
    public void testSkipSum_4() {
        assertEquals(false, SkipSum.skipSum(Arrays.asList(), 1));
    }

    @Test
    public void testSkipSum_5() {
        assertEquals(false, SkipSum.skipSum(Arrays.asList(4), 5));
    }

    @Test
    public void testSkipSum_6() {
        assertEquals(true, SkipSum.skipSum(Arrays.asList(5, 4, 2, 5), 9));
    }
    @Test
    public void testSkipSum_7() {
        assertEquals(true, SkipSum.skipSum(Arrays.asList(), 0));
    }

    @Test
    public void testSkipSum_8() {
        assertEquals(true, SkipSum.skipSum(Arrays.asList(5, 4, 1), 4));
    }

    @Test
    public void testSkipSum_9() {
        assertEquals(true, SkipSum.skipSum(Arrays.asList(3, 4, 2, 3, 0, -1), 7));
    }

    // try a longer one



}
