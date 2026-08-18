package lab3.Partitionable;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartitionableTest {

    @Test
    public void testPartitionable_1() {
        List<Integer> list = Arrays.asList(1, 1, 1, 2, 1);
        assertTrue(Partitionable.isPartitionable(list));

    }

    @Test
    public void testPartitionable_2() {
        List<Integer> list = Arrays.asList(0, -1, 1, 2, 4, -6);
        assertTrue(Partitionable.isPartitionable(list));
    }

    @Test
    public void testPartitionable_3() {
        List<Integer> list = Arrays.asList(15, 1, -6, -2, 4, 4);
        assertTrue(Partitionable.isPartitionable(list));
    }

    @Test
    public void testPartitionable_4() {
        List<Integer> list = Arrays.asList(11, 0, 0, 0, 7, 10, 8);
        assertTrue(Partitionable.isPartitionable(list));
    }

    @Test
    public void testPartitionable_5() {
        List<Integer> list = Arrays.asList(0);
        assertTrue(Partitionable.isPartitionable(list));
    }




    @Test
    public void testNotPartitionable_1() {
        List<Integer> list = Arrays.asList(2, 1, 1, 2, 1);
        assertFalse(Partitionable.isPartitionable(list));
    }

    @Test
    public void testNotPartitionable_2() {
        List<Integer> list = Arrays.asList(7);
        assertFalse(Partitionable.isPartitionable(list));
    }

    @Test
    public void testNotPartitionable_3() {
        List<Integer> list = Arrays.asList(3, 1, 4, 11, 9);
        assertFalse(Partitionable.isPartitionable(list));
    }

    @Test
    public void testNotPartitionable_4() {
        List<Integer> list = Arrays.asList(2, 10, 1, 0, -6);
        assertFalse(Partitionable.isPartitionable(list));
    }




}