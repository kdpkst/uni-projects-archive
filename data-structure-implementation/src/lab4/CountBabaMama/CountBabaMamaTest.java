package lab4.CountBabaMama;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CountBabaMamaTest {

    @Test
    public void testCountBabaMama_1() {
        assertEquals(2, CountBabaMama.countBabaMama("aba babaa amama ma"));
    }

    @Test
    public void testCountBabaMama_2() {
        assertEquals(4, CountBabaMama.countBabaMama("bababamamama"));
    }

    @Test
    public void testCountBabaMama_3() {
        assertEquals(0, CountBabaMama.countBabaMama(""));
    }

    @Test
    public void testCountBabaMama_4() {
        assertEquals(0, CountBabaMama.countBabaMama(" "));
    }

    @Test
    public void testCountBabaMama_5() {
        assertEquals(0, CountBabaMama.countBabaMama("mam"));
    }

    @Test
    public void testCountBabaMama_6() {
        assertEquals(0, CountBabaMama.countBabaMama("aba amaaam"));
    }

    @Test
    public void testCountBabaMama_7() {
        assertEquals(3, CountBabaMama.countBabaMama("baba bababmama "));
    }

    @Test
    public void testCountBabaMama_8() {
        assertEquals(4, CountBabaMama.countBabaMama("mamamamama"));
    }

    @Test
    public void testCountBabaMama_9() {
        assertEquals(1, CountBabaMama.countBabaMama("baba"));
    }

}
