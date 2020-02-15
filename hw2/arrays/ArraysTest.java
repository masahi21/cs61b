package arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/** Testing the methods defined in Array.java.
 *  @author Matthew Sahim
 */

public class ArraysTest {
    int[] arr = {1, 3, 8, 2, 6, 5, 7, 9};


    int[] none = {};
    int[] arr1 = {1, 2, 3};
    int[] arr2 = {4, 5, 6};
    int[] arr3 = {1, 2, 3, 4, 5, 6};

    @Test
    public void catenateTest() {
        assertTrue(Utils.equals(arr3, Arrays.catenate(arr1, arr2)));
    }
    @Test
    public void removeTest() {
        assertTrue(Utils.equals(arr1, Arrays.remove(arr3, 3, 3)));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
