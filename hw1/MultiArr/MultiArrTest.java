import static org.junit.Assert.*;
import org.junit.Test;

public class MultiArrTest {

    @Test
    public void testMaxValue() {
        int[][] array = {{1, 2, 3}, {4, 5}, {6, 7, 5, 4, 3, 2}, {2, 8, 7}};
        assertEquals(8, MultiArr.maxValue(array));
    }

    @Test
    public void testAllRowSums() {
        int[][] array = {{1, 2, 3}, {4, 5}, {6, 7, 5, 4, 3, 2}, {2, 8, 7}};
        assertEquals(59, MultiArr.allRowSums(array));
        int[][] arrnone = {{}};
        assertEquals(0, MultiArr.allRowSums(arrnone));
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(MultiArrTest.class));
    }
}
