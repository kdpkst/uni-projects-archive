package lab4.OddAndTen;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OddAndTenTest {

    @Test
    public void testOddAndTen_1() {
        assertEquals(true, OddAndTen.oddAndTen(Arrays.asList(5, 5, 3)));
    }

    @Test
    public void testOddAndTen_2() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList(5, 5, 4)));
    }

    @Test
    public void testOddAndTen_3() {
        assertEquals(true, OddAndTen.oddAndTen(Arrays.asList(5, 5, 4, 1)));
    }

    @Test
    public void testOddAndTen_4() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList()));
    }

    @Test
    public void testOddAndTen_5() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList(0)));
    }

    @Test
    public void testOddAndTen_6() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList(2)));
    }

    @Test
    public void testOddAndTen_7() {
        assertEquals(true, OddAndTen.oddAndTen(Arrays.asList(3)));
    }

    @Test
    public void testOddAndTen_8() {
        assertEquals(true, OddAndTen.oddAndTen(Arrays.asList(20, 3)));
    }

    @Test
    public void testOddAndTen_9() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList(9, 3)));
    }

    @Test
    public void testOddAndTen_10() {
        assertEquals(false, OddAndTen.oddAndTen(Arrays.asList(9, 3, 7, 2, 7)));
    }





}
