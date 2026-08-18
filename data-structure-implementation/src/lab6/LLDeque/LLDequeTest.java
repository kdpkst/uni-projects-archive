package lab6.LLDeque;

import org.junit.Test;
import static org.junit.Assert.*;

public class LLDequeTest {

    @Test
    public void testEXERCISE() {
        LLDeque<String> deque = new LLDeque<>();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        deque.addFirst("b");
        deque.addFirst("a");
        assertEquals("a", deque.iterGet(0));
        assertEquals("b", deque.iterGet(1));
        assertEquals(null, deque.iterGet(2));
        assertEquals(null, deque.iterGet(4));

    }

	

    @Test
    public void testASSIGNMENT_1() {
        LLDeque<String> deque = new LLDeque<>();
        deque.addLast("a");
        assertEquals("a", deque.iterGet(0));
        deque.addLast("b");
        deque.addLast("c");
        assertEquals("a", deque.iterGet(0));
        assertEquals("b", deque.iterGet(1));
        assertEquals("c", deque.iterGet(2));

    }

    @Test
    public void testASSIGNMENT_2() {
        LLDeque<String> deque = new LLDeque<>();
        assertEquals(null, deque.delFirst());
        deque.addLast("a");
        assertEquals("a", deque.delFirst());
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");
        assertEquals("b", deque.delFirst());
        assertEquals("c", deque.delFirst());
    }

    @Test
    public void testASSIGNMENT_3() {
        LLDeque<String> deque = new LLDeque<>();
        assertEquals(null, deque.delLast());
        deque.addLast("a");
        assertEquals(1, deque.size());
        assertEquals("a", deque.delLast());
        assertEquals(0, deque.size());
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");
        assertEquals("d", deque.delLast());
        assertEquals(2, deque.size());
    }

    @Test
    public void testASSIGNMENT_4() {
        LLDeque<String> deque = new LLDeque<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        assertEquals("c", deque.recGet(2));
        assertEquals("a", deque.delFirst());
        assertEquals("c", deque.delLast());
        assertEquals("b", deque.recGet(0));
    }
	
	
	
	
}
