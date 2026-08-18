package lab2.LeapYear;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeapYearTest {

    @Test
    public void testisLeapYear1() {
        boolean expected = true;
        boolean actual = LeapYear.isLeapYear(1952);
        assertEquals(expected, actual);
    }

    @Test
    public void testisLeapYear2() {
        boolean expected = true;
        boolean actual = LeapYear.isLeapYear(2020);
        assertEquals(expected, actual);
    }

    @Test
    public void testisLeapYear3() {
        boolean expected = false;
        boolean actual = LeapYear.isLeapYear(2019);
        assertEquals(expected, actual);
    }

    @Test
    public void testisLeapYear4() {
        boolean expected = false;
        boolean actual = LeapYear.isLeapYear(1900);
        assertEquals(expected, actual);
    }

    @Test
    public void testisLeapYear5() {
        boolean expected = false;
        boolean actual = LeapYear.isLeapYear(2017);
        assertEquals(expected, actual);
    }

}