package lists;

import org.junit.Test;
import static org.junit.Assert.*;

/** Testing the method in Lists.java.
 *
 *  @author Matthew Sahim
 */

public class ListsTest {

    @Test
    public void naturalRunsTest() {
        assertEquals(IntListList.list(IntList.list(1, 3, 8),
                                      IntList.list(2, 6),
                                      IntList.list(5),
                                      IntList.list(4, 9)),
                Lists.naturalRuns(IntList.list(1, 3, 8, 2, 6, 5, 4, 9)));
    }

    // It might initially seem daunting to try to set up
    // IntListList expected.
    //
    // There is an easy way to get the IntListList that you want in just
    // few lines of code! Make note of the IntListList.list method that
    // takes as input a 2D array.

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
